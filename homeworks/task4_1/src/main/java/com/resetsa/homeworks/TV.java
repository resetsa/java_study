package com.resetsa.homeworks;

import java.util.Random;

public class TV {
    private String brand;
    private String model;
    private int screenSize;

    TV (String brand, String model, int screenSize) {
        this.brand = brand;
        this.model = model;
        this.screenSize = screenSize;
    }

    TV (String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.screenSize = new Random().nextInt(50) + 50;
    }
    
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    public String toString() {
        return "TV{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", screenSize=" + screenSize +
                '}';
    }
}
