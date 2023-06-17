package org.advanced.java.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SearchDirectory {
    public static void main(String[] args) {
        Path start = Paths.get("src/main/java/org/advanced/java/nio2");
        Path startDeep = Paths.get("src/main/java/org/advanced/java");

        try (var streamPaths = Files.find(start, 10, ((path, basicFileAttributes) ->
                path.toString().endsWith(".java") && basicFileAttributes.isRegularFile()))) {
            streamPaths.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
