package com.resetsa.homeworks;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThrows;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class PersonTest {
    @Test
    @DisplayName("Тест добавления продукта в корзину с достаточным количеством монет")
    public void testAddToBucket1() {
        var person = new Person("Alice", 100);
        var product = new Product("Apple", 10);
        person.addToBucket(product);
        assert person.getBucket().contains(product);
    }

    @Test
    @DisplayName("Тест добавления продукта в корзину с достаточным количеством монет, остаток 0")
    public void testAddToBucket2() {
        var person = new Person("Alice", 100);
        var product = new Product("Apple", 100);
        person.addToBucket(product);
        assert person.getBucket().contains(product);
    }

    @Test
    @DisplayName("Тест добавления продукта в корзину с недостаточным количеством монет")
    public void testAddToBucket4() {
        var person = new Person("Alice", 100);
        var product = new Product("Apple", 110);
        IllegalArgumentException exp = assertThrows(IllegalArgumentException.class, () -> {
            person.addToBucket(product);
        });
        assertThat(exp.getMessage(), containsString("не может позволить себе"));
    }

    @Test
    @DisplayName("Тест создания персоны с корректными данными")
    public void testPerson1() {
        var person = new Person("Alice", 100);
        assert person.getName().equals("Alice");
        assert person.getCash() == 100;
        assert person.getBucket().isEmpty();
    }

    @Test
    @DisplayName("Тест создания персоны с отрицательным количеством монет")
    public void testPerson2() {
        IllegalArgumentException exp = assertThrows(IllegalArgumentException.class, () -> {
            new Person("Alice", -10);
        });
        assert exp.getMessage().contains("Деньги не могут быть отрицательными");
    }

    @Test
    @DisplayName("Тест создания персоны с пустым именем")
    public void testPerson3() {
        IllegalArgumentException exp = assertThrows(IllegalArgumentException.class, () -> {
            new Person("", 100);
        });
        assert exp.getMessage().contains("Имя не может быть пустым");
    }

    @Test
    @DisplayName("Тест создания персоны с именем 2 символа")
    public void testPerson4() {
        IllegalArgumentException exp = assertThrows(IllegalArgumentException.class, () -> {
            new Person("сф", 100);
        });
        assert exp.getMessage().contains("Имя не может быть короче 3 символов");
    }
}
