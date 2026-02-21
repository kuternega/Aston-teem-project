package org.AstonStudy.ClassLoader;

public class Record {
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