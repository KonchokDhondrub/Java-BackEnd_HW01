package dataProcessor.operators;

import dataProcessor.DataOperation;
import java.util.Collections;
import java.util.List;

public class SortOperation implements DataOperation {
    public String getName() { return "Sort"; }
    public void apply(List<String> data) {
        Collections.sort(data);
    }
}
