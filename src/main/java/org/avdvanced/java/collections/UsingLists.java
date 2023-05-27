package org.avdvanced.java.collections;

import java.util.*;

public class UsingLists {
    public static void main(String[] args) {
        factoryMethods();
        arrayList();
        stack();
        linkedList();
    }

    private static void factoryMethods() {
        String[] array = new String[] {"Alpha", "Beta", "Charlie"};
        List<String> asList = Arrays.asList(array);                     // The array and the list now back each other
        List<String> of = List.of(array);
        List<String> copy = List.copyOf(asList);

        array[0] = "Delta";                                             // The change is reflected in both the array and the list (asList)
        System.out.println(Arrays.toString(array));                     // ["Delta", "Beta", "Charlie"]
        System.out.println(asList);                                     // ["Delta", "Beta", "Charlie"]

        asList.set(1, "Echo");                                          // The change is reflected in both the array and the list (asList)
        System.out.println(Arrays.toString(array));                     // ["Delta", "Echo", "Charlie"]
        System.out.println(asList);                                     // ["Delta", "Echo", "Charlie"]

//        of.add("Foxtrot");                                            // Throws exception. Immutable, unmodifiable
//        copy.add("Foxtrot");                                          // Throws exception. Immutable, unmodifiable
//        asList.add("Foxtrot");                                        // Throws exception. Immutable, unmodifiable
    }
    private static void arrayList() {
        // Lists maintain order (index) and allow duplicates
        List<String> list = new ArrayList<>();
        list.add("Allan");
        list.add("Allan");
        list.add(1,"Sean");
        list.add("Mary");
        list.add("Mary");
        System.out.println(list);                                       // [Allan, Sean, Allan, Mary, Mary]
        System.out.println(list.get(1));                                // Sean

        list.remove(0);                                           // [Sean, Allan, Mary, Mary]
        list.remove("Mary");                                         // Removes the first "Mary" only
        System.out.println(list);                                       // [Sean, Allan, Mary]
        list.set(0, "Jack");                                            // [Jack, Allan, Mary]

        list.replaceAll(name -> name + " Kennedy");                     // UnaryOperator: The input and output are of the same type; Function<T, T>
        System.out.println(list);                                       // [Jack Kennedy, Allan Kennedy, Mary Kennedy]
    }
    private static void stack() {
        // LIFO. Works on one end only. Legacy class, use the Dequeue interface
        Stack<String> stack = new Stack<>();
        stack.push("Andrea");
        stack.push("Barbara");
        stack.push("Caroline");
        System.out.println(stack);                                      // [Andrea, Barbara, Caroline]  <- Head

        System.out.println(stack.peek());                               // Caroline
        System.out.println(stack.pop());                                // Caroline
        System.out.println(stack);                                      // [Andrea, Barbara] <- Head

        stack.push("Helen");
        System.out.println(stack);                                      // [Andrea, Barbara, Helen] <- Head
    }
    private static void linkedList() {
        // Can be manipulated at both ends
        LinkedList<String> names = new LinkedList<>();                  // OBS! Need to declare as LinkedList<T> in order to have 'addFirst', 'addLast' and so on
        names.add("Collin");                                            // Head-> [Collin] <-Head
        names.add("David");                                             // [Collin, David]
        names.addFirst("Brian");                                     // [Brian, Collin, David]
        names.addLast("Edward");                                     // [Brian, Collin, David, Edward]
        System.out.println(names);

        names.remove("David");                                       // [Brian, Collin, Edward]
        names.removeFirst();                                            // [Collin, Edward]
        names.removeLast();                                             // [Collin]
        System.out.println(names);
    }
}
