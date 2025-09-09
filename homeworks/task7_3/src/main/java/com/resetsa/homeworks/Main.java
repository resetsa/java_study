package com.resetsa.homeworks;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Integer> setI1 = Set.of(1,2,3);
        Set<Integer> setI2 = Set.of(0,1,2,4);
        Print(setI1, setI2);
        Set<String> setS1 = Set.of("a","b","c");
        Set<String> setS2 = Set.of("a","d","e","f");
        Print(setS1, setS2);
    }

    public static <T> void Print(Set<T> set1, Set<T> set2) {
        System.out.printf("set1: %s%n", set1);
        System.out.printf("set2: %s%n", set2);
        System.out.printf("Intersection: %s%n", PowerfulSet.intersection(set1,set2).toString());
        System.out.printf("Union: %s%n", PowerfulSet.union(set1,set2).toString());
        System.out.printf("RelativeComplement: %s%n", PowerfulSet.relativeComplement(set1, set2).toString());
    }
}