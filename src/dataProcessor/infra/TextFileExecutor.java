package dataProcessor.infra;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TextFileExecutor {
    public static void execute(String fromFile, String action) {
        String newFileName = createNewOutputFileName(fromFile, action);
        try (
                BufferedReader in = new BufferedReader(new FileReader(fromFile));
                PrintWriter wr = new PrintWriter(new FileWriter(newFileName))
        ) {


            File file = new File(fromFile);
            List<String> text = new ArrayList<>(Files.readAllLines(Path.of(file.getPath())));
            if (text.isEmpty()) {
                System.out.println("В файле нет данных для обработки.");
                return;
            }



            char[] chars = action.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);

            action = new String(chars);
            Class<?> clazz = Class.forName("dataProcessor.operators." + action);
            Manipulation manipulator = (Manipulation) clazz.getDeclaredConstructor().newInstance();
            manipulator.action(text);
            System.out.println("Данные из файла: " + fromFile + " получены. Начинаю процесс: " + action);

            text.forEach(wr::println);
            wr.flush();
            System.out.println("Обработанные данные сохранены в файл: " + Path.of(newFileName).toAbsolutePath());

        } catch (FileNotFoundException e) {
            System.out.println("Wrong file name");
        } catch (IOException e) {
            System.out.println("Wrong reading data");
        } catch (ClassNotFoundException e) {
            System.out.println("Wrong name of operation");
        } catch (InstantiationException e) {
            System.out.println("no default constructor");
        } catch (IllegalAccessException e) {
            System.out.println("default constructor isn't public");
        } catch (Exception e) {
            System.out.println("Unknown ERROR");
            e.printStackTrace();
        }
    }

    public static String createNewOutputFileName(String fromFile, String action) {
        int dotIndex = fromFile.indexOf(".");
        String part1 = (dotIndex != -1) ? fromFile.substring(0, dotIndex) : fromFile;
        String part2 = (dotIndex != -1) ? fromFile.substring(dotIndex) : "";

        return part1 + "_" + action.replace(" ", "") + part2;
    }
}