package org.avdvanced.java.lab3.streams;

import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class Q2_2023 {
    public static void main(String[] args) {
        OptionalDouble average = IntStream.range(0, 5)
                .average();
        System.out.println(average.orElse(0));
    }
}
