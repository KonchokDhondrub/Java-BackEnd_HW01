package dataProcessor.operators;

import dataProcessor.DataOperation;
import java.util.List;

public class ToLowerCase implements DataOperation {
    public String getName() { return "To LowerCase"; }
    public void apply(List<String> data) {
        data.replaceAll(String::toLowerCase);
    }
}
