package org.avdvanced.java.lab2.methodreference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;

public class LambdasAndMethodReferences {

    public static void main(String[] args) {
        staticMR();
        boundMR();
        unboundMR();
        constructorMR();
    }

    public static void staticMR() {
        List<Integer> integers = Arrays.asList(1, 2, 7, 4, 5);
        Consumer<List<Integer>> lambda = l -> Collections.sort(l);
        System.out.println("The unsorted list of integers: " + integers);
        lambda.accept(integers);
        System.out.println("The sorted list of integers: " + integers);
        System.out.println();

        integers = Arrays.asList(1, 2, 7, 4, 5);
        Consumer<List<Integer>> methodRef = Collections::sort;
        System.out.println("The unsorted list: " + integers);
        lambda.accept(integers);
        System.out.println("The sorted list using Method Reference: " + integers);
        System.out.println();
    }

    public static void boundMR() {
        final String name = "Mr. Joe Bloggs";

        Predicate<String> lambda = prefix -> name.startsWith(prefix);
        System.out.println("Lambda: prefix Mr." + lambda.test("Mr."));
        System.out.println("Lambda: prefix Ms." + lambda.test("Ms."));
        System.out.println();

        Predicate<String> methodRef = name::startsWith;
        System.out.println("MetbhodRef: prefix Mr." + methodRef.test("Mr."));
        System.out.println("MetbhodRef: prefix Ms." + methodRef.test("Ms."));
        System.out.println();
    }

    public static void unboundMR() {
        Predicate<String> lambda = s -> s.isEmpty();
        System.out.println("Lambda unbound: " + lambda.test(""));
        System.out.println("Lambda unbound: " + lambda.test("xyz"));
        System.out.println();

        Predicate<String> methodRef = String::isEmpty;
        System.out.println("MethodRef unbound: " + methodRef.test(""));
        System.out.println("MethodRef unbound: " + methodRef.test("xyz"));
        System.out.println();

        BiPredicate<String, String> biLambda = (s, p) -> s.startsWith(p);
        System.out.println("BiLambda unbound: " + biLambda.test("Mr. Joe Bloggs", "Mr."));
        System.out.println("BiLambda unbound: " + biLambda.test("\"Mr. Joe Bloggs", "Ms."));
        System.out.println();

        BiPredicate<String, String> biMethodRef = String::startsWith;
        System.out.println("BiMethodRef unbound: " + biMethodRef.test("Mr. Joe Bloggs", "Mr."));
        System.out.println("BiMethodRef unbound: " + biMethodRef.test("Mr. Joe Bloggs", "Ms."));
        System.out.println();
    }

    public static void constructorMR() {
        Supplier<List<String>> lambda = () -> new ArrayList<>();
        List<String> list = lambda.get();
        list.add("Lambda");
        list.forEach(s -> System.out.println(s));
        System.out.println();

        Supplier<List<String>> methodRef = ArrayList::new;
        list = methodRef.get();
        list.add("Method Reference");
        list.forEach(s -> System.out.println(s));
        System.out.println();

        Function<Integer, List<String>> lambda2 = i -> new ArrayList<>(i);
        list = lambda2.apply(10);
        list.add("Lambda");
        list.forEach(s -> System.out.println(s));
        System.out.println();

        Function<Integer, List<String>> methodRef2 = ArrayList::new;
        list = methodRef2.apply(15);
        list.add("Method Reference");
        list.forEach(s -> System.out.println(s));
    }
}
