package org.avdvanced.java.concurrency.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SubmittingTaskCollections {

    // Single Thread Executors will accept tasks sequentially in the order they are submitted
//    private static ExecutorService es = Executors.newSingleThreadExecutor();
    private static ExecutorService es = Executors.newFixedThreadPool(4);
    private static List<Callable<String>> callables = new ArrayList<>();

    public static void main(String[] args) {
        // Add callable tasks
        callables.add(() -> "A");
        callables.add(() -> "B");
        callables.add(() -> "C");
        callables.add(() -> "D");

        invokeAny();
//        invokeAll();
    }

    private static void invokeAny() {
        // submitting a collection of tasks
        // executes synchronously and returns when one of the tasks has completed, all
        // other tasks are cancelled.
        try {
            String result = es.invokeAny(callables);
            System.out.println(result);
        } catch (InterruptedException | RuntimeException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            es.shutdown();
        }
        System.out.println("Always at the end!");
    }

    private static void invokeAll() {
        // submitting a collection of tasks
        // executes synchronously and returns when all the tasks are completed.
        try {
            List<Future<String>> list = es.invokeAll(callables);
            list.forEach(future -> {
                try {
                    String s = future.get();        // A, B, C, D in order
                    System.out.println(s);
                } catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            es.shutdown();
        }
        System.out.println("Always at the end!");
    }
}
