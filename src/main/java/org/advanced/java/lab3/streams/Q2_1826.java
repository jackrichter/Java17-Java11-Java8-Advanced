package org.advanced.java.lab3.streams;

import java.util.Optional;

public class Q2_1826 {
    public static void main(String[] args) {
        Optional<String> grade1 = getGrade(50);
        Optional<String> grade2 = getGrade(55);

        System.out.println(grade1.orElse("UNKNOWN"));

        if (grade2.isPresent()) {
            grade2.ifPresent(str -> System.out.println(str));
        } else {
            System.out.println(grade2.orElse("EMPTY"));
        }
    }

    public static Optional<String> getGrade(int marks) {
        Optional<String> grade = Optional.empty();

        if (marks > 50) {
            grade = Optional.of("PASS");
        } else {
            grade = Optional.of("FAIL");
        }

        return grade;
    }
}
