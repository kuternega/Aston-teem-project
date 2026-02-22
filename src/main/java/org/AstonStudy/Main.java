package org.AstonStudy;

import org.AstonStudy.ClassLoader.DefaultClassLoader;
import org.AstonStudy.ClassLoader.EditedClassLoader;
import org.AstonStudy.ClassLoader.NewClassLoader;
import org.AstonStudy.ClassLoader.Record;

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
        List<Record> records = def.getDataRecord();
        if (records.isEmpty()) {
            System.out.println("Записей не загружено. Проверьте файл ClassLoader/DefaultClassLoaderFile");
        } else {
            System.out.println("Загружено записей: " + records.size());
            for (Record r : records) {
                System.out.println(r.getName() + " | " + r.getSomething1() + " | " + r.getSomething2());
            }
        }

        EditedClassLoader edit = new EditedClassLoader();
        edit.load();
        List<Record> editedRecords = edit.getDataRecord();
        if (editedRecords.isEmpty()) {
            System.out.println("Записей не загружено. Проверьте файл ClassLoader/EditedClassLoaderFile");
        } else {
            System.out.println("Загружено записей: " + editedRecords.size());
            for (Record r : editedRecords) {
                System.out.println(r.getName() + " | " + r.getSomething1() + " | " + r.getSomething2());
            }
        }

        NewClassLoader newClassLoader = new NewClassLoader();
        newClassLoader.load();
        List<Record> newRecords = newClassLoader.getDataRecord();
        if (newRecords.isEmpty()) {
            System.out.println("Записей не загружено. Проверьте файл ClassLoader/EditedClassLoaderFile");
        } else {
            System.out.println("Загружено записей: " + newRecords.size());
            for (Record r : newRecords) {
                System.out.println(r.getName() + " | " + r.getSomething1() + " | " + r.getSomething2());
            }
        }
    }
}
