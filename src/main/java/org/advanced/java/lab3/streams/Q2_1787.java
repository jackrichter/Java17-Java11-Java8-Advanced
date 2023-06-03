package org.advanced.java.lab3.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Q2_1787 {
    public static void main(String[] args) {
        Stream<List<String>> listsStream = Stream.of(Arrays.asList("a", "b"), Arrays.asList("a", "c"));
        listsStream.filter(list -> list.contains("c"))
                .flatMap(list -> list.stream())
                .forEach(System.out::println);
    }
}
