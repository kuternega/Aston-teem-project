package org.AstonStudy.util;

import org.AstonStudy.model.Car;
import org.junit.jupiter.api.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    @DisplayName("Тест создания объектов через Builder")
    void testCarCreationWithBuilder() {
        Car car = new Car.Builder()
                .model("Toyota")
                .power(150)
                .year(2020)
                .build();

        assertAll("Проверка полей автомобиля",
                () -> assertEquals("Toyota", car.getModel()),
                () -> assertEquals(150, car.getPower()),
                () -> assertEquals(2020, car.getYear())
        );
    }

    @Test
    @DisplayName("Тест создания объектов через фабричный метод")
    void testCarCreationWithFactoryMethod() {
        Car car = Car.createNewCar("Honda", "2021", "180");

        assertAll("Проверка полей автомобиля",
                () -> assertEquals("Honda", car.getModel()),
                () -> assertEquals(180, car.getPower()),
                () -> assertEquals(2021, car.getYear())
        );
    }

    @Test
    @DisplayName("Тест значений по умолчанию")
    void testDefaultValues() {
        Car car = new Car.Builder().model("BMW").build();

        assertAll("Проверка значений по умолчанию",
                () -> assertEquals("BMW", car.getModel()),
                () -> assertEquals(123, car.getPower()),
                () -> assertEquals(2015, car.getYear())
        );
    }

    @Nested
    @DisplayName("Тесты валидации")
    class ValidationTests {

        @Test
        @DisplayName("Отрицательная мощность должна вызывать исключение")
        void testNegativePower() {
            assertThrows(IllegalArgumentException.class, () ->
                    new Car.Builder().model("Test").power(-10).year(2020).build()
            );
        }

        @Test
        @DisplayName("Мощность > 2000 должна вызывать исключение")
        void testTooHighPower() {
            assertThrows(IllegalArgumentException.class, () ->
                    new Car.Builder().model("Test").power(2500).year(2020).build()
            );
        }

        @Test
        @DisplayName("Пустая модель должна вызывать исключение")
        void testEmptyModel() {
            assertThrows(IllegalArgumentException.class, () ->
                    new Car.Builder().model("").power(150).year(2020).build()
            );
        }

        @Test
        @DisplayName("Модель null должна вызывать исключение")
        void testNullModel() {
            assertThrows(IllegalArgumentException.class, () ->
                    new Car.Builder().model(null).power(150).year(2020).build()
            );
        }

        @Test
        @DisplayName("Год < 1980 должен вызывать исключение")
        void testTooOldYear() {
            assertThrows(IllegalArgumentException.class, () ->
                    new Car.Builder().model("Test").power(150).year(1970).build()
            );
        }
    }

    @Nested
    @DisplayName("Тесты компараторов")
    class ComparatorTests {

        private List<Car> cars;

        @BeforeEach
        void setUp() {
            cars = Arrays.asList(
                    new Car.Builder().power(200).model("BMW").year(2021).build(),
                    new Car.Builder().power(180).model("Audi").year(2020).build(),
                    new Car.Builder().power(150).model("Toyota").year(2019).build()

            );
        }

        @Test
        @DisplayName("Сортировка по мощности")
        void testSortByPower() {
            cars.sort(Car.byPower());

            assertEquals(150, cars.get(0).getPower());
            assertEquals(180, cars.get(1).getPower());
            assertEquals(200, cars.get(2).getPower());
        }

        @Test
        @DisplayName("Сортировка по модели")
        void testSortByModel() {
            cars.sort(Car.byModel());

            assertEquals("Audi", cars.get(0).getModel());
            assertEquals("BMW", cars.get(1).getModel());
            assertEquals("Toyota", cars.get(2).getModel());
        }

        @Test
        @DisplayName("Сортировка по году")
        void testSortByYear() {
            cars.sort(Car.byYear());

            assertEquals(2019, cars.get(0).getYear());
            assertEquals(2020, cars.get(1).getYear());
            assertEquals(2021, cars.get(2).getYear());
        }

        @Test
        @DisplayName("Компараторы можно комбинировать")
        void testChainedComparators() {
            // Добавляем машину с такой же мощностью как у Audi, но старше
            cars.add(new Car.Builder().power(180).model("Opel").year(2018).build());

            cars.sort(Car.byPower().thenComparing(Car.byYear()));


            assertEquals(150, cars.get(0).getPower()); // Toyota 150 (2019)
            assertEquals(180, cars.get(1).getPower()); // Opel 180 (2018)
            assertEquals(180, cars.get(2).getPower()); // Audi 180 (2020)
            assertEquals(200, cars.get(3).getPower()); // BMW 200 (2021)
        }
    }
}

