package org.advanced.java.lab1.lambda;

@FunctionalInterface
public interface Printable<T> {
    public void print(T t);
}
