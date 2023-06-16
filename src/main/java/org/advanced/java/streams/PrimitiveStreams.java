package org.advanced.java.streams;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PrimitiveStreams {
    public static void main(String[] args) {
        creatingPrimitiveStreams();
        usingSum();
        maxMinAverage();
        stats(IntStream.empty());
        stats(IntStream.of(5, 10, 15, 20));
        mappingObjectToPrimitiveStreams();
        mappingPrimitiveToObjectStreams();
        range();
    }

    public static void creatingPrimitiveStreams() {
        System.out.println("creatingPrimitiveStreams():");
        // Using Arrays.stream
        IntStream intStream1 = Arrays.stream(new int[] {1, 2, 3});
        DoubleStream doubleStream1 = Arrays.stream(new double[] {1.0, 2.0, 3.0});
        LongStream longStream1 = Arrays.stream(new long[] {1L, 2L, 3L});

        System.out.println(intStream1.count() + ", " + doubleStream1.count() + ", " + longStream1.count());     // 3, 3, 3

        // Using ...of
        IntStream intStream2 = IntStream.of(1, 2, 3);
        DoubleStream doubleStream2 = DoubleStream.of(1, 2, 3);
        LongStream longStream2 = LongStream.of(1, 2, 3);

        intStream2.forEach(System.out::print);                      // 123
        System.out.println();
        doubleStream2.forEach(System.out::print);                   // 1.02.03.0
        System.out.println();
        longStream2.forEach(System.out::print);                     // 123
        System.out.println();
    }

    public static void usingSum() {
        System.out.println("usingSum():");
        // Using reduce
        // Integer reduce(Integer identity, BinaryOperator accumulator)
        // BinaryOperator extends BiFunction<T,T,T>
        //          T apply(T,T)
        Stream<Integer> nums = Stream.of(1, 2, 3);
        int tot = nums.reduce(0, (n1, n2) -> n1 + n2);
        System.out.println("Total after reduce: " + tot);               // 6

        // Using IntStream and sum
        // IntStream mapToInt(ToIntFunction)
        //     int applyAsInt(T value);
        IntStream ints = Stream.of(1, 2, 3).mapToInt(i -> i);   // Unboxing: Integer -> int
        tot = ints.sum();
        System.out.println("Total after sum: " + tot);               // 6
    }

    private static void maxMinAverage() {
        System.out.println("maxMinAverage():");
        // max
        OptionalInt max = IntStream.of(10, 20, 30)
                .max();
        max.ifPresent(System.out::println);                         // 30
        // min
        OptionalDouble min = DoubleStream.of(10.0, 20.0, 30.0)
                .min();
        System.out.println(min.orElseThrow());                      // 10.0
        // average
        OptionalDouble average = LongStream.of(10L, 20L, 30L)
                .average();
        System.out.println(average.orElseGet(() -> (Math.random()) * 10));      // 20.0
    }

    public static void stats(IntStream nums) {
        System.out.println("stats(): ");
        IntSummaryStatistics intStats = nums.summaryStatistics();

        System.out.println(intStats.getMin());              // Integer_MAX_VALUE if empty, else 5
        System.out.println(intStats.getMax());              // Integer_MIN_VALUE if empty, else 20
        System.out.println(intStats.getAverage());          // 0.0 if empty, else 12.5
        System.out.println(intStats.getCount());            // 0 if empty, else 4
        System.out.println(intStats.getSum());              // 0 if empty, else 50
    }

    public static void mappingObjectToPrimitiveStreams() {
        System.out.println("mappingObjectToPrimitiveStreams():");
        // Stream<T> to Stream<T>
        // map(Function)
        // Function<T, R>
        // String apply(String s)
        System.out.println("Before: \"ash\", \"beech\", \"sycamore\"");
        System.out.print("After: ");
        Stream.of("ash", "beech", "sycamore")
                .map(s -> s.toUpperCase())
                .forEach(s -> System.out.print(s + " "));   // ASH BEECH SYCAMORE
        System.out.println();

        // Stream<T> to DoubleStream
        // mapToDouble(ToDoubleFunction)
        //   double applyAsDouble(T)
        System.out.println("Before: \"ash\", \"beech\", \"sycamore\"");
        System.out.print("After: ");
        Stream.of("ash", "beech", "sycamore")
                .mapToDouble(s -> s.length())
                .forEach(d -> System.out.print(d + " "));   // 3.0 5.0 8.0
        System.out.println();

        // Stream<T> to IntStream
        // mapToInt(ToIntFunction)
        //   int applyAsInt(T)
        System.out.println("Before: \"ash\", \"beech\", \"sycamore\"");
        System.out.print("After: ");
        Stream.of("ash", "beech", "sycamore")
                .mapToInt(s -> s.length())
                .forEach(d -> System.out.print(d + " "));   // 3 5 8
        System.out.println();

        // Stream<T> to LongStream
        // mapToLong(ToLongFunction)
        //   long applyAsLong(T)
        System.out.println("Before: \"ash\", \"beech\", \"sycamore\"");
        System.out.print("After: ");
        Stream.of("ash", "beech", "sycamore")
                .mapToLong(s -> s.length())                 // Upcast from int to long in the background
                .forEach(d -> System.out.print(d + " "));   // 3 5 8
        System.out.println();
    }

    public static void mappingPrimitiveToObjectStreams() {
        System.out.println("mappingPrimitiveToObjectStreams():");
        // IntStream to Stream<T>
        // mapToObj(IntFunction<R>)
        //   R apply(int value)
        System.out.println("Before: 1 2 3");
        System.out.print("After: ");
        Stream<String> stream = IntStream.of(1, 2, 3)
                .mapToObj(n -> "Number:" + n);
        stream.forEach(s -> System.out.print(s + " "));
        System.out.println();

        // IntStream to DoubleStream
        // mapToDouble(IntToDoubleFunction)
        //   double applyAsDouble(int val)
        System.out.println("Before: 1 2 3");
        System.out.print("After: ");
        DoubleStream doubleStream = IntStream.of(1, 2, 3)
                .mapToDouble(n -> n);// Upcast from int to double in the background
        doubleStream.forEach(d -> System.out.print(d + " "));           // 1.0 2.0 3.0
        System.out.println();

        // IntStream to LongStream
        // mapToLong(IntToLongFunction)
        //   long applyAsLong(int val)
        System.out.println("Before: 1 2 3");
        System.out.print("After: ");
        LongStream longStream = IntStream.of(1, 2, 3)
                .mapToLong(n -> (long) n);                      // Cast Isn't Needed!
        longStream.forEach(l -> System.out.print(l + " "));
        System.out.println();

        // IntStream to IntStream
        //  map(IntUnaryOperator)
        //    int applyAsInt(int)
        System.out.println("Before: 1 2 3");
        System.out.print("After: ");
        IntStream.of(1, 2, 3)
                .map(operand -> operand * 2)
                .forEach(i -> System.out.print(i + " "));      // 2 4 6
        System.out.println();
    }

    public static void range() {
        System.out.println("range():");
        // startInclusive, endExclusive
        IntStream.range(1, 5)
                .forEach(n -> System.out.print(n + " "));       // 1 2 3 4
        System.out.println();

        // startInclusive, endInclusive
        IntStream.rangeClosed(1, 5)
                .forEach(n -> System.out.print(n + " "));       // 1 2 3 4 5
        System.out.println();
    }
}
