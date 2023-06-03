package org.advanced.java.lab1.lambda;

@FunctionalInterface
public interface Functionable<T,R> {
    public R applyThis(T t);
}
