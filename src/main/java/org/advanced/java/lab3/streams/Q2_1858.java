package org.advanced.java.lab3.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q2_1858 {
    public static void main(String[] args) {
        List<AnotherBook> books = Arrays.asList(
                new AnotherBook("Gone with the wind", "Fiction"),
                new AnotherBook("Bourne Ultimatum", "Thriller"),
                new AnotherBook("The Client", "Thriller") );
        List<String> genreList = new ArrayList<>();
        books.stream()
                .map(x -> x.getGenre())
                .forEach(x -> genreList.add(x));
        genreList.forEach(System.out::println);
    }
}
