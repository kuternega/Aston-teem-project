package org.AstonStudy.ClassLoader;

import org.AstonStudy.Car;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NewClassLoader implements ClassLoader {
    private static final Path FILE_PATH = Paths.get("src/main/java/org/AstonStudy/ClassLoader/", "EditedClassLoaderFile");
    private final List<Car> carRecord = new ArrayList<Car>();

    public List<Car> getCarRecord() {
        return carRecord;
    }

    // Загрузчик "Вручную"
    @Override
    public void load() {
        boolean isValid = false;
        while (!isValid) {
            System.out.println("Введите через \",\": Название, Что-то ещё, Что-то ещё. " +
                    "Когда закончите напишите \"Стоп\" или \"Stop\"");
            Scanner sc = new Scanner(System.in);
            String userAnswer = sc.nextLine();
            if (userAnswer.equalsIgnoreCase("Стоп") || userAnswer.equalsIgnoreCase("Stop")) {
                isValid = true;
            } else {
                String[] userClass = userAnswer.split(",");
                if (userClass.length == 3) {
                    Car car = Car.createNewCar(userClass[0].trim(), userClass[1].trim(), userClass[2].trim());
                    carRecord.add(car);
                    saveToFile(car);
                } else {
                    System.out.println("Ошибка: нужно ввести ровно три значения через запятую.");
                }
            }
            sc.close();
        }
    }

    private void saveToFile(Car carRecord) {
        List<String> lines = List.of(
                "MODEL: " + carRecord.getModel(),
                "YEAR: " + carRecord.getYear(),
                "POWER: " + carRecord.getPower()
        );
        try {
            Files.write(FILE_PATH, lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}
