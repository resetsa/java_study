package com.resetsa.homeworks;

import java.time.LocalDate;

public class DiscountProduct extends Product {
    public static final String DEFAULT_DISCOUNT_END_DATE = "2100-12-31";
    private int discount;
    private boolean isForceDiscount;
    private LocalDate DiscountEndDate = LocalDate.parse("2000-12-31");

    public DiscountProduct(String name, int price, int discount, boolean isForceDiscount) throws IllegalArgumentException {
        super(name, price);
        checkDiscount(discount);
        this.discount = discount;
        this.isForceDiscount = isForceDiscount;
    }

    public DiscountProduct(String name, int price, int discount) throws IllegalArgumentException {
        super(name, price);
        checkDiscount(discount);
        this.discount = discount;
        this.isForceDiscount = true;
    }

    public DiscountProduct(String name, int price, int discount, LocalDate discountEndDate) throws IllegalArgumentException {
        super(name, price);
        checkDiscount(discount);
        this.discount = discount;
        this.isForceDiscount = false;
        this.DiscountEndDate = discountEndDate;
    }

    private void checkDiscount(int discount) throws IllegalArgumentException {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Скидка должна быть в диапазоне от 0 до 100");
        }
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) throws IllegalArgumentException {
        checkDiscount(discount);
        this.discount = discount;
    }

    public boolean isForceDiscount() {
        return isForceDiscount;
    }

    public void setForceDiscount(boolean isDiscountActive) {
        this.isForceDiscount = isDiscountActive;
    }

    @Override
    public int getPrice() {
        if (isForceDiscount || LocalDate.now().isBefore(DiscountEndDate)) {
            var k = (100 - discount)/100.0;
            return (int)Math.round(super.getPrice() * k);
        }
        return super.getPrice();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + discount;
        result = prime * result + (isForceDiscount ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        DiscountProduct other = (DiscountProduct) obj;
        if (discount != other.discount)
            return false;
        if (isForceDiscount != other.isForceDiscount)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DiscountProduct [discount=" + discount + ", isDiscountActive=" + isForceDiscount + ", getDiscount()="
                + getDiscount() + ", isDiscountActive()=" + isForceDiscount() + "]";
    }

}
