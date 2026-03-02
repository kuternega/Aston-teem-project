package org.AstonStudy.util;

import org.AstonStudy.Car;
import java.io.*;
import java.util.Collection;

public class FileHelper {
    public static void appendToFile(String fileName, Collection<?> collection) {
        try(BufferedWriter out = new BufferedWriter(new FileWriter(fileName))) {
            for (Object element : collection) {
                String outString = String.format("%s;%s;%s", ((Car) element).getPower(), ((Car) element).getModel(), ((Car) element).getYear());
                out.write(outString);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}