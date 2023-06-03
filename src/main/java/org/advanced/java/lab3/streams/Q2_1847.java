package org.advanced.java.lab3.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Q2_1847 {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book("Gone with the wind", 10.0),
                new Book("Gone with the wind", 20.0),
                new Book("Atlas Shrugged", 15.0) );

        books.stream()
                .collect(Collectors.toMap((b->b.getTitle()), b->b.getPrice(), (p1, p2) -> p1 + p2))
                .forEach((a, b)->System.out.println(a + " " + b));
    }
}
