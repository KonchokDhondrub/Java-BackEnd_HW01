import dataProcessor.DataProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataProcessorTest {

    @Nested
    public class test_Operations {

        @Test
        @DisplayName("Operation Sort")
        void operationSort() {
            List<String> data = new ArrayList<>(List.of("c", "b", "a"));
            Collections.sort(data);
            Assertions.assertEquals(List.of("a", "b", "c"), data);
        }

        @Test
        @DisplayName("Operation Reverse")
        void operationReverse() {
            List<String> data = new ArrayList<>(List.of("a", "b", "c"));
            Collections.reverse(data);
            Assertions.assertEquals(List.of("c", "b", "a"), data);
        }

        @Test
        @DisplayName("Operation Reverse")
        void operationShuffle() {
            List<String> data = new ArrayList<>(List.of("a", "b", "c"));
            Collections.shuffle(data);
            Assertions.assertEquals(3, data.size());
            Assertions.assertTrue(data.containsAll(List.of("c", "b", "a")));
        }
    }

    @Nested
    public class test_filteringEmptyLines {

        @Test
        @DisplayName("Operation Shuffle")
        void filteringEmptyLines() {
            List<String> lines = List.of("Line1", "  ", "", "Line2");
            List<String> filteredLines = lines.stream()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .toList();
            Assertions.assertEquals(2, filteredLines.size());
            Assertions.assertEquals(List.of("Line1", "Line2"), filteredLines);
        }
    }

    @Nested
    public class test_createNewOutputFileName {

        @Test
        @DisplayName("Creating ne Output name with extension")
        void test_createNewOutputFileName_withExtension() {
            String input = "filename.txt";
            String operationName = "Sort";
            String expected = "filename_sort.txt";
            String actual = DataProcessor.createNewOutputFileName(input, operationName);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Creating ne Output name without extension")
        void test_createNewOutputFileName_withoutExtension() {
            String input = "filename";
            String operationName = "Sort";
            String expected = "filename_sort";
            String actual = DataProcessor.createNewOutputFileName(input, operationName);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Creating ne Output name with more that one extension")
        void test_createNewOutputFileName_withMoreThatOneExtension() {
            String input = "filename.module.txt";
            String operationName = "Sort";
            String expected = "filename_sort.module.txt";
            String actual = DataProcessor.createNewOutputFileName(input, operationName);
            Assertions.assertEquals(expected, actual);
        }


    }
}
