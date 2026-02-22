package org.AstonStudy;

import org.AstonStudy.ClassLoader.DefaultClassLoader;
import org.AstonStudy.ClassLoader.EditedClassLoader;
import org.AstonStudy.ClassLoader.NewClassLoader;

import java.util.List;

//TIP Для <b>запуска</b> кода нажмите <shortcut actionId="Run"/> или
// щелкните значок <icon src="AllIcons.Actions.Execute"/> в боковой области.
public class Main {
    static void main() {
        //TIP Нажмите <shortcut actionId="ShowIntentionActions"/>, поместив каретку на выделенный текст
        // чтобы увидеть, как IntelliJ IDEA предлагает исправить это.
        IO.println(String.format("Hello and welcome!"));

        for (int i = 1; i <= 5; i++) {
            //TIP Нажмите <shortcut actionId="Debug"/>, чтобы начать отладку кода. Мы установили одну <icon src="AllIcons.Debugger.Db_set_breakpoint"/> точку останова
            // для вас, но вы всегда можете добавить еще, нажав <shortcut actionId="ToggleLineBreakpoint"/>.
            IO.println("i = " + i);
        }


        DefaultClassLoader def = new DefaultClassLoader();
        def.load();
        List<Car> records = def.getCarRecord();
        if (records.isEmpty()) {
            System.out.println("Записей не загружено. Проверьте файл ClassLoader/DefaultClassLoaderFile");
        } else {
            System.out.println("Загружено записей: " + records.size());
            for (Car car : records) {
                System.out.println(car.getModel() + " | " + car.getYear() + " | " + car.getPower());
            }
        }

        EditedClassLoader edit = new EditedClassLoader();
        edit.load();
        List<Car> editedRecords = edit.getCarRecord();
        if (editedRecords.isEmpty()) {
            System.out.println("Записей не загружено. Проверьте файл ClassLoader/EditedClassLoaderFile");
        } else {
            System.out.println("Загружено записей: " + editedRecords.size());
            for (Car car : editedRecords) {
                System.out.println(car.getModel() + " | " + car.getYear() + " | " + car.getPower());
            }
        }

        NewClassLoader newClassLoader = new NewClassLoader();
        newClassLoader.load();
        List<Car> newRecords = newClassLoader.getCarRecord();
        if (newRecords.isEmpty()) {
            System.out.println("Записей не загружено. Проверьте файл ClassLoader/EditedClassLoaderFile");
        } else {
            System.out.println("Загружено записей: " + newRecords.size());
            for (Car car : newRecords) {
                System.out.println(car.getModel() + " | " + car.getYear() + " | " + car.getPower());
            }
        }
    }
}
