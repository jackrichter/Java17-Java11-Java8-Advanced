package org.advanced.java.lab1.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BasicLambdas {

    Predicate<Integer> isEven = i -> i % 2 == 0;
    Predicate<String> containsStr = s -> s.contains("Mr.");
    Predicate<Person> checkAge = p -> p.getAge() >= 18;

    public static void main(String[] args) {
        BasicLambdas basicLambdas = new BasicLambdas();

        basicLambdas.consumer();
        basicLambdas.supplier();
        basicLambdas.predicate();

        System.out.println("Is the number 4 even? " + basicLambdas.check(4, basicLambdas.isEven));
        System.out.println("Is the number 7 even? " + basicLambdas.check(7, basicLambdas.isEven));
        System.out.println("Does Mr. Joe Bloggs contain Mr.? " + basicLambdas.check("Mr. Joe Bloggs", basicLambdas.containsStr));
        System.out.println("Does Ms. Ann Bloggs contain Mr.? " + basicLambdas.check("Ms. Ann Bloggs", basicLambdas.containsStr));
        Person mike = new Person("Mike", 33, 1.8);
        Person ann = new Person("Ann", 13, 1.4);
        System.out.println("Is Mike over 18? " + basicLambdas.check(mike, basicLambdas.checkAge));
        System.out.println("Is Ann over 18? " + basicLambdas.check(ann, basicLambdas.checkAge));
        System.out.println();

        basicLambdas.function();

        List<Person> listPeople = getPeople();
        sortAge(listPeople);
        sortName(listPeople);
        sortHeight(listPeople);
    }

    public void consumer() {
        Printable<String> lambda = s -> System.out.println(s);
        lambda.print("Printable lambda");

        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("Consumer lambda");

        Consumer<String> consumerMR = System.out::println;
        consumerMR.accept("Consumer method reference");

        System.out.println();
    }

    public void supplier() {
        Retrievable<Integer> lambda = () -> 77;
        System.out.println("Retrievable: " + lambda.retrieve());

        Supplier<Integer> supplier = () -> 77;
        System.out.println("Supplier: " + lambda.retrieve());

        System.out.println();
    }

    public void predicate() {
        Evaluate<Integer> lambda = i -> i < 0;
        System.out.println("Evaluate, -1 is negative? " + lambda.isNegative(-1));
        System.out.println("Evaluate, 1 is negative? " + lambda.isNegative(1));

        Predicate<Integer> predicate = i -> i < 0;
        System.out.println("Predicate, -1 is negative? " + predicate.test(-1));
        System.out.println("Predicate, 1 is negative? " + predicate.test(1));

        System.out.println();
    }

    public <T> boolean check(T t, Predicate<T> predicate) {

        return predicate.test(t);
    }

    private void function() {

        Functionable<Integer, String> lambda = (i) -> "Number is: " + i;
        System.out.println("Functionable lambda: " + lambda.applyThis(25));

        Function<Integer, String> function = i -> "Number is: " + i;
        System.out.println("Function lambda: " + function.apply(25));

        System.out.println();
    }

    private static List<Person> getPeople() {
        List<Person> result = new ArrayList<>();

        result.add(new Person("Mike", 33, 1.8));
        result.add(new Person("Mary", 25, 1.4));
        result.add(new Person("Alan", 34, 1.7));
        result.add(new Person("Zoe", 30, 1.5));

        return result;
    }

    private static void sortAge(List<Person> people) {
        people.sort(Comparator.comparing(Person::getAge));

        people.forEach(System.out::println);
        System.out.println();
    }

    private static void sortName(List<Person> people) {
        people.sort(Comparator.comparing(Person::getName));

        people.forEach(System.out::println);
        System.out.println();
    }

    private static void sortHeight(List<Person> people) {
        people.sort(Comparator.comparing(Person::getHeight));

        people.forEach(p -> System.out.println(p));
        System.out.println();
    }
}
