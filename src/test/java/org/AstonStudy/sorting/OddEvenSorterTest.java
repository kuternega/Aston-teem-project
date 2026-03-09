package org.AstonStudy.sorting;

import org.AstonStudy.collection.MyArrayList;
import org.AstonStudy.model.Car;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class OddEvenSorterTest {

    private final SortingStrategy<Car> bubbleSort = new BubbleSortStrategy<>();

    @Nested
    @DisplayName("Сортировка по мощности")
    class SortByPowerTests {
        private MyArrayList<Car> cars;
        private Car car1, car2, car3, car4, car5, car6;

        @BeforeEach
        void setUp() {
            car1 = new Car.Builder().power(150).model("A1").year(2010).build();
            car2 = new Car.Builder().power(121).model("B2").year(2011).build();
            car3 = new Car.Builder().power(200).model("C3").year(2012).build();
            car4 = new Car.Builder().power(99).model("D4").year(2013).build();
            car5 = new Car.Builder().power(180).model("E5").year(2014).build();
            car6 = new Car.Builder().power(145).model("F6").year(2015).build();

            cars = new MyArrayList<>();
            cars.add(car1);
            cars.add(car2);
            cars.add(car3);
            cars.add(car4);
            cars.add(car5);
            cars.add(car6);
        }

        @Test
        @DisplayName("Чётные по возрастанию, нечётные не двигаются")
        void testSortEvenByPowerAscending() {
            OddEvenSorter.sortByEven(cars, Car::getPower, bubbleSort, Car.byPower());

            assertAll(
                    () -> assertEquals(car1, cars.get(0)),
                    () -> assertEquals(car2, cars.get(1)),
                    () -> assertEquals(car5, cars.get(2)),
                    () -> assertEquals(car4, cars.get(3)),
                    () -> assertEquals(car3, cars.get(4)),
                    () -> assertEquals(car6, cars.get(5))
            );
        }

        @Test
        @DisplayName("Чётные по убыванию, нечётные не двигаются")
        void testSortEvenByPowerDescending() {
            OddEvenSorter.sortByEven(cars, Car::getPower, bubbleSort, Car.byPower().reversed());

            assertAll(
                    () -> assertEquals(car3, cars.get(0)),
                    () -> assertEquals(car2, cars.get(1)),
                    () -> assertEquals(car5, cars.get(2)),
                    () -> assertEquals(car4, cars.get(3)),
                    () -> assertEquals(car1, cars.get(4)),
                    () -> assertEquals(car6, cars.get(5))
            );
        }
    }

    @Nested
    @DisplayName("Сортировка по году")
    class SortByYearTests {
        private MyArrayList<Car> cars;
        private Car car1, car2, car3, car4, car5, car6;

        @BeforeEach
        void setUp() {
            car1 = new Car.Builder().power(100).model("A1").year(2001).build();
            car2 = new Car.Builder().power(110).model("B2").year(2002).build();
            car3 = new Car.Builder().power(120).model("C3").year(2003).build();
            car4 = new Car.Builder().power(130).model("D4").year(2004).build();
            car5 = new Car.Builder().power(140).model("E5").year(2005).build();
            car6 = new Car.Builder().power(150).model("F6").year(2006).build();

            cars = new MyArrayList<>();
            cars.add(car1);
            cars.add(car2);
            cars.add(car3);
            cars.add(car4);
            cars.add(car5);
            cars.add(car6);
        }

        @Test
        @DisplayName("Чётные года по возрастанию")
        void testSortEvenByYearAscending() {
            OddEvenSorter.sortByEven(cars, Car::getYear, bubbleSort, Car.byYear());

            assertAll(
                    () -> assertEquals(car1, cars.get(0)),
                    () -> assertEquals(car2, cars.get(1)),
                    () -> assertEquals(car3, cars.get(2)),
                    () -> assertEquals(car4, cars.get(3)),
                    () -> assertEquals(car5, cars.get(4)),
                    () -> assertEquals(car6, cars.get(5))
            );
        }

        @Test
        @DisplayName("Чётные года по убыванию")
        void testSortEvenByYearDescending() {
            OddEvenSorter.sortByEven(cars, Car::getYear, bubbleSort, Car.byYear().reversed());

            assertAll(
                    () -> assertEquals(car1, cars.get(0)),
                    () -> assertEquals(car6, cars.get(1)),
                    () -> assertEquals(car3, cars.get(2)),
                    () -> assertEquals(car4, cars.get(3)),
                    () -> assertEquals(car5, cars.get(4)),
                    () -> assertEquals(car2, cars.get(5))
            );
        }
    }

    @Test
    @DisplayName("Пустой список не вызывает ошибок")
    void testEmptyList() {
        MyArrayList<Car> empty = new MyArrayList<>();
        assertDoesNotThrow(() -> OddEvenSorter.sortByEven(empty, Car::getPower, bubbleSort, Car.byPower()));
        assertEquals(0, empty.size());
    }

    @Test
    @DisplayName("Список без чётных элементов не изменяется")
    void testNoEvenElements() {
        MyArrayList<Car> cars = new MyArrayList<>();
        Car c1 = new Car.Builder().power(111).model("A1").year(2001).build();
        Car c2 = new Car.Builder().power(113).model("B2").year(2003).build();
        cars.add(c1);
        cars.add(c2);
        String before = cars.toString();

        OddEvenSorter.sortByEven(cars, Car::getPower, bubbleSort, Car.byPower());

        assertEquals(before, cars.toString());
    }
}