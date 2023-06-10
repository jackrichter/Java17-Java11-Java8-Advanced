package org.advanced.java.lambdas;

import java.time.LocalTime;
import java.util.*;
import java.util.function.*;

public class FI_from_API {
    public static void main(String[] args) {
        predicate();
        supplier();
        consumer();
        biConsumer();
        function();
        biFunction();
        unaryOperator();
        binaryOperator();
    }

    private static void predicate() {
        // Predicate<T>, boolean test(T t) -> Takes a parameter of Type T and returns a boolean
        Predicate<String> pStr = s -> s.contains("City");
        String str = "Vatican City";
        System.out.println(String.format("%s contains City? %b", str, pStr.test(str)));
    }

    private static void supplier() {
        // Supplier<T>, T get() -> Takes no parameters and return Type T
        Supplier<StringBuilder> supSB = () -> new StringBuilder();
        String result = String.valueOf(supSB.get().append("JR"));
        System.out.println("Supplier SB: " + result);

        Supplier<LocalTime> supTime = () -> LocalTime.now();
        System.out.println("Supplier Time: " + supTime.get());

        Supplier<Double> supRandom = () -> Math.random();
        System.out.println("Supplier Random Double: " + supRandom.get());
    }

    private static void consumer() {
        // Consumer<T>, void accept(T t) -> Takes a parameter of Type T and returns void
        Consumer<String> printConsumer = s -> System.out.println(s);
        printConsumer.accept("To Be or Not to Be. That is the question.");

        List<String> names = new ArrayList<>();
        names.add("Mary");
        names.add("John");
        names.forEach(printConsumer);
    }

    private static void biConsumer() {
        // BiConsumer<T, U>, void accept(T t, U u) -> It Takes two parameters of Types T and U and returns void
        Map<String, String> capitals = new HashMap<>();
        BiConsumer<String, String> biConsumer = (key, value) -> capitals.put(key, value);
        biConsumer.accept("Israel", "Jerusalem");
        biConsumer.accept("Brazil", "Brasilia");
        biConsumer.accept("Sweden", "Stockholm");
        biConsumer.accept("Denmark", "Copenhagen");

        /**
         * OBS!!!
         */
        Consumer<Map.Entry<String, String>> printC = (s) -> System.out.println(s);
        Set<Map.Entry<String, String>> set = capitals.entrySet();
        set.stream()
                .forEach(printC);
    }

    private static void function() {
        // Function<T, R>, R apply(T t) -> Takes a parameter of Type T and returns a Type R
        Function<String, Integer> fn1 = s -> s.length();
        System.out.println("Length: " + fn1.apply("Brazil"));
    }

    private static void biFunction() {
        // BiFunction<T, U, R>, R apply(T t, U u) -> Takes two parameters: T and U and returns a Type R
        BiFunction<String, String, Integer> biFn1 = (s1, s2) -> s1.length() + s2.length();
        System.out.println("BiFunction: Length is " + biFn1.apply("William", "Shakespeare"));

        BiFunction<String, String, String> biFn2 = (s1, s2) -> s1.concat(s2);
        System.out.println("BiFunction: Concatenation: " + biFn2.apply("William ", "Shakespeare"));
    }

    private static void unaryOperator() {
        // UnaryOperator<T> extends Function<T, T>, T apply(T t) -> Takes a parameter of Type T and returns the same Type T
        UnaryOperator<String> unOp = name -> "My name is " + name;
        System.out.println("UnaryOperator: " + unOp.apply("Jack"));
    }

    private static void binaryOperator() {
        // BinaryOperator<T> extends BiFunction<T, T, T>, T apply(T t1, T t2) -> Takes two parameters of the same Type T and returns the same Type T
        BinaryOperator<String> binOp = (s1, s2) -> s1.concat(s2);
        System.out.println("BinaryOperator: Concatination: " + binOp.apply("William ", "Shakespeare"));
    }
}
