package org.AstonStudy;

import java.util.Objects;

public class Car {
    private final int power;
    private final String model;
    private final int year;

    public Car(Builder builder) {
        this.power = builder.power;
        this.model = builder.model;
        this.year = builder.year;
    }

    public Car(String model, String year, String power) {
        this.model = model;
        this.year = Integer.parseInt(year);
        this.power = Integer.parseInt(power);
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
                        "Ошибка: модель должна содержать минимум 2 символа");
            }
        }

        private void validateYear() {
            int currentYear = java.time.Year.now().getValue();

            if (year < 1980) {
                throw new IllegalArgumentException(
                        "Ошибка: год не может быть раньше 1980, получено: " + year);
            }
            if (year > currentYear + 1) {
                throw new IllegalArgumentException(
                        "Ошибка: год не может быть больше " + (currentYear + 1) +
                                ", получено: " + year);
            }
        }
    }
}