package org.AstonStudy.ClassLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NewClassLoader implements ClassLoader {
    private List<Record> dataRecord = new ArrayList<>();
    private static final Path FILE_PATH = Paths.get("src/main/java/org/AstonStudy/ClassLoader/", "EditedClassLoaderFile");


    public List<Record> getDataRecord() {
        return dataRecord;
    }

    // Загрузчик "Вручную"
    @Override
    public void load() {
        boolean isValid = false;
        while (!isValid) {
            System.out.println("Введите через \",\": Название, Что-то ещё, Что-то ещё. Когда закончите напишите \"Стоп\"");
            Scanner sc = new Scanner(System.in);
            String userAnswer = sc.nextLine();
            if (userAnswer.equalsIgnoreCase("Стоп")) {
                isValid = true;
            } else {
                String[] userClass = userAnswer.split(",");
                if (userClass.length == 3) {
                    Record record = new Record(userClass[0].trim(), userClass[1].trim(), userClass[2].trim());
                    dataRecord.add(record);
                    saveToFile(record);
                } else {
                    System.out.println("Ошибка: нужно ввести ровно три значения через запятую.");
                }
            }
        }
    }

    private void saveToFile(Record record) {
        List<String> lines = List.of(
                "НАЗВАНИЕ: " + record.getName(),
                "ЧТО-ТО1: " + record.getSomething1(),
                "ЧТО-ТО2: " + record.getSomething2()
        );
        try {
            Files.write(FILE_PATH, lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}
