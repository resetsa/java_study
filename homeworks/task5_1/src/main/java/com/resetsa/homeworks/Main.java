package com.resetsa.homeworks;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Enter english letter:");
        Scanner scanner = new java.util.Scanner(System.in);
        var input = scanner.nextLine();
        var shift = new Shift();
        try {
            var result = shift.shiftChar(input.charAt(0));
            System.out.println("Shifted character:" + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}