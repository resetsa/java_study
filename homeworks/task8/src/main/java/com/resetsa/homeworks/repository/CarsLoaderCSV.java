package com.resetsa.homeworks.repository;

import java.io.InputStream;
import java.io.IOException;

import java.util.List;
import com.resetsa.homeworks.model.Car;
import java.util.stream.Collectors;

import de.siegmar.fastcsv.reader.CsvRecord;
import de.siegmar.fastcsv.reader.CsvReader;

public class CarsLoaderCSV implements CarsLoader {
    private char delimiter = '|';

    public CarsLoaderCSV(char delimiter) {
        this.delimiter = delimiter;
    }

    public CarsLoaderCSV() {
    }
    
    @Override
    public List<Car> loadFrom(InputStream inputStream) throws IOException {
        try (CsvReader<CsvRecord> csvReader = CsvReader.builder().fieldSeparator(delimiter).ofCsvRecord(inputStream)) {
            return csvReader.stream()
                    .map(record -> new Car(
                            record.getField(0),
                            record.getField(1),
                            record.getField(2),
                            Integer.parseInt(record.getField(3)),
                            Integer.parseInt(record.getField(4))))
                    .collect(Collectors.toList());
        }
    }
}

