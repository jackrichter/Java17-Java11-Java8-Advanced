package org.advanced.java.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Collect_With_Collectors {
    public static void main(String[] args) {
        collectToStringObject();
        collectToPrimitive();
        doCollectToMap1();
        doCollectToMap2();
        doCollectToMap3();
        doGroupingBy1();
        doGroupingBy2();
        doGroupingBy3();
        doPartitioning1();
        doPartitioning2();
        doPartitioning3();
        doPartitioning4();
    }

    public static void collectToStringObject() {
        String str = Stream.of("cake", "biscuits", "apple tart")
                .collect(Collectors.joining(", "));

        System.out.println("Collectors.joining():");
        System.out.println(str);        // cake, biscuits, apple tart
        System.out.println();
    }

    public static void collectToPrimitive() {
        // Collectors.averagingInt(TotIntFunction)
        //        int applyAsInt(T value)
        Double average = Stream.of("cake", "biscuits", "apple tart")
                .collect(Collectors.averagingInt(s -> s.length()));

        System.out.println("Collectors.averagingInt():");
        System.out.println(average);     // 7.333333333333333 (average of ints)
        System.out.println();

        Double gradesAverage = Stream.of(5.6, 8.9, 9.3)
                .collect(Collectors.averagingDouble(d -> d));

        System.out.println("Collectors.averagingDouble():");
        System.out.println(gradesAverage);          // 7.933333333333334 (average of doubles)
        System.out.println();
    }

    public static void doCollectToMap1(){
        Map<String, Integer> map = Stream.of("cake", "biscuits", "apple tart")
                .collect(Collectors.toMap(
                        s -> s,                 // Function to create keys
                        s -> s.length()         // Function to create values
                ));

        System.out.println("Collectors.toMap():");
        System.out.println(map);                // {biscuits=8, cake=4, apple tart=10}
    }

    public static void doCollectToMap2(){
        // We have a Map: dessert length as a key, dessert name as value.
        // Two keys with the same length, which leads to an Exception being thrown, IllegalStateException: Duplicate key.
        // Solution:
        // Supply a third, merge function, whereby we append the colliding keys values together.
        Map<Object, Object> map = Stream.of("cake", "biscuits", "tart")           // cake = tart = 4
                .collect(Collectors.toMap(
                        s -> s.length(),                       // Function to create keys (Integer)
                        s -> s,                                // Function to create values (String)
                        (s1, s2) -> s1 + "," + s2              // Merge Function; Solve the duplicate key-values problem by appending the values
                ));

        System.out.println(map);                               // {4=cake,tart, 8=biscuits}
    }

    public static void doCollectToMap3(){
        // Here we want a TreeMap instead of the default HashMap, because we want the keys sorted.
        // We add a Function to get a TreeMap.
        TreeMap<String, Integer> map = Stream.of("cake", "biscuits", "apple tart", "cake")
                .collect(Collectors.toMap(
                        s -> s,                                // Function to create keys (String)
                        s -> s.length(),                       // Function to create values (Integer)
                        (len1, len2) -> len1 + len2,           // Merge Function; Solve the duplicate key-values problem by adding the values
                        TreeMap::new                           // The Map implementation is a TreeMap
                ));

        System.out.println(map);                               // {apple tart=10, biscuits=8, cake=8} Obs! cake maps to 8
        System.out.println(map.getClass());                    // java.util.TreeMap
        System.out.println();
    }

    public static void doGroupingBy1(){
        // groupingBy tells collect() to group all of the elements into a Map.
        // Passing in a Function that determines the key in the Map
        Stream<String> names = Stream.of("Martin", "Peter", "Joe", "Tom", "Tom", "Ann", "Alan");
        Map<Integer, List<String>> map = names
                .collect(Collectors.groupingBy(String::length));        // s -> s.length()

        System.out.println("Collectors.groupingBy():");
        System.out.println(map);                        // {3=[Joe, Tom, Tom, Ann], 4=[Alan], 5=[Peter], 6=[Martin]}
    }

    public static void doGroupingBy2(){
        // We want the values in a Set, as opposed to the default List, so that we will not have duplicated values
        Stream<String> names = Stream.of("Martin", "Peter", "Joe", "Tom", "Tom", "Ann", "Alan");
        Map<Integer, Set<String>> map = names
                .collect(Collectors.groupingBy(
                        String::length,                     // Function to create the keys (Integer)
                        Collectors.toSet()                  // values set to a Set (no duplicates)
                ));

        System.out.println(map);                            // {3=[Ann, Joe, Tom], 4=[Alan], 5=[Peter], 6=[Martin]}
    }

    public static void doGroupingBy3(){
        // There are no guarantees on the type of Map returned.
        // We want the Map as a TreeMap but leave the values as a List.
        Stream<String> names = Stream.of("Martin", "Peter", "Joe", "Tom", "Tom", "Ann", "Alan");
        TreeMap<Integer, List<String>> map = names
                .collect(Collectors.groupingBy(
                        String::length,                     // keys (Integer)
                        TreeMap::new,                       // Map Type Supplier (TreeMap)
                        Collectors.toList()                 // values (List<String)
                ));

        System.out.println(map);                            // {3=[Joe, Tom, Tom, Ann], 4=[Alan], 5=[Peter], 6=[Martin]}
        System.out.println(map.getClass());                 // class java.util.TreeMap
        System.out.println();
    }

    public static void doPartitioning1(){
        // Partitioning is a special case of grouping where there are
        // only two possible groups true and false.
        // The keys will be the booleans true and false.
        // We use a Predicate.
        Stream<String> names = Stream.of("Thomas", "Teresa", "Mike", "Alan", "Peter");
        Map<Boolean, List<String>> map = names
                .collect(Collectors.partitioningBy(s -> s.startsWith("T")));

        System.out.println("Collectors.partitioningBy():");
        System.out.println(map);                    // false=[Mike, Alan, Peter], true=[Thomas, Teresa]}
    }

    public static void doPartitioning2(){
        Stream<String> names = Stream.of("Thomas", "Teresa", "Mike", "Alan", "Peter");
        Map<Boolean, List<String>> map = names
                .collect(Collectors.partitioningBy(s -> s.length() > 4));

        System.out.println(map);                    // {false=[Mike, Alan], true=[Thomas, Teresa, Peter]}
    }

    public static void doPartitioning3(){
        // Here we are forcing one empty list
        Stream<String> names = Stream.of("Thomas", "Teresa", "Mike", "Alan", "Peter");
        Map<Boolean, List<String>> map = names
                .collect(Collectors.partitioningBy(s -> s.length() > 10));

        System.out.println(map);                    // {false=[Thomas, Teresa, Mike, Alan, Peter], true=[]}
    }

    public static void doPartitioning4(){
        // Instead of the default List, we want to collect the values into a Set.
        Stream<String> names = Stream.of("Alan", "Teresa", "Mike", "Alan", "Peter");
        Map<Boolean, Set<String>> map = names
                .collect(Collectors.partitioningBy(s -> s.length() > 4,
                        Collectors.toSet()));

        System.out.println(map);                // {false=[Mike, Alan], true=[Teresa, Peter]}
    }
}
