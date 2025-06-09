package dataProcessor.operators;

import dataProcessor.infra.Manipulation;

import java.util.Collections;
import java.util.List;

public class Reverse implements Manipulation {
    @Override
    public void action(List<String> data) {
        Collections.reverse(data);
    }
}
