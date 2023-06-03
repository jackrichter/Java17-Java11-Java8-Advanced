package org.advanced.java.lab3.streams;

import java.util.Arrays;
import java.util.List;

public class Q2_1809 {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book("Thinking in Java", 30.0),
                new Book("Java in 24 hrs", 20.0),
                new Book("Java Recipies", 10.0));

        double avg = books.stream()
                .filter(book -> book.getPrice() > 10)
                .mapToDouble(book -> book.getPrice())
                .average()
                .getAsDouble();
        System.out.println(avg);

        double avg2 = books.stream()
                .filter(book -> book.getPrice() > 90)
                .mapToDouble(book -> book.getPrice())
                .average()
//                .getAsDouble();
                .orElse(0.0);
        System.out.println(avg2);
    }
}
