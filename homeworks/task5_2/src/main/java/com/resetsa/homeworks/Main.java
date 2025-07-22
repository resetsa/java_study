package com.resetsa.homeworks;

public class Main {
    public static void main(String[] args) {
        var example=">>-->>>--<<<--<--->>->";
        FindArrow findArrow = new FindArrow();
        try {
            int count = findArrow.findCountArrow(example);
            System.out.println("Count of arrows: " + count);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}