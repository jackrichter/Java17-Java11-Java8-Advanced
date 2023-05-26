package org.avdvanced.java.lab3.streams;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Q2_2024 {
    public static void main(String[] args) {
        DoubleStream oddNum = DoubleStream.of(0, 2, 4);
        double sum = oddNum.filter(x -> x % 2 != 0)
                .sum();
        System.out.println(sum);

        OptionalDouble evenNum = Stream.of(1.0, 3.0)
                .mapToDouble(d -> d)
                .filter(x -> x % 2 == 0)
                .average();
        if (evenNum.isPresent()) {
            System.out.println(evenNum);
        } else {
            System.out.println(0.0);
        }

    }
}
