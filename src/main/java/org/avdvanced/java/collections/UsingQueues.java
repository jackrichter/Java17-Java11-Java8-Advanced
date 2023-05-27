package org.avdvanced.java.collections;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class UsingQueues {
    public static void main(String[] args) {
        linkedListQueue();
        arrayDeQueue();
    }

    private static void linkedListQueue() {
        Queue<Integer> queue = new LinkedList<>();     // FIFO
        queue.add(1);                                  // Head-> [1],  can throw exception
        queue.offer(2);                             // Head-> [1, 2],  true/false
        queue.add(3);                                  // Head-> [1, 2, 3]
        queue.offer(4);                             // Head-> [1, 2, 3, 4]

        System.out.println(queue.element());           // 1, does not remove, can throw exception
        System.out.println(queue.peek());              // 1, does not remove, null
        System.out.println(queue);                     // [1, 2, 3, 4]

        System.out.println(queue.remove());            // 1, Head-> [2, 3, 4], remove and return, throws exception if queue is empty
        System.out.println(queue.poll());              // 2, Head-> [3, 4], remove and return, null if queue is empty
        System.out.println(queue);                     // [3, 4]

        // Use P.O.P!!
    }

    private static void arrayDeQueue() {
        Deque<Integer> numbers = new ArrayDeque<>();    // Double-ended queue resizeable array-queue. Methods a.r.g. throw exception
        numbers.add(1);                                 // Head-> [1] <-Tail, save at this point, no exceptions
        numbers.addFirst(2);                         // Head-> [2, 1] <-Tail, throw exception
        numbers.offerFirst(3);                       // Head-> [3, 2, 1] <-Tail, null/false
        System.out.println("Head (can throw exception):" + numbers.getFirst() + "   Head (no exception): " + numbers.peekFirst());

        numbers.addLast(4);                          // Head-> [3, 2, 1, 4] <-Tail
        numbers.offerLast(5);                        // Head-> [3, 2, 1, 4, 5] <-Tail

        numbers.removeFirst();                         // Head-> [2, 1, 4, 5] <-Tail, can throw exception
        numbers.pollFirst();                           // Head-> [1, 4, 5] <-Tail, null/false, no exception
        numbers.removeLast();                          // Head-> [1, 4] <-Tail, can throw exception
        numbers.pollLast();                            // Head-> [1] <-Tail, null/false, no exception
        System.out.println(numbers);                   // [1]

        // P.O.P is safer
    }
}
