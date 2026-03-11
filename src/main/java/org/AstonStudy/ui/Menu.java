package org.AstonStudy.ui;

import org.AstonStudy.collection.MyArrayList;
import org.AstonStudy.data.DataProvider;
import org.AstonStudy.model.Car;
import org.AstonStudy.sorting.BubbleSortStrategy;
import org.AstonStudy.sorting.InsertionSortStrategy;
import org.AstonStudy.sorting.OddEvenSorter;
import org.AstonStudy.sorting.SortingStrategy;
import org.AstonStudy.util.FileHelper;
import org.AstonStudy.util.MultiThreadCounter;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static Language language = Language.ENGLISH;
    public static FieldOfSort fieldOfSort = FieldOfSort.POWER;
    public static SortAlgo sortAlgo = SortAlgo.BUBBLE_SORT;
    public static Collection collection = null;
    public static Scanner in = new Scanner(System.in);

    public static void showMenu() {
        int choice = 0;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            String menuEng = """
                1.Fill collection
                2.Choose field of sort
                3.Choose sorting algorithm
                4.Sort
                5.Odd-even sort
                6.Write collection to file
                7.Count the number of occurrences of an element
                8.Select language
                9.Quit
                """;
            String menuRus = """
                1.Заполнить коллекцию
                2.Выбрать поле для сортировки
                3.Выбрать алгоритм сортировки
                4.Отсортировать
                5.Четно-нечетная сортировка
                6.Записать коллекцию в файл
                7.Посчитать количество вхождений элемента
                8.Выбор языка
                9.Выход
                """;
            System.out.println(language == Language.ENGLISH ? menuEng : menuRus);
            choice = in.nextInt();
            switch (choice) {
                case 1 -> showFillByMenu();
                case 2 -> showFieldOfSortMenu();
                case 3 -> showSortAlgoMenu();
                case 4 -> {
                    if (collection == null) {
                        System.out.println(language == Language.ENGLISH ? "Collection is empty" : "Коллекция пустая");
                        break;
                    }
                    SortingStrategy<Car> sortingStrategy;
                    if (sortAlgo == SortAlgo.BUBBLE_SORT) {
                        sortingStrategy = new BubbleSortStrategy<>();
                    } else {
                        sortingStrategy = new InsertionSortStrategy<>();
                    }
                    if (fieldOfSort == FieldOfSort.MODEL) {
                        sortingStrategy.sort((List<Car>) collection, Car.byModel());
                    } else if (fieldOfSort == FieldOfSort.POWER) {
                        sortingStrategy.sort((List<Car>) collection, Car.byPower());
                    }
                    else {
                        sortingStrategy.sort((List<Car>) collection, Car.byYear());
                    }
                } //Вызвать выбранный алгоритм сортировки по выбранному полю
                case 5 -> {
                    SortingStrategy<Car> sortingStrategy;
                    if (sortAlgo == SortAlgo.BUBBLE_SORT) {
                        sortingStrategy = new BubbleSortStrategy<>();
                    } else {
                        sortingStrategy = new InsertionSortStrategy<>();
                    }
                    if (fieldOfSort == FieldOfSort.MODEL) {
                        System.out.println(language == Language.ENGLISH ? "Model can't be even/odd" : "Модель не может быть четной/нечетной");
                    } else if (fieldOfSort == FieldOfSort.POWER) {
                        OddEvenSorter.sortByEven((MyArrayList<Car>) collection, Car::getPower, sortingStrategy, Car.byPower());
                    }
                    else {
                        OddEvenSorter.sortByEven((MyArrayList<Car>) collection, Car::getYear, sortingStrategy, Car.byYear());
                    }
                }
                case 6 -> FileHelper.appendToFile("Collection.txt", collection);
                case 7 -> {
                    System.out.println(MultiThreadCounter.countOccurrences((MyArrayList<Car>) collection, DataProvider.manual(in, 1).getFirst(), 8));
                } //Вызвать метод, который посчитает количество вхождений элемента (элемент задается пользователем)
                case 8 -> showLangSelectionMenu();
                case 9 -> System.out.println(language == Language.ENGLISH ? "Application has been shut down" : "Приложение было закрыто");
                default -> System.out.println(language == Language.ENGLISH ? "Wrong choice" : "Неверный выбор");
            }
        } while (choice != 9);
        in.close();
    }

    public static void showFillByMenu() {
        int choiceFillMenu = 0;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            String fillMenuEng = """
                1.File
                2.Default File
                3.Random
                4.Manually
                5.Back to menu
                """;
            String fillMenuRus = """
                1.Файл
                2.Стандартный Файл
                3.Случайно
                4.Вручную
                5.Назад в меню
                """;
            System.out.println(language == Language.ENGLISH ? fillMenuEng : fillMenuRus);
            choiceFillMenu = in.nextInt();
            switch (choiceFillMenu) {
                case 1 -> {
                    System.out.println("Введите путь к файлу:");
                    String filePath = in.nextLine();
                    collection = DataProvider.fromFile(filePath);
                    choiceFillMenu = 5;
                } //Вызвать метод, который заполнит коллекцию из файла
                case 2 -> {
                    collection = DataProvider.fromFile();
                    choiceFillMenu = 5;
                }
                case 3 -> {
                    System.out.println("Введите количество элементов:");
                    int count = in.nextInt();
                    collection = DataProvider.random(count);
                    choiceFillMenu = 5;
                } //Вызвать метод, который заполнит коллекцию случайными элементами
                case 4 -> {
                    System.out.println("Введите количество элементов:");
                    int count = in.nextInt();
                    System.out.println("Введите значения вручную:");
                    collection = DataProvider.manual(in, count);
                    choiceFillMenu = 5;
                } //Вызвать метод, который заполнит коллекцию элементами, введенными пользователем
                case 5 -> System.out.println("Returning to menu.");
                default -> System.out.println(language == Language.ENGLISH ? "Wrong choice" : "Неверный выбор");
            }
        } while (choiceFillMenu != 5);
    }

    public static void showFieldOfSortMenu() {
        int choiceFieldOfSort = 0;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            String fieldMenuEng = """
                1.Power
                2.Model
                3.Year
                4.Back to menu
                """;
            String fieldMenuRus = """
                1.Мощность
                2.Модель
                3.Год выпуска
                4.Назад в меню
                """;
            System.out.println(language == Language.ENGLISH ? fieldMenuEng : fieldMenuRus);
            choiceFieldOfSort = in.nextInt();
            switch (choiceFieldOfSort) {
                case 1 -> {
                    fieldOfSort = FieldOfSort.POWER;
                    choiceFieldOfSort = 4;
                }
                case 2 -> {
                    fieldOfSort = FieldOfSort.MODEL;
                    choiceFieldOfSort = 4;
                }
                case 3 -> {
                    fieldOfSort = FieldOfSort.YEAR;
                    choiceFieldOfSort = 4;
                }
                case 4 -> System.out.println(language == Language.ENGLISH ? "Returning to menu." : "Возврат в меню.");
                default -> System.out.println(language == Language.ENGLISH ? "Wrong choice" : "Неверный выбор");
            }
        } while (choiceFieldOfSort != 4);
    }

    public static void showSortAlgoMenu() {
        int choiceSortAlgo = 0;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            String sortMenuEng = """
                1.Bubble sort
                2.Insert sort
                3.Back to menu
                """;
            String sortMenuRus = """
                1.Пузырьковая сортировка
                2.Сортировка вставками
                3.Назад в меню
                """;
            System.out.println(language == Language.ENGLISH ? sortMenuEng : sortMenuRus);
            choiceSortAlgo = in.nextInt();
            switch (choiceSortAlgo) {
                case 1 -> {
                    sortAlgo = SortAlgo.BUBBLE_SORT;
                    choiceSortAlgo = 4;
                }
                case 2 -> {
                    sortAlgo = SortAlgo.INSERT_SORT;
                    choiceSortAlgo = 4;
                }
                case 3 -> System.out.println(language == Language.ENGLISH ? "Returning to menu." : "Возврат в меню.");
                default -> System.out.println(language == Language.ENGLISH ? "Wrong choice" : "Неверный выбор");
            }
        } while (choiceSortAlgo != 3);
    }

    public static void showLangSelectionMenu() {
        int choiceLang = 0;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            String langMenuEng = """
                    1.English
                    2.Russian
                    3.Back to menu
                    """;
            String langMenuRus = """
                    1.Английский
                    2.Русский
                    3.Назад в меню.
                    """;
            System.out.println(language == Language.ENGLISH ? langMenuEng : langMenuRus);
            choiceLang = in.nextInt();
            switch (choiceLang) {
                case 1 -> {
                    language = Language.ENGLISH;
                    choiceLang = 3;
                }
                case 2 -> {
                    language = Language.RUSSIAN;
                    choiceLang = 3;
                }
                case 3 -> System.out.println(language == Language.ENGLISH ? "Returning to menu." : "Возврат в меню.");
                default -> System.out.println(language == Language.ENGLISH ? "Wrong choice" : "Неверный выбор");
            }
        } while(choiceLang != 3);
    }

    enum Language {
        RUSSIAN,
        ENGLISH,
    }

    enum FieldOfSort {
        POWER,
        MODEL,
        YEAR
    }

    enum SortAlgo {
        BUBBLE_SORT,
        INSERT_SORT
    }
}