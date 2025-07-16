package com.resetsa.homeworks;

public class Person {
    private String name;
    private ChoicesRSP choice;

    public Person(String name, ChoicesRSP choice) {
        this.name = name;
        this.choice = choice;
    }

    public String getName() {
        return name;
    }

    public ChoicesRSP getChoice() {
        return choice;
    }
}
