package dataProcessor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DataProcessor {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
//            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите имя файла: ");
            String fileNameInput = scanner.nextLine();
            fileNameInput = "inputData.txt";

            File file = new File(fileNameInput);

            if (!file.exists() || !file.isFile()) {
                System.out.println("Файл не найден!");
                return;
            } else if (file.length() == 0) {
                System.out.println("Файл пустой!");
                return;
            } else {
                System.out.println("Файл найден: " + file.getAbsolutePath());
            }

            /// Clearing empty lines
            List<String> incomeData = Files.readAllLines(Path.of(file.getPath()))
                    .stream()
                    .filter(line -> !line.trim().isEmpty())
                    .toList();
            if (incomeData.isEmpty()) {
                System.out.println("В файле нет данных для обработки.");
                return;
            }

            List<String> resultData = new ArrayList<>(incomeData);

            List<String> operators = List.of("Sort", "Shuffle", "Reverse");

            /// Menu
            String selectedOperation = menu(scanner, operators);

            switch (selectedOperation) {
                case "Sort" -> Collections.sort(resultData);
                case "Shuffle" -> Collections.shuffle(resultData);
                case "Reverse" -> Collections.reverse(resultData);
                default -> System.out.println("Неверный выбор операции");
            }

            String newOutputName = createNewOutputFileName(fileNameInput, selectedOperation);
            saveToFile(resultData, newOutputName);

        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлом: " + e.getMessage());
        }
    }

    public static String menu(Scanner scanner, List<String> operators) {
        System.out.println("Выберите вариант обработки данных:");
        for (int i = 0; i < operators.size(); i++) {
            System.out.printf("%d. %s \n", i + 1, operators.get(i));
        }
        System.out.print("Выберите номер операции: ");
        int select = 0;
        do {
            select = scanner.nextInt() - 1;
        } while (select < 0 || select >= operators.size());
        System.out.println("Выполняю операцию: " + operators.get(select));
        scanner.close();
        return operators.get(select);
    }

    public static String createNewOutputFileName(String fileNameInput, String selectedOperation) {
        int dotIndex = fileNameInput.indexOf(".");
        String part1 = (dotIndex != -1) ? fileNameInput.substring(0, dotIndex) : fileNameInput;
        String part2 = (dotIndex != -1) ? fileNameInput.substring(dotIndex) : "";

        return part1 + "_" + selectedOperation + part2;
    }

    public static void saveToFile(List<String> resultData, String newOutputName) throws IOException {
        try {
            Files.write(Path.of(newOutputName), resultData);
            System.out.println("Обработанные данные сохранены в файл: " + Path.of(newOutputName).toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }
}
