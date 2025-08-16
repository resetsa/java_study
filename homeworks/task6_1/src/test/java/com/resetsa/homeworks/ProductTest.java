package com.resetsa.homeworks;

import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class ProductTest {
    @Test
    @DisplayName("Тест создания продукта с корректными данными")
    public void testSetName_valid() {
        var product = new Product("Apple", 10);
        assert product.getName().equals("Apple");
        assert product.getPrice() == 10;
    }

    @Test
    @DisplayName("Тест создания продукта с пустым именем")
    public void testSetName_invalid() {
        IllegalArgumentException exp = assertThrows(IllegalArgumentException.class, () -> {
            new Product("", 100);
        });
        assert exp.getMessage().contains("Имя продукта не может быть пустым");
    }

    @Test
    @DisplayName("Тест создания продукта с отрицательной ценой")
    public void testSetPriceName_invalid() {
        IllegalArgumentException exp = assertThrows(IllegalArgumentException.class, () -> {
            new Product("apple", -100);
        });
        assert exp.getMessage().contains("Цена не может быть отрицательной");
    }
}
