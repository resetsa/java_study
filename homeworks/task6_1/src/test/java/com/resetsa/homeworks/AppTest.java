package com.resetsa.homeworks;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.CoreMatchers.containsString;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.HashMap;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class AppTest {
    @Test
    @DisplayName("ParsePersons Валидное использование")
    public void testParsePersons_valid() {
        HashMap<String,Person> expectedMap = new HashMap<>();
        expectedMap.put("alice", new Person("alice", 0));
        expectedMap.put("bob", new Person("bob", 100));

        String[] input = new String[]{"alice = 0","bob = 100"};
        var app = new App();
        var getMap = app.parsePersons(input, "=");

        assertEquals(expectedMap, getMap);
    }

    @Test
    @DisplayName("ParsePersons Пустой разделитель")
    public void testParsePersons_delim_null() {
        String[] input = new String[]{"alice = 0","bob = 100"};
        var app = new App();

        IllegalArgumentException exp = assertThrows(IllegalArgumentException.class, () -> {
            app.parsePersons(input, "");
        });
        assert exp.getMessage().contains("Разделитель полей не может быть пустым");
    }

    @Test
    @DisplayName("ParsePersons Неверный формат")
    public void testParsePersons_delim_wrong_format() {
        String[] input = new String[]{"alice = bob = 0","bob = 100"};
        var app = new App();

        IllegalArgumentException exp = assertThrows(IllegalArgumentException.class, () -> {
            app.parsePersons(input, "=");
        });
        assert exp.getMessage().contains("Неверный формат");
    }

    @Test
    @DisplayName("ParsePersons Значение не число")
    public void testParsePersons_cash_not_digit() {
        String[] input = new String[]{"alice = aa","bob = 100"};
        var app = new App();

        IllegalArgumentException exp = assertThrows(IllegalArgumentException.class, () -> {
            app.parsePersons(input, "=");
        });
        assertThat(exp.getMessage(), containsString("Введено не число"));
    }

    @Test
    @DisplayName("ParsePersons Пустой массив")
    public void testParsePersons_input_null() {
        String[] input = new String[]{"",""};
        var app = new App();

        IllegalArgumentException exp = assertThrows(IllegalArgumentException.class, () -> {
            app.parsePersons(input, "=");
        });
        assertThat(exp.getMessage(), containsString("Неверный формат"));
    }

    @Test
    @DisplayName("ParseProducts Валидное использование")
    public void testParseProducts_valid() {
        HashMap<String,Product> expectedMap = new HashMap<>();
        expectedMap.put("beer", new Product("beer", 10));
        expectedMap.put("apple", new Product("apple", 100));

        String[] input = new String[]{"beer = 10","apple = 100"};
        var app = new App();
        var getMap = app.parseProducts(input, "=");

        assertEquals(expectedMap, getMap);
    }

    @Test
    @DisplayName("ParseProducts Пустой разделитель")
    public void testParseProducts_delim_null() {
        String[] input = new String[]{"beer = 10","apple = 100"};
        var app = new App();

        IllegalArgumentException exp = assertThrows(IllegalArgumentException.class, () -> {
            app.parseProducts(input, "");
        });
        assert exp.getMessage().contains("Разделитель полей не может быть пустым");
    }

    @Test
    @DisplayName("ParseProducts Неверный формат")
    public void testParseProducts_delim_wrong_format() {
        String[] input = new String[]{"beer = apple = 10","apple = 100"};
        var app = new App();

        IllegalArgumentException exp = assertThrows(IllegalArgumentException.class, () -> {
            app.parseProducts(input, "=");
        });
        assert exp.getMessage().contains("Неверный формат");
    }

    @Test
    @DisplayName("ParseProducts Значение не число")
    public void testParseProducts_cash_not_digit() {
        String[] input = new String[]{"beer = aa","apple = 100"};
        var app = new App();

        IllegalArgumentException exp = assertThrows(IllegalArgumentException.class, () -> {
            app.parseProducts(input, "=");
        });
        assertThat(exp.getMessage(), containsString("Введено не число"));
    }

    @Test
    @DisplayName("ParseProducts Пустой массив")
    public void testParseProducts_input_null() {
        String[] input = new String[]{"",""};
        var app = new App();

        IllegalArgumentException exp = assertThrows(IllegalArgumentException.class, () -> {
            app.parseProducts(input, "=");
        });
        assertThat(exp.getMessage(), containsString("Неверный формат"));
    }

    @Test
    @DisplayName("InitPersons Валидное использование")
    public void testInitPersons_valid() {
        HashMap<String,Person> expectedMap = new HashMap<>();
        expectedMap.put("Василий А", new Person("Василий А", 100));
        expectedMap.put("Иван Б", new Person("Иван Б", 200));
        expectedMap.put("Андрей А И", new Person("Андрей А И", 400));

        var app = new App();
        try {
            FileInputStream fInput = new FileInputStream("src/test/resources/valid_persons.txt");
            var getMap = app.initPerson(new Scanner(fInput));
            assertEquals(expectedMap, getMap);
        } catch (Exception e) {
            e.printStackTrace();
            assert false : "Ошибка при чтении файла";
        }
    }

        @Test
    @DisplayName("initProducts Валидное использование")
    public void testInitProducts_valid() {
        HashMap<String,Product> expectedMap = new HashMap<>();
        expectedMap.put("Хлеб", new Product("Хлеб", 100));
        expectedMap.put("Темный хлеб", new Product("Темный хлеб", 0));
        expectedMap.put("Bread", new Product("Bread", 400));

        var app = new App();
        try {
            FileInputStream fInput = new FileInputStream("src/test/resources/valid_products.txt");
            var getMap = app.initProduct(new Scanner(fInput));
            assertEquals(expectedMap, getMap);
        } catch (Exception e) {
            e.printStackTrace();
            assert false : "Ошибка при чтении файла";
        }
    }
}
