package org.avdvanced.java.lab3.streams;


import java.util.Arrays;
import java.util.List;

public class Q2_1810 {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Bob", "Kelly", 31),
                new Person("Paul", "Landers", 32),
                new Person("John", "Paters", 33));

        double avg = persons.stream()
                .filter(p -> p.getAge() < 30)
                .mapToInt(Person::getAge)
                .average()
                .orElse(0.0);
        System.out.println(avg);
    }
}
