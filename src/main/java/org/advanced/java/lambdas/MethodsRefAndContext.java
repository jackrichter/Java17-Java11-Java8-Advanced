package org.advanced.java.lambdas;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodsRefAndContext {
    public static void main(String[] args) {
        // No Person being passed in => Supplier
        Supplier<Integer> supLambda = () -> Person.howMany();
        Supplier<Integer> supMR = Person::howMany;

        System.out.println(supLambda.get());    // 0
        System.out.println(supMR.get());        // 0

        // One Person to be passed in => Function
        Function<Person, Integer> funcLambda = person -> Person.howMany(person);
        Function<Person, Integer> funcMR = Person::howMany;         // Context
        int totLambda = funcLambda.apply(new Person());
        int totMR = funcMR.apply(new Person());
        System.out.println(totLambda);      // 1
        System.out.println(totMR);          // 1

        // Two Person's to be passed in => BiFunction
        BiFunction<Person, Person, Integer> biFuncLambda = (p1, p2) -> Person.howMany(p1, p2);
        BiFunction<Person, Person, Integer> biFuncMR = Person::howMany;             // Context
        int totLambda2 = biFuncLambda.apply(new Person(), new Person());
        int totMR2 = biFuncMR.apply(new Person(), new Person());
        System.out.println(totLambda2);     // 2
        System.out.println(totMR2);         // 2
    }

    static class Person {
        public static Integer howMany(Person... people) {           // Passing an Array of Person. 0 -> n
            return people.length;
        }
    }
}
