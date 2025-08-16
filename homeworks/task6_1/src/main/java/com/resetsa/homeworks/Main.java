package com.resetsa.homeworks;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var app = new App();
        Scanner scanner = new Scanner(System.in);
        var persons = app.initPerson(scanner);
        if (persons == null || persons.isEmpty()) {
            System.out.println("Нет покупателей для продолжения работы");
            return;
        }
        var products = app.initProduct(scanner);
        if (products == null || products.isEmpty()) {
            System.out.println("Нет продуктов для продолжения работы");
            return;
        }
        app.run(scanner,persons,products);
        app.printReport(persons);
        scanner.close();
    }
}