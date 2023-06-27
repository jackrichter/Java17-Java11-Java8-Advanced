package org.advanced.java.java17.pattern_matching_switch;

public class PatternMatching {
    public static void main(String[] args) {
        whatType("ABC");
        whatType(123);
        whatType(null);
        whatType(32.39);    //Double

        infoOnType("ABC");
        infoOnType("abc");
        infoOnType(12);
        infoOnType(8);
    }

    public static void whatType(Object o) {
        switch (o) {
            case String s -> System.out.println("String");
            case Integer i -> System.out.println("Integer");
            case null -> System.out.println("Null");
            default -> System.out.println("Not recognized");
        }
    }

    public static void infoOnType(Object o) {
        switch (o) {
            case String s && s.startsWith("A") -> System.out.println("String beginning with A: " + s);
            case Integer i && i.intValue() > 10 -> System.out.println("Integer > 10: " + i);
            case null -> System.out.println("Null");
            default -> System.out.println("Not recognized");
        }
    }
}
