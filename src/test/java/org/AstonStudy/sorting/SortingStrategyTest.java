package org.AstonStudy.sorting;

import org.AstonStudy.collection.MyArrayList;
import org.AstonStudy.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortingStrategyTest {

    private MyArrayList<Car> cars;

    @BeforeEach
    void setUp() {
        cars = new MyArrayList<>();
        cars.add(new Car.Builder().power(200).model("BMW").year(2021).build());
        cars.add(new Car.Builder().power(180).model("Audi").year(2020).build());
        cars.add(new Car.Builder().power(150).model("Toyota").year(2019).build());
        cars.add(new Car.Builder().power(180).model("Opel").year(2018).build());
    }

    @Nested
    @DisplayName("BubbleSortStrategy tests")
    class BubbleSortTests {

        private SortingStrategy<Car> bubbleSort;

        @BeforeEach
        void init() {
            bubbleSort = new BubbleSortStrategy<>();
        }

        @Test
        @DisplayName("Sort by power")
        void sortByPower() {
            bubbleSort.sort(cars, Car.byPower());
            assertAll(
                    () -> assertEquals(150, cars.get(0).getPower()),
                    () -> assertEquals(180, cars.get(1).getPower()),
                    () -> assertEquals(180, cars.get(2).getPower()),
                    () -> assertEquals(200, cars.get(3).getPower())
            );
        }

        @Test
        @DisplayName("Sort by model")
        void sortByModel() {
            bubbleSort.sort(cars, Car.byModel());
            assertAll(
                    () -> assertEquals("Audi", cars.get(0).getModel()),
                    () -> assertEquals("BMW", cars.get(1).getModel()),
                    () -> assertEquals("Opel", cars.get(2).getModel()),
                    () -> assertEquals("Toyota", cars.get(3).getModel())
            );
        }

        @Test
        @DisplayName("Sort by year")
        void sortByYear() {
            bubbleSort.sort(cars, Car.byYear());
            assertAll(
                    () -> assertEquals(2018, cars.get(0).getYear()),
                    () -> assertEquals(2019, cars.get(1).getYear()),
                    () -> assertEquals(2020, cars.get(2).getYear()),
                    () -> assertEquals(2021, cars.get(3).getYear())
            );
        }
    }

    @Nested
    @DisplayName("InsertionSortStrategy tests")
    class InsertionSortTests {

        private SortingStrategy<Car> insertionSort;

        @BeforeEach
        void init() {
            insertionSort = new InsertionSortStrategy<>();
        }

        @Test
        @DisplayName("Sort by power")
        void sortByPower() {
            insertionSort.sort(cars, Car.byPower());
            assertAll(
                    () -> assertEquals(150, cars.get(0).getPower()),
                    () -> assertEquals(180, cars.get(1).getPower()),
                    () -> assertEquals(180, cars.get(2).getPower()),
                    () -> assertEquals(200, cars.get(3).getPower())
            );
        }

        @Test
        @DisplayName("Sort by model")
        void sortByModel() {
            insertionSort.sort(cars, Car.byModel());
            assertAll(
                    () -> assertEquals("Audi", cars.get(0).getModel()),
                    () -> assertEquals("BMW", cars.get(1).getModel()),
                    () -> assertEquals("Opel", cars.get(2).getModel()),
                    () -> assertEquals("Toyota", cars.get(3).getModel())
            );
        }

        @Test
        @DisplayName("Sort by year")
        void sortByYear() {
            insertionSort.sort(cars, Car.byYear());
            assertAll(
                    () -> assertEquals(2018, cars.get(0).getYear()),
                    () -> assertEquals(2019, cars.get(1).getYear()),
                    () -> assertEquals(2020, cars.get(2).getYear()),
                    () -> assertEquals(2021, cars.get(3).getYear())
            );
        }
    }

    @Test
    @DisplayName("Empty list sorting should not throw")
    void emptyList() {
        MyArrayList<Car> empty = new MyArrayList<>();
        SortingStrategy<Car> bubble = new BubbleSortStrategy<>();
        bubble.sort(empty, Car.byPower());
        assertTrue(empty.isEmpty());

        SortingStrategy<Car> insertion = new InsertionSortStrategy<>();
        insertion.sort(empty, Car.byModel());
        assertTrue(empty.isEmpty());
    }

    @Test
    @DisplayName("Single element list sorting should not change")
    void singleElement() {
        MyArrayList<Car> single = new MyArrayList<>();
        single.add(new Car.Builder().power(100).model("Test").year(2000).build());
        SortingStrategy<Car> bubble = new BubbleSortStrategy<>();
        bubble.sort(single, Car.byPower());
        assertEquals(100, single.get(0).getPower());

        SortingStrategy<Car> insertion = new InsertionSortStrategy<>();
        insertion.sort(single, Car.byModel());
        assertEquals("Test", single.get(0).getModel());
    }
}