package org.advanced.java.streams;

import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class CreatingStreams {
    public static void main(String[] args) {
        fromArray();
        fromCollection();
        fromPrimitives();
        infiniteStreams();
    }

    public static void fromArray() {
        System.out.println("fromArray():");
        // Array is considered a source of a stream
        Double[] numbers = {1.1, 2.2, 3.3};
        Stream<Double> stream1 = Arrays.stream(numbers);
        long n = stream1.count();
        System.out.println("Number of elements in numbers: " + n);      // 3

        // Re-creating numbers using static method of Stream: Stream.of
        Stream<Double> stream2 = Stream.of(numbers);
        System.out.println("Number of elements in numbers: " + n);      // 3

        // Using Stream.of
        Stream<String> stream3 = Stream.of("America", "Europe");
        System.out.println("Number of elements: " + stream3.count());   // 2
        System.out.println();
    }

    public static void fromCollection() {
        System.out.println("fromCollection():");
        // stream() is a default method in the Collection interface
        List<String> animals = Arrays.asList("cat", "dog", "sheep");
        Stream<String> stream1 = animals.stream();
        System.out.println("Number of animal elements: " + stream1.count());        // 3

        // Map is not a Collection, use the Map method entrySet() to return a Set view of the Map
        Map<String, Integer> namesToAges = new HashMap<>();
        namesToAges.put("Mike", 22);
        namesToAges.put("Mary", 24);
        namesToAges.put("Alice", 31);
        Set<Map.Entry<String, Integer>> entrySet = namesToAges.entrySet();
        long n = entrySet
                .stream()
                .count();
        System.out.println("Number of entries: " + n);      // 3
        System.out.println();
    }

    public static void fromPrimitives() {
        System.out.println("fromPrimitives():");
        int[] intArray = {1, 2, 3};
        double[] doubleArray = {1.1, 2.2, 3.3};
        long[] longArray = {1L, 2L, 3L};

        // Using Arrays.stream
        IntStream intStream1 = Arrays.stream(intArray);
        DoubleStream doubleStream1 = Arrays.stream(doubleArray);
        LongStream longStream1 = Arrays.stream(longArray);
        System.out.println("Count of elements: " + intStream1.count() + ", " + doubleStream1.count()
                        + ", " + longStream1.count());              // 3, 3, 3

        // Using the interface's static of()
        IntStream intStream2 = IntStream.of(intArray);
        DoubleStream doubleStream2 = DoubleStream.of(doubleArray);
        LongStream longStream2 = LongStream.of(longArray);
        System.out.println("2nd count of elements: " + intStream2.count() + ", " + doubleStream2.count()
                + ", " + longStream2.count());              // 3, 3, 3

        System.out.println();
    }

    public static void infiniteStreams() {
        System.out.println("infiniteStreams():");
        // Using Stream.generate(Supplier). Be careful! Infinite!
        Stream<Integer> randoms = Stream.generate(() -> {
            return (int) (Math.random() * 10);
        });
        randoms.limit(5).forEach(System.out::println);
        System.out.println();

        // Using Stream.iterate(seed, UnaryOperator) pre-Java9. Be careful! Infinite
        Stream<Integer> oddNums_old = Stream.iterate(1, n -> n + 2);
        oddNums_old.limit(5).forEach(System.out::println);
        System.out.println();

        // Java9, Stream.iterate(seed, Predicate, UnaryOperator). Can be made finite!
        Stream<Integer> oddNums = Stream.iterate(1, n -> n < 9, n -> n + 2);
        oddNums.forEach(System.out::println);
    }
}
