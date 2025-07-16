package com.resetsa.homeworks;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Имя: ");
        String name = scanner.nextLine();
        System.out.printf("Привет, %s!%n", name);
        scanner.close();
    }
}