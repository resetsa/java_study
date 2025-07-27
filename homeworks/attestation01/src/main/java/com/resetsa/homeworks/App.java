package com.resetsa.homeworks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    private final String EXIT_COMMAND = "end";
    private final String PERSON_DELIMITER = ";";
    private final String PRODUCT_DELIMITER = ";";
    private final String FIELD_DELIMETER = "=";
    private final String PERSON_PURCHASE_DELIMETER = "-";
    private final String PURCHASE_DELIMETER = ",";

    public HashMap<String,Person> parsePersons(String[] personDescriptions, String delimField) {
        HashMap<String,Person> persons = new HashMap<>();
        if (delimField == "" || delimField.isEmpty()) {
            throw new IllegalArgumentException("Разделитель полей не может быть пустым");
        }
        for (String description : personDescriptions) {
            String[] parts = description.split(delimField);
            if (parts.length != 2) {
                throw new IllegalArgumentException("Неверный формат: " + description);
            }
            String name = parts[0].trim();
            int cash;
            try {
                cash = Integer.parseInt(parts[1].trim());
                persons.put(name, new Person(name, cash));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Введено не число " + name + ": " + parts[1]);
            }
        }
        return persons;
    }

    public HashMap<String,Product> parseProducts(String[] productDescriptions, String delimField) {
        HashMap<String,Product> products = new HashMap<>();
        if (delimField == "" || delimField.isEmpty()) {
            throw new IllegalArgumentException("Разделитель полей не может быть пустым");
        }
        for (String description : productDescriptions) {
            String[] parts = description.split(delimField);
            if (parts.length != 2) {
                throw new IllegalArgumentException("Неверный формат: " + description);
            }
            String name = parts[0].trim();
            int price;
            try {
                price = Integer.parseInt(parts[1].trim());
                products.put(name, new Product(name, price));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Введено не число " + name + ": " + parts[1]);
            }
        }
        return products;
    }

    public void printReport(HashMap<String,Person> persons) {
        ArrayList<String> report = genReport(persons, PERSON_PURCHASE_DELIMETER, PURCHASE_DELIMETER);
        for (String line : report) {
            System.out.print(line);
        }
    }
    public ArrayList<String> genReport(HashMap<String,Person> persons, String personOrderDelim, String orderDelim) {
        ArrayList<String> result = new ArrayList<>();
        for ( Map.Entry<String,Person> person : persons.entrySet()) {
            ArrayList<String> nameOrders = new ArrayList<>();
            for (Product order : person.getValue().getBucket()) {
                nameOrders.add(order.getName());
            }
            String listOrderNames = new String("Ничего не куплено");
            if (nameOrders.size() != 0) {
                listOrderNames = String.join(orderDelim, nameOrders);
            }
            result.add(String.format("%s %s %s%n", person.getValue().getName(), personOrderDelim, listOrderNames));
        }
        return result;
    }

    public HashMap<String,Person> initPerson (Scanner scanner) {
        HashMap<String,Person> persons = new HashMap<>();
        System.out.println("Введите строку описания персон");
        System.out.printf("Формат: имя1%sкол-во монет%sимя2=кол-во монет%n", FIELD_DELIMETER, PERSON_DELIMITER);
        String[] personDescriptions = scanner.nextLine().split(PERSON_DELIMITER);
        try {
            persons = parsePersons(personDescriptions, FIELD_DELIMETER);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
            return null;
        }
        return persons;
    }

    public HashMap<String,Product> initProduct(Scanner scanner) {
        HashMap<String,Product> products = new HashMap<>();
        System.out.println("Введите строку описания продуктов");
        System.out.printf("Формат: имя1%sцена1%sимя2=цена2%n", FIELD_DELIMETER, PRODUCT_DELIMITER);
        String[] productDescriptions = scanner.nextLine().split(PRODUCT_DELIMITER);
        try {
            products = parseProducts(productDescriptions, FIELD_DELIMETER);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
            return null;
        }
        return products;
    }

    public void run(Scanner scanner, HashMap<String,Person> persons, HashMap<String,Product> products) {
        System.out.println("Введите имя покупателя и имя продукта");
        System.out.printf("Формат: имя покупателя%sимя продукта%n", PERSON_PURCHASE_DELIMETER);
        System.out.println("Для выхода введите end");
        String input = scanner.nextLine();

        while (!input.equalsIgnoreCase(EXIT_COMMAND))
        {
            try {
                String[] parts = input.split(PERSON_PURCHASE_DELIMETER);
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Неверный формат: " + input);
                }
                String personName = parts[0].trim();
                String productName = parts[1].trim();
                
                var person = persons.get(personName);
                var product = products.get(productName);
                if (person == null || product == null) {
                    throw new IllegalArgumentException("Покупатель или продукт не найден");
                }
                
                person.addToBucket(product);
                
                System.out.println(person.getName() + " купил " + product.getName());
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
            finally {
                // System.out.println(persons);
                // System.out.println(products);
                input = scanner.nextLine();
            }
        }
    }

}
