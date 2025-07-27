package com.resetsa.homeworks;


public class Product {
    private String name;
    private int price;

    public Product(String name, int price) throws IllegalArgumentException {
        checkName(name);
        checkPrice(price);
        this.name = name;
        this.price = price;
    }

    private void checkPrice(int price) throws IllegalArgumentException {
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
    }

    private void checkName(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Имя продукта не может быть пустым");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        checkName(name);
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) throws IllegalArgumentException {
        checkPrice(price);
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", price=" + price + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + price;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (price != other.price)
            return false;
        return true;
    }

}
