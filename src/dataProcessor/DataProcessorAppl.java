package dataProcessor;

import dataProcessor.infra.TextFileExecutor;

public class DataProcessorAppl {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Wrong number of arguments");
            return;
        }
        TextFileExecutor.execute(args[0], args[1]);
    }
}