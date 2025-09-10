package com.resetsa.homeworks.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    private static final String filePath = "homeworks/task8/src/main/java/com/resetsa/homeworks/data/car.txt";
    public static void main(String[] args) {
        try (InputStream inputStream = new FileInputStream(filePath)) {
            var loader = new com.resetsa.homeworks.repository.CarsLoaderCSV();
            var repository = new com.resetsa.homeworks.repository.CarsRepositoryImpl(loader, inputStream);
            var blackOrNew = repository.getByColor("Black");
            blackOrNew.extendCars(repository.getByMileage(0).getCars());
            var uniqueCars = repository.getByPrice(700_000, 800_000).getUnique();
            var sortByPrice = repository.getByPrice(0);
            var modelTAverage = repository.getAveragePrice("Toyota");
            var modelVAverage = repository.getAveragePrice("Volvo");
            System.out.println("All cars:");
            repository.printCars();
            System.out.println("Black cars or new cars IDs:");
            blackOrNew.getUnique().getCars().stream().forEach(car -> System.out.println(car.getId()));
            System.out.printf("Unique model number between %d and %d: %d\n", 700_000,800_000,uniqueCars.getCars().size());
            System.out.printf("Color min price: %s\n", sortByPrice.getCars().get(0).getColor());
            System.out.printf("Average price for %s: %.2f\n", "Toyota", modelTAverage);
            System.out.printf("Average price for %s: %.2f\n", "Volvo", modelVAverage);
        }
        catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
        }
        catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Wrong data format: " + e.getMessage());
        }
    }
}