package org.AstonStudy.data;

import org.AstonStudy.collection.MyArrayList;
import org.AstonStudy.model.Car;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DataProvider {
    static Path PATH_DEFAULT_CARS = Paths.get("src\\main\\java\\org\\AstonStudy\\data", "DefaultCars");

    public static MyArrayList<Car> fromFile (String filename) {
        try (Stream<String> lines = Files.lines(Paths.get(filename))) {
            return lines
                    .map(line ->{
                        String[] parts = line.split(";");
                        if (parts.length != 3) {
                            System.err.println("Некорректная строка " + line);
                            return null;
                        } try {
                            return new Car.Builder()
                                    .power(Integer.parseInt(parts[0].trim()))
                                    .model(parts[1].trim())
                                    .year(Integer.parseInt(parts[2].trim()))
                                    .build();
                        } catch (Exception e) {
                            System.err.println("Ошибка создать Car из строчки " + line + " — " + e.getMessage());
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(MyArrayList::new, MyArrayList::add, MyArrayList::addAll);
        } catch (IOException e) {
            System.err.println("Файл не найден " + filename);
            return new MyArrayList<>();
        }
    }

    public static MyArrayList<Car> fromFile() {
        try (Stream<String> lines = Files.lines(Paths.get(String.valueOf(PATH_DEFAULT_CARS)))) {
            return lines
                    .map(line ->{
                        String[] parts = line.split(";");
                        if (parts.length != 3) {
                            System.err.println("Некорректная строка " + line);
                            return null;
                        } try {
                            return new Car.Builder()
                                    .power(Integer.parseInt(parts[0].trim()))
                                    .model(parts[1].trim())
                                    .year(Integer.parseInt(parts[2].trim()))
                                    .build();
                        } catch (Exception e) {
                            System.err.println("Ошибка создать Car из строчки " + line + " — " + e.getMessage());
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(MyArrayList::new, MyArrayList::add, MyArrayList::addAll);
        } catch (IOException e) {
            System.err.println("Файл не найден " + String.valueOf(PATH_DEFAULT_CARS));
            return new MyArrayList<>();
        }
    }


    public static MyArrayList<Car> random(int count) {
        Random random = new Random();
        return IntStream.range(0, count)
                .mapToObj(i -> {
                    int power = 50 + random.nextInt(1951);
                    String model = "Model" + (random.nextInt(100) + 1);
                    int year = 1980 + random.nextInt(46);
                    return new Car.Builder()
                            .power(power)
                            .model(model)
                            .year(year)
                            .build();
                })
                .collect(MyArrayList::new, MyArrayList::add, MyArrayList::addAll);
    }

    public static MyArrayList<Car> manual(Scanner scanner ,int count) {

        MyArrayList<Car> carMyArrayList = new MyArrayList<>();
        System.out.println("Вводите автомобили в формате: мощность;модель;год");
        for (int i = 0; i < count; i++) {
            while (true) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                if (parts.length != 3) {
                    System.out.println("Ошибка: Должно быть три поля через \";\".");
                    continue;
                }
                try {
                    Car car = new Car.Builder()
                            .power(Integer.parseInt(parts[0].trim()))
                            .model(parts[1].trim())
                            .year(Integer.parseInt(parts[2].trim()))
                            .build();
                    carMyArrayList.add(car);
                    break;
                } catch (Exception e) {
                    System.out.println("Ошибка: " + e.getMessage() + ". Попробуйте снова");
                }
            }
        }
        return carMyArrayList;
    }
}