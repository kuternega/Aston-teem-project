package org.AstonStudy.util;

import org.AstonStudy.collection.MyArrayList;
import org.AstonStudy.model.Car;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class MultiThreadCounterTest {

    private MyArrayList<Car> cars;
    private Car carA, carB, carC, nonExistent;

    @BeforeEach
    void setUp() {
        carA = new Car.Builder().power(150).model("AA").year(2010).build();
        carB = new Car.Builder().power(200).model("BB").year(2012).build();
        carC = new Car.Builder().power(180).model("CC").year(2011).build();

        cars = new MyArrayList<>();
        // carA: 3 раза, carB: 5 раз, carC: 1 раз, null: 1 раз
        for (int i = 0; i < 3; i++) cars.add(carA);
        for (int i = 0; i < 5; i++) cars.add(carB);
        cars.add(carC);
        cars.add(null);
    }

    @Test
    @DisplayName("Многопоточный подсчёт совпадает с однопоточным для разного числа потоков")
    void testCountOccurrences() {
        int expectedA = countSingleThread(cars, carA);
        int expectedB = countSingleThread(cars, carB);
        int expectedC = countSingleThread(cars, carC);
        int expectedNull = countSingleThread(cars, null);

        assertEquals(3, expectedA);
        assertEquals(5, expectedB);
        assertEquals(1, expectedC);
        assertEquals(1, expectedNull);

        int[] threadCounts = {1, 2, 3, 4, 5, 10};
        for (int tc : threadCounts) {
            int resultA = MultiThreadCounter.countOccurrences(cars, carA, tc);
            int resultB = MultiThreadCounter.countOccurrences(cars, carB, tc);
            int resultC = MultiThreadCounter.countOccurrences(cars, carC, tc);
            int resultNull = MultiThreadCounter.countOccurrences(cars, null, tc);

            assertAll("Потоков: " + tc,
                    () -> assertEquals(expectedA, resultA),
                    () -> assertEquals(expectedB, resultB),
                    () -> assertEquals(expectedC, resultC),
                    () -> assertEquals(expectedNull, resultNull)
            );
        }
    }

    @Test
    @DisplayName("Пустой список всегда возвращает 0")
    void testEmptyList() {
        MyArrayList<Car> empty = new MyArrayList<>();
        assertEquals(0, MultiThreadCounter.countOccurrences(empty, carA, 1));
        assertEquals(0, MultiThreadCounter.countOccurrences(empty, carA, 5));
        assertEquals(0, MultiThreadCounter.countOccurrences(empty, null, 2));
    }

    @Test
    @DisplayName("Некорректное число потоков вызывает исключение")
    void testInvalidThreadCount() {
        assertThrows(IllegalArgumentException.class,
                () -> MultiThreadCounter.countOccurrences(cars, carA, 0));
        assertThrows(IllegalArgumentException.class,
                () -> MultiThreadCounter.countOccurrences(cars, carA, -1));
    }

    private int countSingleThread(MyArrayList<Car> list, Car target) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            Car car = list.get(i);
            if (car == null ? target == null : car.equals(target)) {
                count++;
            }
        }
        return count;
    }
}