package org.avdvanced.java.collections.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingCatsMultipleFields {
    public static void main(String[] args) {
        List<Cat_No_Comparator> cats = new ArrayList<>();
        cats.add(new Cat_No_Comparator("Trixy", 5));
        cats.add(new Cat_No_Comparator("Bella", 7));
        cats.add(new Cat_No_Comparator("Bella", 2)); // second Bella
        Comparator<Cat_No_Comparator> compCat = Comparator
                                        .comparing(Cat_No_Comparator::getName)
                                        .thenComparingInt(Cat_No_Comparator::getAge);
        Collections.sort(cats, compCat);
        System.out.println(cats);// [Cat{name=Bella, age=2}, Cat{name=Bella, age=7}, Cat{name=Trixy, age=5}]
    }
    
}
