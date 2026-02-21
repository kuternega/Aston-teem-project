package org.AstonStudy.ClassLoader;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultClassLoaderTest {

    @Test
    public void testLoadRecords() {
        DefaultClassLoader loader = new DefaultClassLoader();
        loader.load();

        // Используем импортированный класс Record
        List<Record> records = loader.getDataRecord();
        assertEquals(3, records.size());

        assertEquals("NAME#1", records.get(0).getName());
        assertEquals("SOMETHING#1", records.get(0).getSomething1());
        assertEquals("ANDNEXT#1", records.get(0).getSomething2());

        assertEquals("NAME#2", records.get(1).getName());
        assertEquals("SOMETHING#2", records.get(1).getSomething1());
        assertEquals("ANDNEXT#2", records.get(1).getSomething2());

        assertEquals("NAME#3", records.get(2).getName());
        assertEquals("SOMETHING#3", records.get(2).getSomething1());
        assertEquals("ANDNEXT#3", records.get(2).getSomething2());
    }
}
