package org.advanced.java.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class IntermediateOperations {
    public static void main(String[] args) {
        doFilter();
        doDistinct();
        doLimit();
        doMap();
        doFlatMap();
        doSorted1();
        doSorted2();
        doSorted3();
    }

    public static void doFilter() {
        // The filter() method returns a Stream with the elements that
        // MATCH the given predicate.
        // Stream<T> filter(Predicate)
        System.out.println("doFilter():");
        Stream.of("Skåne", "Småland", "Halland")
                .filter(county -> county.length() > 5)
                .forEach(county -> System.out.print(county + " "));
        System.out.println();
    }

    public static void doDistinct() {
        // distinct() returns a stream with duplicate values removed.
        // It is case-sensitive.
        // Stream<T> distinct()
        System.out.println("doDistinct():");
        Stream.of("eagle", "eagle", "EAGLE")
                .peek(s -> System.out.print(" 1." + s))
                .distinct()
                .forEach(s -> System.out.print(" 2." + s));                 // 1.eagle 2.eagle 1.eagle 1.EAGLE 2.EAGLE
        System.out.println();
    }

    private static void doLimit() {
        // limit is a short-circuiting stateful
        // intermediate operation. Lazy evaluation - 66, 77, 88 and 99
        // are not streamed as they are not needed (limit of 2 i.e. 44 and 55).
        // Stream<T> limit(long maxSize)
        System.out.println("doLimit():");
        System.out.println("Prior: 11,22,33,44,55,66,77,88,99");
        System.out.print("After:");
        Stream.of(11,22,33,44,55,66,77,88,99)
                .peek(n -> System.out.print(" A." + n))
                .filter(n -> n > 40)
                .peek(n -> System.out.print(" B." + n))
                .limit(2)
                .forEach(n -> System.out.print(" C." + n));                 // After: A.11 A.22 A.33 A.44 B.44 C.44 A.55 B.55 C.55
        System.out.println();
    }

    private static void doMap() {
        // The map () is for transforming data. It produces one output value for each input value.
        // <R> Stream<R> map(Function<T,R> mapper)
        //            R apply(T t)
        System.out.println("doMap():");
        System.out.println("Prior: \"book\", \"pen\", \"ruler\"");
        System.out.print("After: ");
        Stream.of("book", "pen", "ruler")
                .map(s -> s.length())
                .forEach(s -> System.out.print(s + ", "));                  // After: 4, 3, 5
        System.out.println();
    }

    private static void doFlatMap() {
        // flatMap () takes each element in the stream
        // such as Stream<List<String>> and makes any elements it contains
        // top level elements in a single stream e.g. Stream<String>.
        // In other words, going from a List<String> to a String.
        // <R> Stream<R> map(Function<T,R> mapper)
        System.out.println("doFlatMap():");
        System.out.println("Before: (Sean, Desmond) (Mary, Ann)");
        System.out.print("After: ");
        List<String> list1 = Arrays.asList("Sean", "Desmond");
        List<String> list2 = Arrays.asList("Mary", "Ann");
        Stream<List<String>> listStream = Stream.of(list1, list2);

        listStream
                .flatMap(list -> list.stream())
                .forEach(s -> System.out.print(s + ", "));                  // After: Sean, Desmond, Mary, Ann
        System.out.println();

        // Another example
        System.out.println("Before: (1, 2) (3, 4)");
        System.out.print("After: ");
        List<Integer> together = Stream.of(asList(1, 2), asList(3, 4))      // Stream of List<Integer>
                .flatMap(List::stream)
                .map(integer -> integer + 1)
                .collect(Collectors.toList());
        System.out.println(together);                   // After: [2, 3, 4, 5]
    }

    public static void doSorted1() {
        // Stream<T> sorted(Comparator<T> comparator)
        // Stream<T> sorted() -> Natural Order (String, Integer, etc.)
        System.out.println("doSorted()");
        System.out.println("Before: \"Tim\", \"Jim\", \"Peter\", \"Ann\", \"Mary\"");
        System.out.print("After: ");
        Stream.of("Tim", "Jim", "Peter", "Ann", "Mary")
                .filter(name -> name.length() == 3)
                .sorted()
                .limit(2)
                .forEach(name -> System.out.print(name + " "));             // Ann, Jim
        System.out.println();
    }

    public static void doSorted2() {
        Person p1 = new Person("John", 31);
        Person p2 = new Person("Mary", 25);
        Stream.of(p1, p2)
                .sorted(Comparator.comparing(Person::getAge))
                .forEach(person -> System.out.print(person + " "));         // Person{name='Mary', age=25} Person{name='John', age=31}
        System.out.println();
    }

    public static void doSorted3() {
        System.out.println("Before: \"Toby\", \"Anna\", \"Leroy\", \"Alex\"");
        System.out.print("After: ");
        Stream.of("Toby", "Anna", "Leroy", "Alex")
                .filter(name -> name.length() == 4)
                .sorted()
                .limit(2)
                .forEach(s -> System.out.print(s + ", "));                  // After: Alex, Anna
    }
}

