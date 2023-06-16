package org.advanced.java.streams;

import java.util.Arrays;
import java.util.List;

public class Laziness {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("April", "Ben", "Charlie", "David", "Benildus", "Christian");
        names.stream()
                .peek(System.out::println)
                .filter(s -> {
                    System.out.println("Filter1: " + s);
                    return s.startsWith("B") || s.startsWith("C");
                })
                .filter(s -> {
                    System.out.println("Filter2: " + s);
                    return s.length() > 3;
                })
                .limit(1)
                .forEach(System.out::println);                  // Charlie
    }
}
