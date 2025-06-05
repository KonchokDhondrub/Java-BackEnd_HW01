package dataProcessor.operators;

import dataProcessor.DataOperation;
import java.util.Collections;
import java.util.List;

public class ReverseOperation implements DataOperation {
    public String getName() { return "Reverse"; }
    public void apply(List<String> data) {
        Collections.reverse(data);
    }
}
