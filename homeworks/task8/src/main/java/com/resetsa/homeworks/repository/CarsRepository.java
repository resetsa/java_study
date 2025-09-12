package com.resetsa.homeworks.repository;

public interface CarsRepository {
    public CarsRepositoryImpl getByColor(String color);
    public CarsRepositoryImpl getByMileage(Integer mileage);
    public CarsRepositoryImpl getByPrice(Integer minPrice, Integer maxPrice);
    public CarsRepositoryImpl getByPrice(Integer minPrice);
    public CarsRepositoryImpl getUnique();
    public Double getAveragePrice(String model);
}
