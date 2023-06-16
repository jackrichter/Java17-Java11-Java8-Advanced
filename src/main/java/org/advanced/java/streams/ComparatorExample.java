package org.advanced.java.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ComparatorExample {
    public static void main(String[] args) {
        System.out.println("ComparatorExample:");
        // Optional<Person> max(Comparator)
        // Comparator.comparing(Comparable)
        // Integer is a Comparable
        List<Person> people = Arrays.asList(
                new Person("Allan", 22),
                new Person("Zoe", 20),
                new Person("Peter", 29)
        );
        Person oldest = people.stream()
                .max(Comparator.comparing(p -> p.getAge()))
                .get();
        System.out.println("Oldest: " + oldest.getName() + ", " + oldest.getAge());

        List<Integer> integerList = Arrays.asList(22, 13, 34);
        integerList.stream()
                .sorted(Comparator.comparing(n -> n))
                .forEach(integer -> System.out.print(integer + " "));         // 13 22 34
        System.out.println();

        List<String> names = Arrays.asList("Xavier", "Abraham", "Ziggy", "Aaron");
        names.stream()
                .sorted((s1, s2) -> s1.compareTo(s2))
                .forEach(name -> System.out.print(name + " "));             // Aaron Abraham Xavier Ziggy
        System.out.println();

        // sum
        List<Integer> nums = Arrays.asList(2, 5, 7);
        int sum = nums.stream()
                .mapToInt(i -> i)
                .sum();
        System.out.println(sum);                        // 14
    }
}
