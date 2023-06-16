package org.advanced.java.streams;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class Optionals {
    public static void main(String[] args) {
        doOptionalAverage();
        doOptionalPrimitiveAverage();
        doOptionalNull();
    }

    public static void doOptionalAverage() {
        System.out.println("doOptionalAverage():");
        Optional<Double> optAvg = calcAverage(50, 60, 70);
        if (optAvg.isPresent()) {
            System.out.println(optAvg.get());           // 60.0
        }

        // void ifPresent(Consumer c)
        optAvg.ifPresent(System.out::println);          // 60.0

        // T orElse(T t)
        System.out.println(optAvg.orElse(Double.NaN));  // 60.0

        // returning an empty Optional
        Optional<Double> emptyOpt = calcAverage();
        System.out.println(emptyOpt.orElse(Double.NaN));  // NaN

        // T orElseGet(Supplier<T> s)
        System.out.println(emptyOpt.orElseGet(() -> Math.random()));    // some random double between 0 and 1
    }

    public static void doOptionalPrimitiveAverage() {
        System.out.println("doOptionalPrimitiveAverage():");
        OptionalDouble optAvg = IntStream.rangeClosed(1, 10)
                .average();
        optAvg.ifPresent(d -> System.out.println(d));               // 5.5
        System.out.println(optAvg.getAsDouble());                   // 5.5
        System.out.println(optAvg.orElseGet(() -> Double.NaN));     // 5.5
    }

    public static void doOptionalNull() {
        System.out.println("doOptionalNull():");
        Optional<String> optJR = dealWithNull("JR");
        optJR.ifPresent(System.out::println);                       // JR

        Optional<String> optNull = dealWithNull(null);
        System.out.println(optNull.orElseGet(() -> "Empty optional"));      // Empty optional
    }

    private static Optional<Double> calcAverage(int... scores) {
        if (scores.length == 0) {
            return Optional.empty();
        }
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        double average = (double) sum / scores.length;

        return Optional.of(average);
    }

    private static Optional<String> dealWithNull(String param) {
//        Optional<String> optRet = param == null ? Optional.empty() : Optional.of(param);

        // Alternative
        Optional<String> optRet = Optional.ofNullable(param);       // same as above

        return optRet;
    }
}
