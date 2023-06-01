package org.avdvanced.java.concurrency.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorWithRunableTest {
    public static void main(String[] args) {
        ExecutorService es = Executors.newSingleThreadExecutor();

        // execute the Runnable task (asynchronously) - void run()
        es.execute(() -> System.out.println("Runnable example"));

        // shutdown the executor service otherwise this application will never terminate;
        // existing tasks will be allowed to complete but no new tasks accepted
        es.shutdown();
    }
}
