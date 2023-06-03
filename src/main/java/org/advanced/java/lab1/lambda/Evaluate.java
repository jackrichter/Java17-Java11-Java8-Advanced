package org.advanced.java.lab1.lambda;

@FunctionalInterface
public interface Evaluate<T> {
    public boolean isNegative(T t);
}
