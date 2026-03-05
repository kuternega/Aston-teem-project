package org.AstonStudy.util;

import org.AstonStudy.collection.MyArrayList;
import org.AstonStudy.model.Car;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    private MyArrayList<String> list;

    @BeforeEach
    void setUp() {
        list = new MyArrayList<>();
    }

    @Nested
    @DisplayName("Тесты базовых операций")
    class BasicOperationsTests {

        @Test
        @DisplayName("Новый список должен быть пустым")
        void testNewListIsEmpty() {
            assertTrue(list.isEmpty());
            assertEquals(0, list.size());
        }

        @Test
        @DisplayName("Добавление одного элемента")
        void testAddOneElement() {
            boolean result = list.add("Первый");

            assertTrue(result);
            assertFalse(list.isEmpty());
            assertEquals(1, list.size());
            assertEquals("Первый", list.get(0));
        }

        @Test
        @DisplayName("Добавление нескольких элементов")
        void testAddMultipleElements() {
            list.add("Первый");
            list.add("Второй");
            list.add("Третий");

            assertEquals(3, list.size());
            assertEquals("Первый", list.get(0));
            assertEquals("Второй", list.get(1));
            assertEquals("Третий", list.get(2));
        }

        @Test
        @DisplayName("Получение элемента по индексу")
        void testGetElement() {
            list.add("Первый");
            list.add("Второй");

            assertEquals("Первый", list.get(0));
            assertEquals("Второй", list.get(1));
        }

        @Test
        @DisplayName("Изменение элемента по индексу")
        void testSetElement() {
            list.add("Первый");
            list.add("Второй");

            String oldValue = list.set(1, "Новый");

            assertEquals("Второй", oldValue);
            assertEquals("Новый", list.get(1));
        }

        @Test
        @DisplayName("Проверка toString")
        void testToString() {
            list.add("A");
            list.add("B");
            list.add("C");

            assertEquals("[A, B, C]", list.toString());
        }
    }

    @Nested
    @DisplayName("Тесты граничных случаев")
    class EdgeCasesTests {

        @Test
        @DisplayName("Получение элемента с несуществующим индексом")
        void testGetWithInvalidIndex() {
            list.add("Первый");

            assertAll("Проверка исключений при get",
                    () -> assertThrows(IndexOutOfBoundsException.class,
                            () -> list.get(-1)),
                    () -> assertThrows(IndexOutOfBoundsException.class,
                            () -> list.get(1))  // индекс равен размеру
            );
        }

        @Test
        @DisplayName("Изменение элемента с несуществующим индексом")
        void testSetWithInvalidIndex() {
            list.add("Первый");

            assertAll("Проверка исключений при set",
                    () -> assertThrows(IndexOutOfBoundsException.class,
                            () -> list.set(-1, "Тест")),
                    () -> assertThrows(IndexOutOfBoundsException.class,
                            () -> list.set(1, "Тест"))
            );
        }

        @Test
        @DisplayName("Создание списка с отрицательной емкостью")
        void testNegativeCapacity() {
            assertThrows(IllegalArgumentException.class,
                    () -> new MyArrayList<>(-5));
        }
    }

    @Nested
    @DisplayName("Тесты расширения массива")
    class CapacityTests {

        @Test
        @DisplayName("Автоматическое расширение при добавлении элементов")
        void testAutoExpansion() {
            MyArrayList<Integer> numbers = new MyArrayList<>(2);

            numbers.add(10);
            numbers.add(20);
            assertEquals(2, numbers.size());

            // Этот add должен вызвать расширение
            numbers.add(30);

            assertEquals(3, numbers.size());
            assertEquals(10, numbers.get(0));
            assertEquals(20, numbers.get(1));
            assertEquals(30, numbers.get(2));

            // Добавляем еще
            numbers.add(40);
            numbers.add(50);

            assertEquals(5, numbers.size());
            assertEquals(40, numbers.get(3));
            assertEquals(50, numbers.get(4));
        }
    }

    @Nested
    @DisplayName("Тесты с автомобилями")
    class CarTests {

        @Test
        @DisplayName("Работа с автомобилями")
        void testWithCars() {
            MyArrayList<Car> carList = new MyArrayList<>();

            Car toyota = new Car.Builder().model("Toyota").power(150).year(2020).build();
            Car honda = new Car.Builder().model("Honda").power(120).year(2019).build();
            Car bmw = new Car.Builder().model("BMW").power(200).year(2021).build();

            carList.add(toyota);
            carList.add(honda);
            carList.add(bmw);

            assertEquals(3, carList.size());
            assertEquals(toyota, carList.get(0));
            assertEquals(honda, carList.get(1));
            assertEquals(bmw, carList.get(2));
        }

        @Test
        @DisplayName("Изменение автомобиля")
        void testModifyCar() {
            MyArrayList<Car> carList = new MyArrayList<>();

            Car oldCar = new Car.Builder().model("Toyota").power(150).year(2020).build();
            carList.add(oldCar);

            Car newCar = new Car.Builder().model("Toyota").power(160).year(2020).build();
            Car returned = carList.set(0, newCar);

            assertEquals(oldCar, returned);
            assertEquals(newCar, carList.get(0));
            assertEquals(160, carList.get(0).getPower());
        }
    }
}
