package org.AstonStudy.ClassLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NewClassLoader implements ClassLoader {
    private List<Record> dataRecord = new ArrayList<>();

    public List<Record> getDataRecord() {
        return dataRecord;
    }

    // Загрузчик "Вручную"
    @Override
    public void load() {
        boolean isValid = false;
        while (!isValid) {
            System.out.println("Введите через \",\": Название, Что-то ещё, Что-то ещё. Когда закончите напишите \"Стоп\"");
            Scanner sc = new Scanner(System.in);
            String userAnswer = sc.nextLine();
            if (userAnswer.equalsIgnoreCase("Стоп")) {
                isValid = true;
            } else {
                String[] userClass = userAnswer.split(",");
                dataRecord.add(new Record(userClass[0], userClass[1], userClass[2]));
            }
        }
    }
}
