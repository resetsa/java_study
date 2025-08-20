package com.resetsa.homeworks;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.Test;

public class DiscountProductTest {
    @Test
    public void testGetPrice_discount_true_force() {
        DiscountProduct product = new DiscountProduct("Apple", 10,20, true);
        assert product.getName().equals("Apple");
        assert product.isForceDiscount() == true;
        assert product.getDiscount() == 20;
        assert product.getPrice() == 8 : "Expected price after discount to be 8, but got " + product.getPrice();
    }

    @Test
    public void testGetPrice_discount_false_force() {
        DiscountProduct product = new DiscountProduct("Apple", 10,20, false);
        assert product.getName().equals("Apple");
        assert product.isForceDiscount() == false;
        assert product.getDiscount() == 20;
        assert product.getPrice() == 10 : "Expected price after discount to be 10, but got " + product.getPrice();
    }

    @Test
    public void testGetPrice_discount_bydate_true() {
        DiscountProduct product = new DiscountProduct("Apple", 10,20, LocalDate.parse("2100-12-31"));
        assert product.getName().equals("Apple");
        assert product.isForceDiscount() == false;
        assert product.getDiscount() == 20;
        assert product.getPrice() == 8 : "Expected price after discount to be 8, but got " + product.getPrice();
    }

    @Test
    public void testGetPrice_discount_bydate_false() {
        DiscountProduct product = new DiscountProduct("Apple", 10,20, LocalDate.parse("2000-12-31"));
        assert product.getName().equals("Apple");
        assert product.isForceDiscount() == false;
        assert product.getDiscount() == 20;
        assert product.getPrice() == 10 : "Expected price after discount to be 10, but got " + product.getPrice();
    }

    @Test
    public void testCheckDiscount_valid() {
        var product = new DiscountProduct("Apple", 10,20, true);
        assert product.getName().equals("Apple");
        assert product.getDiscount() == 20;
    }

    @Test
    public void testCheckDiscount_invalid_neg() {
        IllegalArgumentException exp = assertThrows(IllegalArgumentException.class, () -> {
            new DiscountProduct("apple", 100, -20, true);
        });
        assert exp.getMessage().contains("Скидка должна быть в диапазоне от 0 до 100");
    }

    @Test
    public void testCheckDiscount_invalid_more() {
        IllegalArgumentException exp = assertThrows(IllegalArgumentException.class, () -> {
            new DiscountProduct("apple", 100, 120, true);
        });
        assert exp.getMessage().contains("Скидка должна быть в диапазоне от 0 до 100");
    }

    @Test
    public void testCheckDiscount_valid_0() {
        var product = new DiscountProduct("Apple", 10,0, true);
        assert product.getName().equals("Apple");
        assert product.getDiscount() == 0;
    }

    @Test
    public void testCheckDiscount_valid_100() {
        var product = new DiscountProduct("Apple", 10,100, true);
        assert product.getName().equals("Apple");
        assert product.getDiscount() == 100;
    }

}
