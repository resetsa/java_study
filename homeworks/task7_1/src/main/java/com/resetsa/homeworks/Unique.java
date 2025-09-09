package com.resetsa.homeworks;

import java.util.ArrayList;
import java.util.HashSet;

// Реализовать метод, который на вход принимает ArrayList<T>, а возвращает набор
// уникальных элементов этого массива. Решить, используя коллекции

public class Unique {
    public static <T> HashSet<T> GetUnique(ArrayList<T> aList) {
        return new HashSet<>(aList);
    }
}
