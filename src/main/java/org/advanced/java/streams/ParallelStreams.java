package org.advanced.java.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ParallelStreams {
    public static void main(String[] args) {
        doSequentialStream();
        doParallelStream();
        doSequentialAddition();
        doParallelAddition();
    }

    public static void doSequentialStream() {
        System.out.println("doSequentialStream():");
        Arrays.asList("a", "b", "c")                    // create List
                .stream()                               // stream the List
                .forEach(System.out::print);            // abc
        System.out.println();
    }

    public static void doParallelStream() {
        System.out.println("doParallelStream():");
        // Careful if order is important.
        // The order of Thread completion will determine the result
        Arrays.asList("a", "b", "c")
                .stream()
                .parallel()
                .forEach(System.out::print);            // bca
        System.out.println();
    }

    public static void doSequentialAddition() {
        System.out.println("doSequentialAddition():");
        int sum = Stream.of(10, 20, 30, 40, 50, 60)
                .mapToInt(n -> n)                       // Unboxing Integer to int
                .sum();
        System.out.println("Sum == "+ sum);             // Sum == 210
    }

    public static void doParallelAddition() {
        System.out.println("doParallelAddition():");

        // Using Collection.parallelStream
        Stream<String> animalStream = List.of("sheep", "cows", "horses")
                .parallelStream()
                        .map(s -> s.concat(" "));
        animalStream.forEach(s -> System.out.print(s));     // cows horses sheep
        System.out.println();

        // Using Stream.parallel
        Stream.of("sheep", "dogs", "horses")
                .parallel()
                .map(s -> s.concat(" "))                // dogs horses sheep
                .forEach(System.out::print);
        System.out.println();

        int sum = Stream.of(10, 20, 30, 40, 50, 60)
                .parallel()
                .mapToInt(n -> n.intValue())            // <==> n -> n
                .sum();
        System.out.println("The sum: " + sum);          // The sum: 210
    }
}
