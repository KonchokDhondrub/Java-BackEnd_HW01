package dataProcessor;

import java.util.List;

public interface DataOperation {
    String getName();
    void apply(List<String> data);
}
