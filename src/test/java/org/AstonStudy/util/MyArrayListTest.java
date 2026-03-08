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

    @Nested
    @DisplayName("Тесты методов поиска indexOf, lastIndexOf, contains")
    class SearchMethodsTests {

        private MyArrayList<Car> carList;
        private Car carA, carB, carC, nonExistent;

        @BeforeEach
        void setUp() {
            carList = new MyArrayList<>();
            carA = new Car.Builder().power(150).model("AA").year(2010).build();
            carB = new Car.Builder().power(200).model("BB").year(2012).build();
            carC = new Car.Builder().power(180).model("CC").year(2011).build();

            carList.add(carA);
            carList.add(carB);
            carList.add(carA);
            carList.add(null);
        }

        @Test
        @DisplayName("indexOf возвращает первое вхождение или -1")
        void testIndexOf() {
            assertEquals(0, carList.indexOf(carA));
            assertEquals(1, carList.indexOf(carB));
            assertEquals(-1, carList.indexOf(carC));
            assertEquals(3, carList.indexOf(null));
        }

        @Test
        @DisplayName("lastIndexOf возвращает последнее вхождение или -1")
        void testLastIndexOf() {
            assertEquals(2, carList.lastIndexOf(carA));
            assertEquals(1, carList.lastIndexOf(carB));
            assertEquals(-1, carList.lastIndexOf(carC));
            assertEquals(3, carList.lastIndexOf(null));
        }

        @Test
        @DisplayName("contains возвращает true, если элемент присутствует")
        void testContains() {
            assertTrue(carList.contains(carA));
            assertTrue(carList.contains(carB));
            assertFalse(carList.contains(carC));
            assertTrue(carList.contains(null));
        }
    }

    @Nested
    @DisplayName("Тесты методов add(index, element) и remove(index)")
    class IndexedOperationsTests {

        private MyArrayList<String> list;

        @BeforeEach
        void setUp() {
            list = new MyArrayList<>();
            list.add("A");
            list.add("B");
            list.add("C");
        }

        @Test
        @DisplayName("add в начало")
        void addAtBeginning() {
            list.add(0, "X");
            assertEquals(4, list.size());
            assertAll(
                    () -> assertEquals("X", list.get(0)),
                    () -> assertEquals("A", list.get(1)),
                    () -> assertEquals("B", list.get(2)),
                    () -> assertEquals("C", list.get(3))
            );
        }

        @Test
        @DisplayName("add в середину")
        void addAtMiddle() {
            list.add(2, "Y");
            assertEquals(4, list.size());
            assertAll(
                    () -> assertEquals("A", list.get(0)),
                    () -> assertEquals("B", list.get(1)),
                    () -> assertEquals("Y", list.get(2)),
                    () -> assertEquals("C", list.get(3))
            );
        }

        @Test
        @DisplayName("add в конец")
        void addAtEnd() {
            list.add(3, "Z");
            assertEquals(4, list.size());
            assertAll(
                    () -> assertEquals("A", list.get(0)),
                    () -> assertEquals("B", list.get(1)),
                    () -> assertEquals("C", list.get(2)),
                    () -> assertEquals("Z", list.get(3))
            );
        }

        @Test
        @DisplayName("add с неверным индексом")
        void addWithInvalidIndex() {
            assertAll(
                    () -> assertThrows(IndexOutOfBoundsException.class,
                            () -> list.add(-1, "Invalid")),
                    () -> assertThrows(IndexOutOfBoundsException.class,
                            () -> list.add(4, "Invalid"))
            );
        }

        @Test
        @DisplayName("remove из начала")
        void removeFromBeginning() {
            String removed = list.remove(0);
            assertEquals("A", removed);
            assertEquals(2, list.size());
            assertAll(
                    () -> assertEquals("B", list.get(0)),
                    () -> assertEquals("C", list.get(1))
            );
        }

        @Test
        @DisplayName("remove из середины")
        void removeFromMiddle() {
            String removed = list.remove(1);
            assertEquals("B", removed);
            assertEquals(2, list.size());
            assertAll(
                    () -> assertEquals("A", list.get(0)),
                    () -> assertEquals("C", list.get(1))
            );
        }

        @Test
        @DisplayName("remove из конца")
        void removeFromEnd() {
            String removed = list.remove(2);
            assertEquals("C", removed);
            assertEquals(2, list.size());
            assertAll(
                    () -> assertEquals("A", list.get(0)),
                    () -> assertEquals("B", list.get(1))
            );
        }

        @Test
        @DisplayName("remove с неверным индексом")
        void removeWithInvalidIndex() {
            assertAll(
                    () -> assertThrows(IndexOutOfBoundsException.class,
                            () -> list.remove(-1)),
                    () -> assertThrows(IndexOutOfBoundsException.class,
                            () -> list.remove(3))
            );
        }

        @Test
        @DisplayName("remove из пустого списка")
        void removeFromEmpty() {
            MyArrayList<String> empty = new MyArrayList<>();
            assertThrows(IndexOutOfBoundsException.class,
                    () -> empty.remove(0));
        }
    }
}