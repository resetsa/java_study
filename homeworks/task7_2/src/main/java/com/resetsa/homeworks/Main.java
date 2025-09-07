package com.resetsa.homeworks;

public class Main {
    public static void main(String[] args) {
        System.out.printf("%s - %s: %b%n", "Бейсбол","бобслей",Anagram.isAnagram("Бейсбол", "бобслей"));
        System.out.printf("%s - %s: %b%n", "героин","регион",Anagram.isAnagram("героин", "регион"));
        System.out.printf("%s - %s: %b%n", "ласка","скала",Anagram.isAnagram("ласка", "скала"));
        System.out.printf("%s - %s: %b%n", "Клоака","околка",Anagram.isAnagram("Клоака", "околка"));
        System.out.printf("%s - %s: %b%n", "test","tests",Anagram.isAnagram("test", "tests"));
    }
}