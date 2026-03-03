package org.AstonStudy.util;

import org.AstonStudy.Car;
import org.junit.jupiter.api.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHelperTest {

    @Test
    @DisplayName("Add to File Test")
    void objectsInString() {
        Car car1 = new Car.Builder()
                .model("Волга")
                .power(80)
                .year(1980)
                .build();
        Car car2 = new Car.Builder()
                .model("Лада")
                .power(90)
                .year(2015)
                .build();
        Car car3 = new Car.Builder()
                .model("Москвич")
                .power(71)
                .year(1982)
                .build();
        List<Car> list = new ArrayList<>();
        list.add(car1);
        list.add(car2);
        list.add(car3);

        FileHelper.appendToFile("Output.txt", list);

        String[] lineExpected = new String[] {"80;Волга;1980", "90;Лада;2015", "71;Москвич;1982"};
        String lineFromFile;
        try(BufferedReader reader = new BufferedReader(new FileReader("Output.txt"))) {
            for (int i = 0; i < 3; i++) {
                lineFromFile = reader.readLine();
                Assertions.assertEquals(lineExpected[i], lineFromFile);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
