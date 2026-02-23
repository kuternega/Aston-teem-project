package org.AstonStudy.ClassLoader;

import org.AstonStudy.Car;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DefaultClassLoader implements ClassLoader {
    private final List<Car> carRecord = new ArrayList<Car>();

    public List<Car> getCarRecord() {
        return carRecord;
    }

    @Override
    public void load() {
        Path path = Paths.get("src/main/java/org/AstonStudy/ClassLoader/", "DefaultClassLoaderFile");

        try {
            List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i += 3) {
                String model = lines.get(i).substring(7).trim();
                String year = lines.get(i+1).substring(6).trim();
                String power = lines.get(i+2).substring(7).trim();
                carRecord.add(Car.createNewCar(model, year, power));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
