package com.resetsa.homeworks;

import java.util.ArrayList;
import java.util.Arrays;

public class Sort {
    public static String sort(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        String[] words = input.split(" ");
        ArrayList<String> sortedWords = new ArrayList<String>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            sortedWords.add(sorted);
        }
        java.util.Arrays.sort(words);
        return String.join(" ", sortedWords);
    }
}
