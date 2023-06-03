package org.advanced.java.concurrency.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExcecutorServiceTypes {
    public static void main(String[] args) {
        // CachedThreadPool
        ExecutorService es = Executors.newCachedThreadPool();

        // FixedThreadPool
        int numOfCpu = Runtime.getRuntime().availableProcessors();
        ExecutorService es2 = Executors.newFixedThreadPool(numOfCpu);

        // SingleThreadExecutor
        ExecutorService es3 = Executors.newSingleThreadExecutor();
    }
}
