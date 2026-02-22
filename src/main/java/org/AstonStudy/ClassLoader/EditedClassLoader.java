package org.AstonStudy.ClassLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EditedClassLoader implements ClassLoader {
    private List<Record> dataRecord = new ArrayList<>();

    // Загрузчик нового файла
    @Override
    public void load() {
        System.out.println("Добавьте файл в папку ClassLoader и назовите его EditedClassLoaderFile");
        Path path = Paths.get("src/main/java/org/AstonStudy/ClassLoader/", "EditedClassLoaderFile");



        try {
            List<String> lines = Files.readAllLines(path);

            if (lines.isEmpty()) {
                System.out.println("Файл пуст.");
                return;
            }

            if (lines.size() % 3 != 0) {
                System.out.println("Файл содержит " + lines.size() + " строк, а должно быть кратно 3.");
                return;
            }

            for (int i = 0; i < lines.size(); i += 3) {
                String name = lines.get(i).substring(10).trim();
                String something1 = lines.get(i+1).substring(9).trim();
                String something2 = lines.get(i+2).substring(9).trim();
                dataRecord.add(new Record(name, something1, something2));
            }
        } catch (IOException e) {
            System.out.println("Вы не добавили файл EditedClassLoaderFile в папку ClassLoader");
            e.printStackTrace();
        }
    }
}
