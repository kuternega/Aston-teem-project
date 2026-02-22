package org.AstonStudy.ClassLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DefaultClassLoader implements ClassLoader {
    private List<Record> dataRecord = new ArrayList<>();

    public List<Record> getDataRecord() {
        return dataRecord;
    }

    @Override
    public void load() {
        Path path = Paths.get("src/main/java/org/AstonStudy/ClassLoader/", "DefaultClassLoaderFile");

        try {
            List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i += 3) {
                String name = lines.get(i).substring(10).trim();
                String something1 = lines.get(i+1).substring(9).trim();
                String something2 = lines.get(i+2).substring(9).trim();
                dataRecord.add(new Record(name, something1, something2));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
