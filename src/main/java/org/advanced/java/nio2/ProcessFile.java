package org.advanced.java.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ProcessFile {
    public static void main(String[] args) {
        // Using Files.lines() to stream a file providing one line at a time.
        List<Cat> cats = loadCats("Cats.txt");
        cats.forEach(cat -> System.out.print(cat + " "));               // Cat{Fido, Black} Cat{Lily, White}
        System.out.println();
    }

    public static List<Cat> loadCats(String fileName) {
        List<Cat> cats = new ArrayList<>();

        try (Stream<String> fileStream = Files.lines(Paths.get("src/main/resources/" + fileName))) {
            fileStream
                    .forEach(line -> {
                        String [] details = line.split("/");
                        String name = details[0];
                        String color = details[1];
                        cats.add(new Cat(name, color));
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cats;
    }
}
