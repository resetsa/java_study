package com.resetsa.homeworks.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.resetsa.homeworks.model.Car;

public interface CarsLoader {
    public List<Car> loadFrom(InputStream inputStream) throws IOException ;
}
