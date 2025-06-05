package dataProcessor.operators;

import dataProcessor.DataOperation;
import java.util.Collections;
import java.util.List;

public class ShuffleOperation implements DataOperation {
    public String getName() { return "Shuffle"; }
    public void apply(List<String> data) {
        Collections.shuffle(data);
    }
}
