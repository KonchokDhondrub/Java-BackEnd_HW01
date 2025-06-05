package dataProcessor;

import java.io.File;
import java.net.URL;
import java.util.*;

public class OperationRegistry {
    private static final Map<String, DataOperation> operations = new LinkedHashMap<>();

    static {
        String packageName = "dataProcessor.operators";
        try {
            for (Class<?> clazz : getClasses(packageName)) {
                if (DataOperation.class.isAssignableFrom(clazz) && !clazz.isInterface()) {
                    DataOperation operation = (DataOperation) clazz.getDeclaredConstructor().newInstance();
                    register(operation);
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка загрузки операций: " + e.getMessage());
        }
    }

    public static void register(DataOperation operation) {
        operations.put(operation.getName(), operation);
    }

    // Remove duplicates
    public static Set<String> availableOperations() {
        return operations.keySet();
    }

    public static void applyOperation(String name, List<String> data) {
        DataOperation operation = operations.get(name);
        if (operation != null) {
            operation.apply(data);
        } else {
            throw new IllegalArgumentException("Неизвестная операция: " + name);
        }
    }

    // Classes search
    private static List<Class<?>> getClasses(String packageName) throws Exception {
        String path = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(path);

        if (resource == null) {
            throw new RuntimeException("Пакет не найден: " + packageName);
        }

        File directory = new File(resource.toURI());
        List<Class<?>> classes = new ArrayList<>();

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().replace(".class", "");
                Class<?> clazz = Class.forName(className);
                classes.add(clazz);
            }
        }

        return classes;
    }
}
