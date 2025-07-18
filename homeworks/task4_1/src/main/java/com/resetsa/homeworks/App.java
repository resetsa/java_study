package com.resetsa.homeworks;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        ArrayList<TV> tvs = new ArrayList<>();

        // Creating TV objects with different constructors
        var tv1 = new TV("Samsung", "QLED", 55);
        var tv2 = new TV("LG", "OLED");
        var tv3 = new TV("Sony", "Bravia", 65);

        // Adding TVs to the list
        tvs.add(tv1);
        tvs.add(tv2);
        tvs.add(tv3);

        // Displaying the TVs
        for (var tv : tvs) {
            System.out.println(tv);
        }
        
    }
}