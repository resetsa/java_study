package com.resetsa.homeworks;

import java.util.Arrays;

// С консоли на вход подается две строки s и t. Необходимо вывести true, если одна
// строка является валидной анаграммой другой строки, и false – если это не так.
// Анаграмма – это слово, или фраза, образованная путем перестановки букв другого
// слова или фразы, обычно с использованием всех исходных букв ровно один раз.
// Для проверки:
// - Бейсбол – бобслей
// - Героин – регион
// - Клоака – околка

public class Anagram {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        var listS = s.trim().toLowerCase().toCharArray();
        var listT = t.trim().toLowerCase().toCharArray();
        Arrays.sort(listS);
        Arrays.sort(listT);
        return Arrays.equals(listS,listT);
    }
}
