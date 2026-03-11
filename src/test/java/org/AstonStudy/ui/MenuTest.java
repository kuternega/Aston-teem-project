package org.AstonStudy.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {
    @Test
    @DisplayName("sort")
    void sort() {

    }

    @Test
    @DisplayName("oddEvenSort")
    void oddEvenSort() {

    }

    @Test
    @DisplayName("writeCollectionToFile")
    void writeCollectionToFile() {

    }

    @Test
    @DisplayName("Count the number of occurrences of an element")
    void countTheNumberOfOccurrencesOfAnElement() {

    }

    @Test
    @DisplayName("Quit")
    void Quit() {

    }

    /** Заполнение коллекций:
     * 1)Вызов меню заполнения коллекций
     * 2)Заполнение коллекций из файла
     * 3)Заполнение коллекций рандом методом
     * 4)Ручное заполнение
     * 5)Выход в главное меню**/

    @Test
    @DisplayName("fillCollection")
    void fillCollection() {

    }

    @Test
    @DisplayName("Show fill by menu - File")
    void file() {

    }

    @Test
    @DisplayName("Show fill by menu - Random")
    void random() {

    }

    @Test
    @DisplayName("Show fill by menu - Manually")
    void manual() {

    }

    @Test
    @DisplayName("Back to the menu")
    void backToTheMenu() {

    }

    /** Сортировка машин
     *1)Вызов меню сортировки
     *2)Сортировка по мощности
     *3)Сортировка по модели
     *4)Сортировка по году выпуска
     *5)Выход в главное меню**/

    @Test
    @DisplayName("choseFieldSort")
    void choseFieldSort() {

    }

    @Test
    @DisplayName("Sort by power")
    void power() {

    }

    @Test
    @DisplayName("Sort by model")
    void model() {

    }

    @Test
    @DisplayName("Sort by year")
    void year() {

    }

    @Test
    @DisplayName("Back to the Menu from SortAlgorithm")
    void backToTheMenu2() {

    }

    /** Выбор метода сортировки
     * 1)Выбор меню с алгоритмами сортировки
     * 2)Сортировка Пузырьком
     * 3)Сортировка Вставками
     * 4)Выход в главное меню
     * **/

    @Test
    @DisplayName("choseSortAlgorithm")
    void choseSortAlgorithm() {

    }

    @Test
    @DisplayName("BubbleSort")
    void bubbleSort() {
        
    }

    @Test
    @DisplayName("InsertSort")
    void insertSort() {
        String input = "2\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        Menu.showSortAlgoMenu();
        assertEquals(Menu.SortAlgo.INSERT_SORT, Menu.sortAlgo);
    }

    @Test
    @DisplayName("Back to the menu")
    void backToTheMenu3() {
        String input = "3";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Menu.showSortAlgoMenu();
        assertEquals(Menu.SortAlgo.BUBBLE_SORT, Menu.sortAlgo);
    }

    /** Выбор языка
     * 1)Вход в меню выбора языка
     * 2)Выбор Английского
     * 3)Выбор Русского
     * 4)Назад в меню
     * **/

    @Test
    @DisplayName("choseLanguage")
    void choseLanguage() {
        String input = "8\n2\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Menu.showMenu();
        assertEquals(Menu.Language.RUSSIAN, Menu.language);
    }

    @Test
    @DisplayName("Chose English")
    void english() {
        String input = "1\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Menu.showLangSelectionMenu();
        assertEquals(Menu.Language.ENGLISH, Menu.language);
    }

    @Test
    @DisplayName("Chose Russian")
    void russian() {
        String input = "2\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Menu.showLangSelectionMenu();
        assertEquals(Menu.Language.RUSSIAN, Menu.language);
    }

    @Test
    @DisplayName("Back to the menu")
    void backToTheMenu4() {
        String input = "3";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Menu.showLangSelectionMenu();
        assertEquals(Menu.Language.ENGLISH, Menu.language);
    }
}
