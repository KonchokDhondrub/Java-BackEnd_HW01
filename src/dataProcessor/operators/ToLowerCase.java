package dataProcessor.operators;

import dataProcessor.infra.Manipulation;

import java.util.List;

public class ToLowerCase implements Manipulation {
    @Override
    public void action(List<String> data) {
        data.replaceAll(String::toLowerCase);
    }
}
