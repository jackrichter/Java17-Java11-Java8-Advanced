package org.avdvanced.java.lab3.streams;

import java.util.Comparator;
import java.util.List;

public class Q2_1762 {
    public static void main(String[] args) {
        List<Item> items = List.of(new Item(1, "Screw"), new Item(2, "Nail"), new Item(3, "Bolt"));
        items.stream()
                .sorted(Comparator.comparing(Item::getName))
                .map(Item::getName)
                .forEach(System.out::print);
    }
}
