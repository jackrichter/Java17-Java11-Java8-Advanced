package org.avdvanced.java.lab1.lambda;

@FunctionalInterface
public interface Functionable<T,R> {
    public R applyThis(T t);
}
