package org.advanced.java.lambdas;

import java.util.function.Predicate;

public class TestPredicate {

    public static void main(String[] args) {
        Evaluate<Integer> lambda = i -> i < 0;
        System.out.println("Evaluate: " + lambda.isNegative(-1));
        System.out.println("Evaluate: " + lambda.isNegative(1));

        Predicate<Integer> predicate = i -> i < 0;
        System.out.println("Predicate: " + predicate.test(-1));
        System.out.println("Predicate: " + predicate.test(1));

        int x = 4;
        Predicate<Integer> evenPred = i -> i % 2 == 0;
        System.out.println(String.format("Is %d even?, answer: %b", x, check(x, evenPred)));

        int y = 11;
        System.out.println(String.format("Is %d even?, answer: %b", y, check(y, evenPred)));

        String name = "Mr. Joe Bloggs";
        Predicate<String> mrPred = s -> s.startsWith("Mr.");
        System.out.println(String.format("Does %s starts with Mr.? %b", name, check(name, mrPred)));

        name = "Mrs. Jane Bloggs";
        System.out.println(String.format("Does %s starts with Mr.? %b", name, check(name, mrPred)));
    }

    private static <T> boolean check(T t, Predicate<T> predicate) {
        return predicate.test(t);
    }
}
