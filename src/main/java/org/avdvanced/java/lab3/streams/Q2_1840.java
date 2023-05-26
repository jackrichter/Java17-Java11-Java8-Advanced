package org.avdvanced.java.lab3.streams;

import java.util.Arrays;
import java.util.List;

public class Q2_1840 {
    public static void main(String[] args) {
        List<Integer> ls = Arrays.asList(11, 11, 22, 33, 33, 55, 66);
        boolean anyMatch = ls.stream()
                .distinct()
                .anyMatch(i -> i == 11);
        System.out.println(anyMatch);

        boolean noneMatch = ls.stream()
                .distinct()
                .noneMatch(i -> i % 11 > 0);
        System.out.println(noneMatch);
    }
}
