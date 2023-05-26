package org.avdvanced.java.lab3.streams;

import java.util.Optional;

public class Q2_1849 {
    public static void main(String[] args) {
        Optional<Double> price = Optional.ofNullable(20.0);
        price.ifPresent(System.out::println);
        System.out.println(price.orElse(0.0));
        System.out.println(price.orElseGet(() -> null));

        Optional<Double> price2 = Optional.ofNullable(null);
        System.out.println(price2);
        if (price2.isEmpty()) {
            System.out.println("empty");
        }
        Double val = price2.orElseGet(() -> 0.0);       // supplier
        System.out.println(val);
        Double x = price2.orElse(44.0);


        Optional<Double> price3 = Optional.ofNullable(null);
        Double z = price3.orElseThrow(() -> new RuntimeException("Bad Code"));      // supplier
        System.out.println(z);
    }
}
