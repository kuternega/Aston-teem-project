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

    public static class Record {
        private final String name;
        private final String something1;
        private final String something2;

        public Record(String name, String something1, String something2) {
            this.name = name;
            this.something1 = something1;
            this.something2 = something2;
        }

        public String getName() { return name; }
        public String getSomething1() { return something1; }
        public String getSomething2() { return something2; }
    }

    @Override
    public void load() {
        Path path = Paths.get("ClassLoader", "DefaultClassLoaderFile");

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
