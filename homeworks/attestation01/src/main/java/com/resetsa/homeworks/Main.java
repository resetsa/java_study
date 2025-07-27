package com.resetsa.homeworks;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var app = new App();
        Scanner scanner = new Scanner(System.in);
        var persons = app.initPerson(scanner);
        var products = app.initProduct(scanner);
        app.run(scanner,persons,products);
        app.printReport(persons);
        scanner.close();
    }
}