package org.AstonStudy.collection;

import org.AstonStudy.model.Car;
import org.junit.jupiter.api.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    private MyArrayList<String> list;
    private MyArrayList<Car> carList;
    private Car car1, car2, car3;

    @BeforeEach
    void setUp() {
        list = new MyArrayList<>();
        carList = new MyArrayList<>();
        car1 = new Car.Builder().power(150).model("AA").year(2010).build();
        car2 = new Car.Builder().power(200).model("BB").year(2012).build();
        car3 = new Car.Builder().power(180).model("CC").year(2011).build();
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
        private Car carA, carB, carC;

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

        @Test
        @DisplayName("clearTest")
        void clearTest() {
            MyArrayList<String> list = new MyArrayList<>();
            list.add("A");
            list.add("B");
            list.add("C");
            list.clear();
            assertEquals(0, list.size());
            assertTrue(list.isEmpty());
        }

        @Test
        @DisplayName("addAllTest")
        void addAllTest() {
            MyArrayList<String> list = new MyArrayList<>();
            List<String> ALPHABET = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                    "S", "T", "U", "V", "W", "X", "Y", "Z");
            List<String> alphabet = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
                    "s", "t", "u", "v", "w", "x", "y", "z");

            list.addAll(ALPHABET);
            list.addAll(alphabet);
            assertEquals(52, list.size());
            assertFalse(list.isEmpty());
        }

        @Test
        @DisplayName("toArray()")
        void toArray() {
            MyArrayList<String> list = new MyArrayList<>();
            list.add("A");
            list.add("B");
            Object[] objects = list.toArray();
            assertEquals("A", objects[0]);
            assertEquals("B", objects[1]);
        }

        @Test
        @DisplayName("toArray(T1[]a)")
        void toArrayT1() {
            MyArrayList<String> list = new MyArrayList<>();
            list.add("A");
            list.add("B");

            String[] stringArray = list.toArray(new String[0]);
            assertEquals("A", stringArray[0]);
            assertEquals("B", stringArray[1]);
            assertEquals(2, stringArray.length);

            String[] bigArray = new String[5];
            String[] result = list.toArray(bigArray);
            assertArrayEquals(bigArray, result);
            assertEquals("A", bigArray[0]);
            assertEquals("B", bigArray[1]);
            for (int i = 2; i < 5; i++) {
                assertNull(bigArray[i]);
            }
        }
    }

    @Nested
    @DisplayName("Тесты remove(Object)")
    class RemoveObjectTests {

        @Test
        @DisplayName("Удаление существующего элемента")
        void testRemoveExistingElement() {
            list.add("A");
            list.add("B");
            list.add("C");

            boolean removed = list.remove("B");

            assertTrue(removed);
            assertEquals(2, list.size());
            assertEquals("A", list.get(0));
            assertEquals("C", list.get(1));
        }

        @Test
        @DisplayName("Удаление первого вхождения")
        void testRemoveFirstOccurrence() {
            list.add("A");
            list.add("B");
            list.add("A");
            list.add("C");

            boolean removed = list.remove("A");

            assertTrue(removed);
            assertEquals(3, list.size());
            assertEquals("B", list.get(0));
            assertEquals("A", list.get(1));
            assertEquals("C", list.get(2));
        }

        @Test
        @DisplayName("Удаление null")
        void testRemoveNull() {
            list.add("A");
            list.add(null);
            list.add("B");

            boolean removed = list.remove(null);

            assertTrue(removed);
            assertEquals(2, list.size());
            assertEquals("A", list.get(0));
            assertEquals("B", list.get(1));
        }

        @Test
        @DisplayName("Удаление несуществующего элемента")
        void testRemoveNonExisting() {
            list.add("A");
            list.add("B");

            boolean removed = list.remove("X");

            assertFalse(removed);
            assertEquals(2, list.size());
        }

        @Test
        @DisplayName("Удаление из пустого списка")
        void testRemoveFromEmpty() {
            boolean removed = list.remove("A");
            assertFalse(removed);
        }

        @Test
        @DisplayName("Удаление автомобиля")
        void testRemoveCar() {
            carList.add(car1);
            carList.add(car2);
            carList.add(car3);

            boolean removed = carList.remove(car2);

            assertTrue(removed);
            assertEquals(2, carList.size());
            assertEquals(car1, carList.get(0));
            assertEquals(car3, carList.get(1));
        }
    }

    @Nested
    @DisplayName("Тесты addAll(int, Collection)")
    class AddAllAtIndexTests {

        @Test
        @DisplayName("Добавление в начало списка")
        void testAddAllAtBeginning() {
            list.add("C");
            list.add("D");
            Collection<String> toAdd = List.of("A", "B");

            boolean changed = list.addAll(0, toAdd);

            assertTrue(changed);
            assertEquals(4, list.size());
            assertEquals("A", list.get(0));
            assertEquals("B", list.get(1));
            assertEquals("C", list.get(2));
            assertEquals("D", list.get(3));
        }

        @Test
        @DisplayName("Добавление в середину списка")
        void testAddAllInMiddle() {
            list.add("A");
            list.add("D");
            Collection<String> toAdd = List.of("B", "C");

            boolean changed = list.addAll(1, toAdd);

            assertTrue(changed);
            assertEquals(4, list.size());
            assertEquals("A", list.get(0));
            assertEquals("B", list.get(1));
            assertEquals("C", list.get(2));
            assertEquals("D", list.get(3));
        }

        @Test
        @DisplayName("Добавление в конец списка")
        void testAddAllAtEnd() {
            list.add("A");
            list.add("B");
            Collection<String> toAdd = List.of("C", "D");

            boolean changed = list.addAll(2, toAdd);

            assertTrue(changed);
            assertEquals(4, list.size());
            assertEquals("A", list.get(0));
            assertEquals("B", list.get(1));
            assertEquals("C", list.get(2));
            assertEquals("D", list.get(3));
        }

        @Test
        @DisplayName("Добавление пустой коллекции")
        void testAddAllEmpty() {
            list.add("A");
            Collection<String> empty = Collections.emptyList();

            boolean changed = list.addAll(0, empty);

            assertFalse(changed);
            assertEquals(1, list.size());
        }

        @Test
        @DisplayName("Добавление с неверным индексом (отрицательный)")
        void testAddAllInvalidIndexNegative() {
            Collection<String> toAdd = List.of("A");
            assertThrows(IndexOutOfBoundsException.class,
                    () -> list.addAll(-1, toAdd));
        }

        @Test
        @DisplayName("Добавление с неверным индексом (больше размера)")
        void testAddAllInvalidIndexTooLarge() {
            list.add("A");
            Collection<String> toAdd = List.of("B");
            assertThrows(IndexOutOfBoundsException.class,
                    () -> list.addAll(2, toAdd));
        }

        @Test
        @DisplayName("Добавление null коллекции")
        void testAddAllNullCollection() {
            assertThrows(NullPointerException.class,
                    () -> list.addAll(0, null));
        }

        @Test
        @DisplayName("Проверка расширения массива при добавлении")
        void testAddAllEnsureCapacity() {
            MyArrayList<Integer> numbers = new MyArrayList<>(2);
            numbers.add(1);
            numbers.add(2);
            Collection<Integer> toAdd = List.of(3, 4, 5);

            numbers.addAll(2, toAdd);

            assertEquals(5, numbers.size());
            assertEquals(1, numbers.get(0));
            assertEquals(2, numbers.get(1));
            assertEquals(3, numbers.get(2));
            assertEquals(4, numbers.get(3));
            assertEquals(5, numbers.get(4));
        }

        @Test
        @DisplayName("Добавление автомобилей")
        void testAddAllCars() {
            carList.add(car1);
            Collection<Car> toAdd = List.of(car2, car3);

            boolean changed = carList.addAll(1, toAdd);

            assertTrue(changed);
            assertEquals(3, carList.size());
            assertEquals(car1, carList.get(0));
            assertEquals(car2, carList.get(1));
            assertEquals(car3, carList.get(2));
        }
    }

    @Nested
    @DisplayName("Тесты removeAll(Collection)")
    class RemoveAllTests {

        @Test
        @DisplayName("Удаление нескольких элементов")
        void testRemoveAll() {
            list.add("A");
            list.add("B");
            list.add("C");
            list.add("B");
            list.add("D");
            Collection<String> toRemove = List.of("B", "D");

            boolean changed = list.removeAll(toRemove);

            assertTrue(changed);
            assertEquals(2, list.size());
            assertEquals("A", list.get(0));
            assertEquals("C", list.get(1));
        }

        @Test
        @DisplayName("Удаление всех элементов")
        void testRemoveAllEverything() {
            list.add("A");
            list.add("B");
            list.add("C");
            Collection<String> toRemove = List.of("A", "B", "C");

            boolean changed = list.removeAll(toRemove);

            assertTrue(changed);
            assertTrue(list.isEmpty());
        }

        @Test
        @DisplayName("Удаление элементов, которых нет в списке")
        void testRemoveAllNonExisting() {
            list.add("A");
            list.add("B");
            Collection<String> toRemove = List.of("X", "Y");

            boolean changed = list.removeAll(toRemove);

            assertFalse(changed);
            assertEquals(2, list.size());
        }

        @Test
        @DisplayName("Удаление с пустой коллекцией")
        void testRemoveAllEmpty() {
            list.add("A");
            Collection<String> empty = Collections.emptyList();

            boolean changed = list.removeAll(empty);

            assertFalse(changed);
            assertEquals(1, list.size());
        }

        @Test
        @DisplayName("Удаление с null коллекцией")
        void testRemoveAllNull() {
            assertThrows(NullPointerException.class,
                    () -> list.removeAll(null));
        }

        @Test
        @DisplayName("Удаление дубликатов")
        void testRemoveAllDuplicates() {
            list.add("A");
            list.add("B");
            list.add("A");
            list.add("C");
            list.add("A");
            Collection<String> toRemove = List.of("A");

            boolean changed = list.removeAll(toRemove);

            assertTrue(changed);
            assertEquals(2, list.size());
            assertEquals("B", list.get(0));
            assertEquals("C", list.get(1));
        }

        @Test
        @DisplayName("Удаление автомобилей")
        void testRemoveAllCars() {
            carList.add(car1);
            carList.add(car2);
            carList.add(car3);
            carList.add(car2);
            Collection<Car> toRemove = List.of(car2, car3);

            boolean changed = carList.removeAll(toRemove);

            assertTrue(changed);
            assertEquals(1, carList.size());
            assertEquals(car1, carList.get(0));
        }
    }

    @Nested
    @DisplayName("Тесты retainAll(Collection)")
    class RetainAllTests {

        @Test
        @DisplayName("Оставить только указанные элементы")
        void testRetainAll() {
            list.add("A");
            list.add("B");
            list.add("C");
            list.add("B");
            list.add("D");
            Collection<String> toRetain = List.of("B", "D");

            boolean changed = list.retainAll(toRetain);

            assertTrue(changed);
            assertEquals(3, list.size());
            assertEquals("B", list.get(0));
            assertEquals("B", list.get(1));
            assertEquals("D", list.get(2));
        }

        @Test
        @DisplayName("Пустая коллекция для retainAll удаляет все элементы")
        void testRetainAllEmpty() {
            list.add("A");
            list.add("B");
            list.add("C");
            Collection<String> empty = Collections.emptyList();

            boolean changed = list.retainAll(empty);

            assertTrue(changed);
            assertTrue(list.isEmpty());
        }

        @Test
        @DisplayName("Коллекция содержит все элементы (изменений нет)")
        void testRetainAllAllPresent() {
            list.add("A");
            list.add("B");
            list.add("C");
            Collection<String> toRetain = List.of("A", "B", "C");

            boolean changed = list.retainAll(toRetain);

            assertFalse(changed);
            assertEquals(3, list.size());
        }

        @Test
        @DisplayName("Коллекция содержит больше элементов, чем список")
        void testRetainAllSuperset() {
            list.add("A");
            list.add("B");
            Collection<String> toRetain = List.of("A", "B", "C", "D");

            boolean changed = list.retainAll(toRetain);

            assertFalse(changed); // ничего не удалили
            assertEquals(2, list.size());
        }

        @Test
        @DisplayName("Удаление всех элементов, кроме одного")
        void testRetainAllSingle() {
            list.add("A");
            list.add("B");
            list.add("C");
            Collection<String> toRetain = List.of("B");

            boolean changed = list.retainAll(toRetain);

            assertTrue(changed);
            assertEquals(1, list.size());
            assertEquals("B", list.get(0));
        }

        @Test
        @DisplayName("retainAll с null коллекцией")
        void testRetainAllNull() {
            assertThrows(NullPointerException.class,
                    () -> list.retainAll(null));
        }

        @Test
        @DisplayName("retainAll с автомобилями")
        void testRetainAllCars() {
            carList.add(car1);
            carList.add(car2);
            carList.add(car3);
            carList.add(car2);
            Collection<Car> toRetain = List.of(car1, car2);

            boolean changed = carList.retainAll(toRetain);

            assertTrue(changed);
            assertEquals(3, carList.size());
            assertEquals(car1, carList.get(0));
            assertEquals(car2, carList.get(1));
            assertEquals(car2, carList.get(2));
        }
    }
}