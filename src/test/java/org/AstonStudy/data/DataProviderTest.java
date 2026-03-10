package org.AstonStudy.data;

import org.AstonStudy.collection.MyArrayList;
import org.AstonStudy.model.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.time.Year;

public class DataProviderTest {

    @Test
    @DisplayName("fromFile")
    void fromFileTest() {
        Path path = Paths.get("src\\test\\resources", "fromFileTest");
        MyArrayList<Car> fromFileCars = DataProvider.fromFile(path.toString());
        assertEquals(1, fromFileCars.size());
        Car addedCar = fromFileCars.get(0);
        assertEquals(90, addedCar.getPower());
        assertEquals("Mazda", addedCar.getModel());
        assertEquals(2003, addedCar.getYear());
    }

    @Test
    @DisplayName("missingFile")
    void missingFileTest() {
        Path path = Paths.get("src\\test\\resources", "fromFileTest2");
        MyArrayList<Car> fromMissingFile = DataProvider.fromFile(path.toString());
        assertTrue(fromMissingFile.isEmpty());
    }

    @Test
    @DisplayName("random")
    void randomTest() {
        int count = 10;
        int currentYear = Year.now().getValue();
        MyArrayList<Car> randomCars = DataProvider.random(count);
        assertEquals(count, randomCars.size());

        for (Car car : randomCars) {
            assertAll(
                    () -> assertTrue(car.getPower() >= 50 && car.getPower() <= 2000),
                    () -> assertNotNull(car.getModel()),
                    () -> assertFalse(car.getModel().isEmpty()),
                    () -> assertTrue(car.getYear() >= 1980 && car.getYear() <= currentYear)
            );
        }
    }

    @Test
    @DisplayName("manual")
    void manualTest() {
        String input = "200;Toyota;2020\n150;Honda;2019\n";
        Scanner testScanner = new Scanner(input);
        MyArrayList<Car> manualCars = DataProvider.manual(testScanner, 2);
        assertEquals(2, manualCars.size());

        for (Car car : manualCars) {
            assertAll(
                    () -> assertTrue(car.getPower() == 200 || car.getPower() == 150),
                    () -> assertTrue(car.getModel().equals("Toyota") || car.getModel().equals("Honda")),
                    () -> assertTrue(car.getYear() == 2020 || car.getYear() == 2019)
            );
        }
    }
}
