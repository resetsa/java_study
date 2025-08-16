package com.resetsa.homeworks;

import java.util.ArrayList;

public class Person {
    private String name;
    private int cash;
    private ArrayList<InterfaceProduct> bucket = new ArrayList<>();
    public Person(String name, int cash) throws IllegalArgumentException {
        checkName(name);
        checkCash(cash);
        this.name = name;
        this.cash = cash;
        this.bucket = new ArrayList<>();
    }

    private void checkName(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if  (name.length() < 3) {
            throw new IllegalArgumentException("Имя не может быть короче 3 символов");
        }
    }

    private void checkCash(int cash) throws IllegalArgumentException {
        if (cash < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        checkName(name);
        this.name = name;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) throws IllegalArgumentException {
        checkCash(cash);
        this.cash = cash;
    }

    public ArrayList<InterfaceProduct> getBucket() {
        return bucket;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", cash=" + cash + ", bucket=" + bucket + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + cash;
        result = prime * result + ((bucket == null) ? 0 : bucket.hashCode());
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
        Person other = (Person) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (cash != other.cash)
            return false;
        if (bucket == null) {
            if (other.bucket != null)
                return false;
        } else if (!bucket.equals(other.bucket))
            return false;
        return true;
    }

    public void addToBucket(InterfaceProduct product) throws IllegalArgumentException {
        if (product == null) {
            throw new IllegalArgumentException("Продукт не может быть null");
        }
        try {
            this.setCash(this.getCash() - product.getPrice());
            this.bucket.add(product);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("%s не может позволить себе %s", this.name, product.getName()));
        }
    }

    
}
