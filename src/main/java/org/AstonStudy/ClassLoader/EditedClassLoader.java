package org.AstonStudy.ClassLoader;

import org.AstonStudy.Car;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EditedClassLoader implements ClassLoader {
    private final List<Car> carRecord = new ArrayList<Car>();

    public List<Car> getCarRecord() {
        return carRecord;
    }

    // Загрузчик нового файла
    @Override
    public void load() {
        String help = """ 
                Добавьте в файл EditedClassLoader свои значения типа
                "MODEL: " + Ваше значение,
                "YEAR: " + Ваше значение,
                "POWER: " + Ваше значение,
                Название в кавычках это "Теги", на основе которых потом ищутся значения
                """;
        System.out.printf(help);
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
                if (!lines.get(i).startsWith("MODEL: ")) {
                    System.out.println("Строка " + (i+1) + " должна начинаться с 'MODEL: '");
                    continue;
                }
                if (!lines.get(i + 1).startsWith("YEAR: ")) {
                    System.out.println("Строка " + (i + 2) + " должна начинаться с 'YEAR: '");
                    continue;
                }
                if (!lines.get(i + 2).startsWith("POWER: ")) {
                    System.out.println("Строка " + (i + 3) + " должна начинаться с 'POWER: '");
                    continue;
                }
                String model = lines.get(i).substring(7).trim();
                String year = lines.get(i+1).substring(6).trim();
                String power = lines.get(i+2).substring(7).trim();
                carRecord.add(Car.createNewCar(model, year, power));
            }
        } catch (IOException e) {
            System.out.println("Вы не добавили файл EditedClassLoaderFile в папку ClassLoader");
            e.printStackTrace();
        }
    }
}
