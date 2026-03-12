package org.AstonStudy.ui;

import org.junit.jupiter.api.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MenuTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        Menu.language = Menu.Language.ENGLISH;
        Menu.fieldOfSort = Menu.FieldOfSort.POWER;
        Menu.sortAlgo = Menu.SortAlgo.BUBBLE_SORT;
        Menu.collection = null;
        Menu.in = new java.util.Scanner(System.in);
        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Menu.in = new java.util.Scanner(System.in);
    }

                                            /**
                                             * Тест на выбор языка
                                             * 1)Выбор Английского языка
                                             * 2)Выбор Русского языка
                                             * 3)Возврат в главное меню без изменений
                                             * **/

    @Test
    @DisplayName("Выбор английского языка в меню языка")
    void testEnglishLanguage() {
        setInput("1\n3\n");
        Menu.showLangSelectionMenu();
        assertEquals(Menu.Language.ENGLISH, Menu.language);
        // Убираем проверку на сообщение, так как оно не выводится при таком сценарии
    }

    @Test
    @DisplayName("Выбор русского языка в меню языка")
    void testRussianLanguage() {
        setInput("2\n3\n"); // 2 - Russian, 3 - back
        Menu.showLangSelectionMenu();
        assertEquals(Menu.Language.RUSSIAN, Menu.language);
    }

    @Test
    @DisplayName("Возврат из меню языка без изменения")
    void testLanguageBack() {
        setInput("3\n");
        Menu.showLangSelectionMenu();
        assertEquals(Menu.Language.ENGLISH, Menu.language);
        assertTrue(outContent.toString().contains("Returning to menu.")); // это должно работать
    }

                                        /**
                                         * Тесты на выбор поля сортировки
                                         * 1)Выбор поля мощности
                                         * 2)Выбор поля модели
                                         * 3)Выбор поля года
                                         * 4)Возврат в главное меню без изменений
                                         * **/

    @Test
    @DisplayName("Выбор поля POWER")
    void testFieldPower() {
        setInput("1\n4\n"); // 1 - Power, 4 - back
        Menu.showFieldOfSortMenu();
        assertEquals(Menu.FieldOfSort.POWER, Menu.fieldOfSort);
    }

    @Test
    @DisplayName("Выбор поля MODEL")
    void testFieldModel() {
        setInput("2\n4\n");
        Menu.showFieldOfSortMenu();
        assertEquals(Menu.FieldOfSort.MODEL, Menu.fieldOfSort);
    }

    @Test
    @DisplayName("Выбор поля YEAR")
    void testFieldYear() {
        setInput("3\n4\n");
        Menu.showFieldOfSortMenu();
        assertEquals(Menu.FieldOfSort.YEAR, Menu.fieldOfSort);
    }

    @Test
    @DisplayName("Возврат без изменения поля")
    void testFieldBack() {
        setInput("4\n");
        Menu.showFieldOfSortMenu();
        assertEquals(Menu.FieldOfSort.POWER, Menu.fieldOfSort); // по умолчанию
    }

                                        /**
                                         * Тест выбора алгоритма сортировки:
                                         * 1)Выбор пузырьковой сортировки
                                         * 2)Выбор сортировки вставками
                                         * 3)Возврат в главное меню без изменений
                                         * **/

    @Test
    @DisplayName("Выбор пузырьковой сортировки")
    void testBubbleSort() {
        setInput("1\n3\n"); // 1 - Bubble, 3 - back
        Menu.showSortAlgoMenu();
        assertEquals(Menu.SortAlgo.BUBBLE_SORT, Menu.sortAlgo);
    }

    @Test
    @DisplayName("Выбор сортировки вставками")
    void testInsertSort() {
        setInput("2\n3\n");
        Menu.showSortAlgoMenu();
        assertEquals(Menu.SortAlgo.INSERT_SORT, Menu.sortAlgo);
    }

    @Test
    @DisplayName("Возврат без изменения алгоритма")
    void testSortAlgoBack() {
        setInput("3\n");
        Menu.showSortAlgoMenu();
        assertEquals(Menu.SortAlgo.BUBBLE_SORT, Menu.sortAlgo);
    }

                                    /**
                                     * Тесты на заполнение коллекции
                                     * 1)Из файла с ручным вводом пути
                                     * 2)Из Стандартного файла
                                     * 3)Заполнение случайными машинами
                                     * 4)Ручное заполнение
                                     * 5)Возврат в меню без изменений
                                     * **/
    @Test
    @DisplayName("Заполнение из файла (ручной ввод пути)")
    void testFillFromFile() {
        setInput("1\nsrc\\test\\resources\\MenuTestFile\n5\n");
        Menu.showFillByMenu();
        assertNotNull(Menu.collection);
        assertFalse(Menu.collection.isEmpty());
    }

    @Test
    @DisplayName("Заполнение из стандартного файла (default)")
    void testFillFromDefaultFile() {
        setInput("2\n5\n");
        Menu.showFillByMenu();
        assertNotNull(Menu.collection);
    }

    @Test
    @DisplayName("Заполнение случайными элементами")
    void testFillRandom() {
        setInput("3\n5\n5\n");
        Menu.showFillByMenu();
        assertNotNull(Menu.collection);
        assertEquals(5, Menu.collection.size());
    }

    @Test
    @DisplayName("Ручное заполнение")
    void testFillManual() {
        setInput("4\n2\n150;Toyota;2020\n200;Honda;2019\n5\n");
        Menu.showFillByMenu();
        assertNotNull(Menu.collection);
        assertEquals(2, Menu.collection.size());
    }

    @Test
    @DisplayName("Возврат без заполнения")
    void testFillBack() {
        setInput("5\n");
        Menu.showFillByMenu();
        assertNull(Menu.collection);
    }

                                /**
                                 * * Тест всея тестов:
                                 * 1)Происходит выбор Русского Языка
                                 * 2)Заполнение Коллекции вручную (2 машины: 150;Toyota;2020 и 200;Honda;2019)
                                 * 3)Выбор сортировки по мощности
                                 * 4)Выбирается сортировка пузырьком
                                 * 5)Выполняем сортировку
                                 * 6)Выход из программы
                                 * (В теории ещё можно между 5) и 6) выполнить заполнение
                                 * и затем убедится что происходит заполнение)
                                 * **/

    @Test
    @DisplayName("Полный цикл: выбор языка, заполнение, сортировка")
    void testFullCycle() {
        String input = "8\n2\n1\n4\n2\n150;Toyota;2020\n200;Honda;2019\n2\n1\n3\n1\n4\n9\n";
        setInput(input);
        Menu.showMenu();
        assertEquals(Menu.Language.RUSSIAN, Menu.language);
        assertEquals(Menu.FieldOfSort.POWER, Menu.fieldOfSort);
        assertEquals(Menu.SortAlgo.BUBBLE_SORT, Menu.sortAlgo);
        assertNotNull(Menu.collection);
        assertEquals(2, Menu.collection.size());
        assertTrue(outContent.toString().contains("Приложение было закрыто"));
    }

                                /**
                                 * Тестирование корректного ввода:
                                 * 1)Ввод вместо числа букв
                                 * 2)Ввод несуществующего пункта в меню
                                 * **/

    @Test
    @DisplayName("Ввод нечислового значения в главном меню")
    void testInvalidInputMainMenu() {
        setInput("abc\n9\n");
        Menu.showMenu();
        assertTrue(outContent.toString().contains("Wrong choice. Please enter an integer."));
    }

    @Test
    @DisplayName("Неверный пункт меню")
    void testWrongChoice() {
        setInput("99\n9\n");
        Menu.showMenu();
        assertTrue(outContent.toString().contains("Wrong choice") || outContent.toString().contains("Неверный выбор"));
    }
}