package org.advanced.java.collections;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Not FIFO and not LIFO (== Stack).
 * Uses ordering, natural or customized (== comparator).
 *
 * For customized order, the class being compared
 * must implement Comparable<theClass>, or
 * a Comparator for that class (Comparator<theClass> comp)
 * must be coded externally in the program.
 * Obs.
 * The variables in the class must themselves also implement Comparable!
 */
public class UsingPriorityQueue {
    public static void main(String[] args) {
        priorityQueueNaturalOrdering();
        priorityQueueCustomOrder();
    }

    private static void priorityQueueNaturalOrdering() {
        Queue<String> chars = new PriorityQueue<>();             // Alphabetic order
        chars.add("V");
        chars.add("C");
        chars.add("A");

        while (chars.iterator().hasNext()) {
            System.out.println(chars.poll() + " ");             // A C V
        }

        Queue<Integer> nums = new PriorityQueue<>();            // Numeric order
        nums.offer(11);
        nums.offer(5);
        nums.add(2);

        while (nums.iterator().hasNext()) {
            System.out.println(nums.remove() + " ");            // 2, 5, 11
        }
    }

    private static void priorityQueueCustomOrder() {
        // Using a Comparator to order by title
        Comparator<Book> compByTitle = Comparator.comparing(book -> book.getTitle());           // Gets in a book and returns the title which is then the sorting key
        Queue<Book> booksByTitle = new PriorityQueue<>(compByTitle);                            // Order by Title
        booksByTitle.add(new Book("Java", 55.0));
        booksByTitle.add(new Book("Python", 23.0));
        booksByTitle.add(new Book("C++", 99.0));

        System.out.println("Ordering by Title:");
        Iterator<Book> itBooks = booksByTitle.iterator();
        while (itBooks.hasNext()) {
            Book book = booksByTitle.poll();                    // <= OBS!
            System.out.println(book);
        }
        System.out.println();

        Comparator<Book> compByPrice = Comparator.comparing(book -> book.getPrice());           // Gets in a book and returns the price which is then the sorting key
        Queue<Book> booksByPrice = new PriorityQueue<>(compByPrice);                            // Order by Price
        booksByPrice.add(new Book("Java", 55.0));
        booksByPrice.add(new Book("Python", 23.0));
        booksByPrice.add(new Book("C++", 99.0));

        System.out.println("Ordering by Price:");
        Iterator<Book> itBooks2 = booksByPrice.iterator();
        while (itBooks2.hasNext()) {
            Book book = booksByPrice.poll();
            System.out.println(book);
        }
    }
}
