package ru.education;

public class Reader {
    private String name;
    private int usageTime;

    public Reader(String name) {
        this.name = name;
        this.usageTime = 0;
    }

    public String getName() {
        return name;
    }

    public int getUsageTime() {
        return usageTime;
    }
}
