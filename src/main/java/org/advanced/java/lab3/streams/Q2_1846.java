package org.advanced.java.lab3.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Q2_1846 {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book("Atlas Shrugged", 10.0),
                new Book("Freedom at Midnight", 5.0),
                new Book("Gone with the wind", 5.0) );

        BiConsumer<String, Double> biConsumer = (a, b) -> {
          if (a.startsWith("A")) {
              System.out.println(b);
          }
        };

        Map<String, Double> bookMap = books
                .stream()
                .collect(Collectors.toMap(b -> b.getTitle(), b -> b.getPrice()));

        bookMap.forEach(biConsumer);
    }
}
