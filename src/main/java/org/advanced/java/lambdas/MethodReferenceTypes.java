package org.advanced.java.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;

public class MethodReferenceTypes {
    public static void main(String[] args) {
        boundMethodReference();
        unBoundMethodReference();
        staticMethodReference();
        constructorMethodReference();
    }

    public static void boundMethodReference() {
        String name = "Mr. Joe Bloggs";

        Supplier<String> lowercaseLambda = () -> name.toLowerCase();
        Supplier<String> lowercaseMR = name::toLowerCase;
        System.out.println(lowercaseLambda.get());
        System.out.println(lowercaseMR.get());

        Predicate<String> titleLambda = (s) -> name.startsWith(s);
        Predicate<String> titleMR = name::startsWith;               // Here the context determines which overloaded startsWith is used
        System.out.println(titleLambda.test("Mr."));
        System.out.println(titleMR.test("Mrs."));
    }

    public  static void unBoundMethodReference() {
        Function<String, String> uppercaseLambda = s -> s.toUpperCase();
        Function<String, String> uppercaseMR =  String::toUpperCase;

        System.out.println(uppercaseLambda.apply("Jack"));
        System.out.println(uppercaseMR.apply("Jack"));

        BiFunction<String, String, String> concatLambda = (s1, s2) -> s1.concat(s2);
        BiFunction<String, String, String> concatMR = String::concat;

        System.out.println(concatLambda.apply("John ", "Kennedy"));
        System.out.println(concatMR.apply("John ", "Kennedy"));
    }

    public static void staticMethodReference() {
        // An example static method is Collections.sort(List)
        // Context: Consumer takes one parameter => sort(List) is used, as opposed to sort(List, Comparator)
        Consumer<List<Integer>> sortedLambda = list -> Collections.sort(list);
        Consumer<List<Integer>> sortedMR = Collections::sort;      // From the Context it knows it's a Consumer that takes one argument: a List of Integers, therefore Collections(List l)

        List<Integer> numbersLambda = Arrays.asList(2,1,5,4,9);
        List<Integer> numbersMR = Arrays.asList(8,12,4,3,7);

        sortedLambda.accept(numbersLambda);
        sortedMR.accept(numbersMR);

        System.out.println(numbersLambda);
        System.out.println(numbersMR);

    }

    public static void constructorMethodReference() {

    }
}
