package dataProcessor.operators;

import dataProcessor.infra.Manipulation;

import java.util.Collections;
import java.util.List;

public class Sort implements Manipulation {
    @Override
    public void action(List<String> data) {
        Collections.sort(data);
    }
}
