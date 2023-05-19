package org.avdvanced.java.lab1.lambda;

@FunctionalInterface
public interface Printable<T> {
    public void print(T t);
}
