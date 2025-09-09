package com.resetsa.homeworks;

import java.util.HashSet;
import java.util.Set;

// Реализовать класс PowerfulSet, в котором должны быть следующие методы:
// - public <T> Set<T> intersection(Set<T> set1, Set<T> set2) – возвращает
// пересечение двух наборов.
// Пример: set1 = {1, 2, 3}, set2 = {0, 1, 2, 4}. Вернуть {1, 2}
// - public <T> Set<T> union(Set<T> set1, Set<T> set2) – возвращает
// объединение двух наборов
// Пример: set1 = {1, 2, 3}, set2 = {0, 1, 2, 4}. Вернуть {0, 1, 2, 3, 4}
// - public <T> Set<T> relativeComplement(Set<T> set1, Set<T> set2) –
// возвращает элементы первого набора без тех, которые находятся также и
// во втором наборе.
// Пример: set1 = {1, 2, 3}, set2 = {0, 1, 2, 4}. Вернуть {3}

public class PowerfulSet {
    public static <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        var result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }
    public static <T> Set<T> union(Set<T> set1, Set<T> set2) {
        var result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }
    public static <T> Set<T> relativeComplement(Set<T> set1, Set<T> set2) {
        var result = new HashSet<>(set1);
        var intersectionSet = intersection(set1, set2);
        result.removeAll(intersectionSet);
        return result;
    }
}
