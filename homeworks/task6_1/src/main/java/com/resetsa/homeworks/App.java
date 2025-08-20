package com.resetsa.homeworks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class App {
    private final String EXIT_COMMAND = "end";
    private final String PERSON_DELIMITER = ";";
    private final String PRODUCT_DELIMITER = ";";
    private final String FIELD_DELIMETER = "=";
    private final String PERSON_PURCHASE_DELIMETER = "-";
    private final String PURCHASE_DELIMETER = ",";

    private final LocalDate processDate;

    public App() {
        this.processDate = LocalDate.now();
    }

    public App(LocalDate processDate) {
        if (processDate == null) {
            throw new IllegalArgumentException("Дата не может быть null");
        }
        this.processDate = processDate;
    }

    public LocalDate getProcessDate() {
        return processDate;
    }

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

    public HashMap<String,InterfaceProduct> parseProducts(String[] productDescriptions, String delimField) {
        HashMap<String,InterfaceProduct> products = new HashMap<>();
        if (delimField == "" || delimField.isEmpty()) {
            throw new IllegalArgumentException("Разделитель полей не может быть пустым");
        }
        for (String description : productDescriptions) {
            String[] parts = description.split(delimField);
            try {
                if (parts.length == 2) {
                    var name = parts[0].trim();
                    var price = Integer.parseInt(parts[1].trim());
                    products.put(name, new Product(name, price));
                    continue;
                }
                if (parts.length == 4) {
                    var name = parts[0].trim();
                    var price = Integer.parseInt(parts[1].trim());
                    var discount = Integer.parseInt(parts[2].trim());
                    var discountDate = LocalDate.parse(parts[3].trim());
                    products.put(name, new DiscountProduct(name, price, discount, discountDate));
                    continue;
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Введено не число в строке: " + description);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Неверный формат даты в строке: " + description);
            }
            throw new IllegalArgumentException("Неверный формат: " + description);
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
        result.add(String.format("Отчет на %s%n", processDate));
        for ( Map.Entry<String,Person> person : persons.entrySet()) {
            ArrayList<String> nameOrders = new ArrayList<>();
            for (var order : person.getValue().getBucket()) {
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
            System.out.println(e.getMessage());
            return null;
        }
        return persons;
    }

    public HashMap<String,InterfaceProduct> initProduct(Scanner scanner) {
        HashMap<String,InterfaceProduct> products = new HashMap<>();
        System.out.println("Введите строку описания продуктов");
        System.out.printf("Формат: имя1%sцена%sимя2=цена2%n", FIELD_DELIMETER, PRODUCT_DELIMITER);
        System.out.printf("Формат: имя1%sцена%sскидка%sдата_окончания%sимя2=цена2%n", FIELD_DELIMETER, FIELD_DELIMETER, FIELD_DELIMETER, PRODUCT_DELIMITER);
        String[] productDescriptions = scanner.nextLine().split(PRODUCT_DELIMITER);
        try {
            products = parseProducts(productDescriptions, FIELD_DELIMETER);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
            return null;
        }
        return products;
    }

    public void run(Scanner scanner, HashMap<String,Person> persons, HashMap<String,InterfaceProduct> products) {
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
                System.out.println(e.getMessage());
            }
            finally {
                // System.out.println(persons);
                // System.out.println(products);
                input = scanner.nextLine();
            }
        }
    }

}
