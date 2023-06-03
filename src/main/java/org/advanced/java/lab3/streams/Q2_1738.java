package org.advanced.java.lab3.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;

public class Q2_1738 {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3);

        // Sum via IntStream
        int sum = list1.stream()
                .mapToInt(i -> i)
                .sum();
        System.out.println(sum);

        // Max via IntStream
        list1 = Arrays.asList(1, 2, 3);
        OptionalInt max = list1.stream()
                .mapToInt(i -> i)
                .max();
        max.ifPresent(System.out::println);

        // Oldest Person
        List<Person> personList = Arrays.asList(
                new Person("Alan", "Burke", 22),
                new Person("Zoe", "Peters", 20),
                new Person("Peter", "Castle", 29));
        Person oldest = personList.stream()
                .max(Comparator.comparing(Person::getAge))
                .get();
        System.out.println(oldest);

        List<Integer> list2 = List.of(10, 47, 33, 23);

        // Reduce using Optional
        Integer maxVal1 = list2.stream()
                .reduce((a, b) -> a > b ? a : b)
                .get();
        System.out.println(maxVal1);

        // Reduce using identity
        Integer maxVal2 = list2.stream()
                .reduce(Integer.MIN_VALUE, (a, b) -> Integer.max(a, b));
        System.out.println(maxVal2);
    }
}
