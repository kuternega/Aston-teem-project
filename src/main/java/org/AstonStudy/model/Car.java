package org.AstonStudy.model;

import java.util.Comparator;
import java.util.Objects;

public class Car {
    private final int power;
    private final String model;
    private final int year;

    private Car(Builder builder) {
        this.power = builder.power;
        this.model = builder.model;
        this.year = builder.year;
    }

    public static Car createNewCar(String model, String year, String power) {
        return new Builder()
                .model(model)
                .power(Integer.parseInt(power))
                .year(Integer.parseInt(year))
                .build();
    }
    public static Comparator<Car> byPower() {
        return (car1, car2) -> Integer.compare(car1.power, car2.power);
    }

    public static Comparator<Car> byModel() {
            return (car1, car2) -> car1.model.compareTo(car2.model);
    }

    public static Comparator<Car> byYear() {
        return (car1, car2) -> Integer.compare(car1.year, car2.year);
    }

    public int getPower() {
        return power;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return String.format("Car{power=%d, model='%s', year=%d}",
                power, model, year);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Car car = (Car) object;
        return power == car.power &&
                year == car.year &&
                Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(power, model, year);
    }

    public static class Builder {
        private int power;
        private String model;
        private int year;

        public Builder() {
            // Значения по умолчанию
            this.power = 123;
            this.year = 2015;
        }

        public Builder power(int power) {
            this.power = power;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Car build() {
            validate();
            return new Car(this);
        }

        private void validate() {
            validatePower();
            validateModel();
            validateYear();
        }

        private void validatePower() {
            if (power <= 0) {
                throw new IllegalArgumentException(
                        "Ошибка: мощность должна быть положительной, получено: " + power);
            }
            if (power > 2000) {
                throw new IllegalArgumentException(
                        "Ошибка: мощность не может превышать 2000 л.с., получено: " + power);
            }

            if (power < 50) {
                throw new IllegalArgumentException(
                        "Ошибка: мощность не может быть меньше 50 л.с., получено: " + power);
            }
        }

        private void validateModel() {
            if (model == null) {
                throw new IllegalArgumentException(
                        "Ошибка: модель не может быть null");
            }
            if (model.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Ошибка: модель не может быть пустой");
            }
            if (model.trim().length() < 2) {
                throw new IllegalArgumentException(
                        "Ошибка: модель должна содержать минимум 2 символа, получено: " + model);
            }

            if (model.matches(".*\\d.*")) {
                throw new IllegalArgumentException(
                        "Ошибка: модель не должна содержать цифры: " + model);
            }
        }

        private void validateYear() {
            int currentYear = java.time.Year.now().getValue();

            if (year < 1980) {
                throw new IllegalArgumentException(
                        "Ошибка: год не может быть раньше 1970, получено: " + year);
            }
            if (year > currentYear + 1) {
                throw new IllegalArgumentException(
                        "Ошибка: год не может быть больше " + (currentYear + 1) +
                                ", получено: " + year);
            }
            if (year > currentYear) {
                System.out.println("Ошибка: указан будущий год: " + year);
            }
        }
    }
}
