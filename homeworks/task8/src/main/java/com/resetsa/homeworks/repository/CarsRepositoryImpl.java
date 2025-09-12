package com.resetsa.homeworks.repository;

import com.resetsa.homeworks.model.Car;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.List;

public class CarsRepositoryImpl implements CarsRepository {
    private List<Car> cars;
    public CarsRepositoryImpl(CarsLoader carsLoader, InputStream inputStream) throws IOException {
        this.cars = carsLoader.loadFrom(inputStream);
    }

    public CarsRepositoryImpl(List<Car> cars) {
        this.cars = cars;
    }

    public void printCars() {
        cars.forEach(System.out::println);
    }

    public List<Car> getCars() {
        return cars;
    }

    public void extendCars(List<Car> newCars) {
        this.cars.addAll(newCars);
    }


    public CarsRepositoryImpl getByColor(String color) {
        return new CarsRepositoryImpl(cars.stream()
                .filter(car -> car.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList()));
    }

    @Override
    public CarsRepositoryImpl getByMileage(Integer mileage) {
        return new CarsRepositoryImpl(cars.stream()
                .filter(car -> car.getMileage() <= mileage)
                .collect(Collectors.toList()));
    }

    @Override
    public CarsRepositoryImpl getByPrice(Integer minPrice, Integer maxPrice) {
        return new CarsRepositoryImpl(cars.stream()
                .filter(car -> car.getPrice() >= minPrice && car.getPrice() <= maxPrice)
                .distinct()
                .sorted(Comparator.comparingInt(Car::getPrice))
                .collect(Collectors.toList()));
    }

    @Override
    public CarsRepositoryImpl getByPrice(Integer minPrice) {
        return new CarsRepositoryImpl(cars.stream()
                .filter(car -> car.getPrice() >= minPrice)
                .distinct()
                .sorted(Comparator.comparingInt(Car::getPrice))
                .collect(Collectors.toList()));
    }

    @Override
    public CarsRepositoryImpl getUnique() {
        var uniq=cars.stream().collect(Collectors.toSet());
        return new CarsRepositoryImpl(uniq.stream().collect(Collectors.toList()));
    }

    @Override
    public Double getAveragePrice(String model) {
        return cars.stream()
                .filter(car -> car.getModel().equalsIgnoreCase(model))
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0.0);
    }


}
