package org.advanced.java.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TerminalOperations {
    public static void main(String[] args) {
        doReduce1();
        doReduce2();
        doReduce3();
        doCollect();
        doForEach();
        doMatches();
        doAnyAndFirst();
        doCount();
        doMinAndMax();
    }

    public static void doReduce1() {
        // The reduce() method combines a stream into a single object.
        // It is a reduction, which means it processes all elements.
        // The most common way of doing a reduction is to start with
        // an initial value and keep merging it with the next value.
        //
        // T reduce(T identity, BinaryOperator<T> accumulator)
        //      BinaryOperator<T> functional method:
        //          T apply(T, T);
        String name = Stream.of("s", "e", "a", "n")
                .reduce("", (s, u) -> s + u);
        System.out.println(name);       // sean

        Integer product = Stream.of(2, 3, 4)
                .reduce(1, (a, b) -> a * b);
        System.out.println(product);    // 24
    }
    public static void doReduce2() {
        // When you leave out the identity, an Optional is
        // returned because there may not be any data.
        // Useful when dealing with the same type.
        //
        // Optional<T> reduce(BinaryOperator<T> accumulator)
        //      BinaryOperator<T> functional method:
        //          T apply(T, T);
        BinaryOperator<Integer> biOp = (a, b) -> a + b;         // Accumulator
        Stream<Integer> empty = Stream.empty();
        Stream<Integer> one = Stream.of(6);
        Stream<Integer> multi = Stream.of(1, 2, 3);

        empty.reduce(biOp).ifPresent(System.out::println);      // nothing
        one.reduce(biOp).ifPresent(System.out::println);        // 6
        multi.reduce(biOp).ifPresent(System.out::println);      // 12

        // The common version of reduce that shows that the version without identity is needed
        Integer reduce = Stream.of(1, 1, 1)             // Obs. Not an Optional returned
                .filter(n -> n > 20)                           // Even with a filter, the value of 1 would have been returned
                .reduce(1, (a, b) -> a);                // The value of 1 is always returned
        System.out.println(reduce);                            // 1

    }
    public static void doReduce3() {
        // We use this version when we are dealing with different types,
        // allowing us to create intermediate reductions and then combine
        // them at the end. This is useful when working with parallel
        // streams - the streams can be decomposed and reassembled by
        // separate threads
        //
        //<U> U reduce(U identity, BiFunction accumulator, BinaryOperator combiner)
        Stream<String> stream = Stream.of("car", "bus", "train", "aeroplane");
        int length = stream.reduce(0,    // The identity
                (i, s) -> i + s.length(),       // The accumulator of different types (BiFunction)
                (i1, i2) -> i1 + i2);           // The combiner of the two accumulated lengths
        System.out.println(length);     // 20
    }

    public static void doCollect() {
        // Also called Reduction of Common Mutable Objects.
        //
        // Common Mutable Objects are: StringBuilder and ArrayList!!
        //
        // This version is used when you want complete control over
        // how collecting should work (Not using Collectors). The accumulator adds an element
        // to the collection, e.g., the next String to the StringBuilder.
        // The combiner takes two collections and merges them. It is useful
        // in parallel processing
        //
        // StringBuilder collect(Supplier<StringBuilder> supplier,
        //               BiConsumer<StringBuilder,String> accumulator
        //               BiConsumer<StringBuilder,StringBuilder> combiner)
        StringBuilder word = Stream.of("ad", "jud", "i", "cate")
                .collect(() -> new StringBuilder(),
                        (sb, str) -> sb.append(str),
                        (sb1, sb2) -> sb1.append(sb2));
        System.out.println(word);           // adjudicate
    }

    public static void doForEach() {
        Stream<String> names = Stream.of("Cathy", "Pauline", "Zoe");
        names.forEach(System.out::print);       //CathyPaulineZoe
        System.out.println();
    }

    public static void doMatches() {
        // boolean anyMatch(Predicate)
        // boolean allMatch(Predicate)
        // boolean noneMatch(Predicate)
        Predicate<String> predicate = s -> s.startsWith("A");
        List<String> list = Arrays.asList("Alan", "Brian", "Colin");

        System.out.println(list.stream().anyMatch(predicate));          // true
        System.out.println(list.stream().allMatch(predicate));          // false
        System.out.println(list.stream().noneMatch(predicate));         // false
    }

    public static void doAnyAndFirst() {
        // Optional<T> findAny()
        // Optional<T> findFirst()
        Optional<String> any = Stream.of("John", "Paul").findAny();
        Optional<String> first = Stream.of("John", "Paul").findFirst();

        any.ifPresent(System.out::print);
        System.out.print(" and ");
        first.ifPresent(System.out::println);
    }

    public static void doCount() {
        long count = Stream.of("dog", "cat")
                .count();
        System.out.println(count);          // 2
    }

    public static void doMinAndMax() {
        // Optional<T> min(Comparator)
        // Optional<T> max(Comparator)
        Optional<String> min = Stream.of("cow", "horse", "pig")
                .min((s1, s2) -> s1.length() - s2.length());            // cow
        min.ifPresent(System.out::println);

        Optional<Integer> max = Stream.of(4, 6, 2, 9, 9)
                .max((i1, i2) -> i1 - i2);                              // 9
        max.ifPresent(System.out::println);
    }
}
