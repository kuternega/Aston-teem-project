package org.AstonStudy.data;

import org.AstonStudy.collection.MyArrayList;
import org.AstonStudy.model.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class DataProviderTest {

    @Test
    @DisplayName("fromFile")
    void fromFileTest() {
        Path path = Paths.get("src\\test\\resources", "fromFileTest");
        MyArrayList<Car> fromFileCars = DataProvider.fromFile(String.valueOf(path));
        System.out.println(fromFileCars.isEmpty());
        System.out.println(fromFileCars.toString());
    }

    @Test
    @DisplayName("missingFile")
    void missingFileTest() {
        Path path = Paths.get("src\\test\\resources", "fromFileTest2");
        DataProvider.fromFile(String.valueOf(path));
    }

    @Test
    @DisplayName("random")
    void randomTest() {
        MyArrayList<Car> randomCars = DataProvider.random(1);
        System.out.println(randomCars.toString());
    }

    @Test
    @DisplayName("manual")
    void manualTest() {
        String input = "200;Toyota;2020\n150;Honda;2019\n";
        Scanner testScanner = new Scanner(input);
        MyArrayList<Car> manualCars = DataProvider.manual(testScanner, 2);
        System.out.println(manualCars.toString());
    }
}
