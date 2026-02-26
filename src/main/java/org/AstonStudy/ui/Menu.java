package org.AstonStudy.ui;

import java.util.Scanner;

public static class Menu {
    public static Language language = Language.ENGLISH;
    public static FieldOfSort fieldOfSort = FieldOfSort.POWER;
    public static SortAlgo sortAlgo = SortAlgo.BUBBLE_SORT;

    public static void showMenu() {
        int choice = 0;
        do {
            Scanner in = new Scanner(System.in);
            System.out.clear();
            String menuEng = """
                1.Fill collection
                2.Choose field of sort
                3.Choose sorting algorithm
                4.Sort
                5.Write collection to file
                6.Count the number of occurrences of an element
                7.Quit
                """;
            String menuRus = """
                1.Заполнить коллекцию
                2.Выбрать поле для сортировки
                3.Выбрать алгоритм сортировки
                4.Отсортировать
                5.Записать коллекцию в файл
                6.Посчитать количество вхождений элемента
                7.Выход
                """;
            System.out.println(language == Language.ENGLISH ? menuEng : menuRus);
            choice = in.nextInt();
            in.close();
            switch (choice) {
                case 1 -> showFillByMenu();
                case 2 -> showFieldOfSortMenu();
                case 3 -> showSortAlgoMenu();
                case 4 -> System.out.println("N/a"); //Вызвать выбранный алгоритм сортировки по выбранному полю
                case 5 -> System.out.println("N/a"); //Вызвать метод, который запишет коллекцию в файл
                case 6 -> System.out.println("N/a"); //Вызвать метод, который посчитает количество вхождений элемента (элемент задается пользователем)
                case 7 -> System.out.println(language == Language.ENGLISH ? "Application has been shut down" : "Приложение было закрыто");
                default -> System.out.println(language == Language.ENGLISH ? "Wrong choice" : "Неверный выбор");
            }
        } while (choice != 7);
    }

    public static void showFillByMenu() {
        int choiceFillMenu = 0;
        do {
            Scanner in = new Scanner(System.in);
            System.out.clear();
            String fillMenuEng = """
                1.File
                2.Random
                3.Manually
                4.Back to menu
                """;
            String fillMenuRus = """
                1.Файл
                2.Случайно
                3.Вручную
                4.Назад в меню
                """;
            System.out.println(language == Language.ENGLISH ? fillMenuEng : fillMenuRus);
            choiceFillMenu = in.nextInt();
            in.close();
            switch (choiceFillMenu) {
                case 1 -> System.out.println("N/a"); //Вызвать метод, который заполнит коллекцию из файла
                case 2 -> System.out.println("N/a"); //Вызвать метод, который заполнит коллекцию случайными элементами
                case 3 -> System.out.println("N/a"); //Вызвать метод, который заполнит коллекцию элементами, введенными пользователем
                case 4 -> break;
                default -> System.out.println(language == Language.ENGLISH ? "Wrong choice" : "Неверный выбор");
            }
        } while (choiceFillMenu != 4);
    }

    public static void showFieldOfSortMenu() {
        int choiceFieldOfSort = 0;
        do {
            Scanner in = new Scanner(System.in);
            System.out.clear();
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
            in.close();
            switch (choiceFieldOfSort) {
                case 1 -> fieldOfSort = FieldOfSort.POWER;
                case 2 -> fieldOfSort = FieldOfSort.MODEL;
                case 3 -> fieldOfSort = FieldOfSort.YEAR;
                case 4 -> break;
                default -> System.out.println(language == Language.ENGLISH ? "Wrong choice" : "Неверный выбор");
            }
        } while (choiceFieldOfSort != 4);
    }

    public static void showSortAlgoMenu() {
        int choiceSortAlgo = 0;
        do {
            Scanner in = new Scanner(System.in);
            System.out.clear();
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
            in.close();
            switch (choiceSortAlgo) {
                case 1 -> sortAlgo = SortAlgo.BUBBLE_SORT;
                case 2 -> sortAlgo = SortAlgo.INSERT_SORT;
                case 3 -> break;
                default -> System.out.println(language == Language.ENGLISH ? "Wrong choice" : "Неверный выбор");
            }
        } while (choiceSortAlgo != 3);
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