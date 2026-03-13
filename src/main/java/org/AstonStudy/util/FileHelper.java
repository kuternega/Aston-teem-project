package org.AstonStudy.util;

import org.AstonStudy.model.Car;
import java.io.*;
import java.util.Collection;

public class FileHelper {
    public static void appendToFile(String fileName, Collection<?> collection) {
        StringBuilder buffer = new StringBuilder();
        for (Object element : collection) {
            buffer.append(String.format("%s;%s;%s\n", ((Car) element).getPower(), ((Car) element).getModel(), ((Car) element).getYear()));
        }
        try(BufferedWriter out = new BufferedWriter(new FileWriter(fileName))) {
            out.write(buffer.toString());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}