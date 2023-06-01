package org.avdvanced.java.concurrency.executor;

import java.util.concurrent.*;

/**
 * Illustrates how Callable, Executors, ExecutorService, and Future are related,
 * also shows how they work together to execute a task.
 */
public class ExecutorWithCallableTest {
    public static void main(String[] args) {
        ExecutorService es = Executors.newSingleThreadExecutor();

        // submit the Callable task (asynchronously) to the executor service
        // and store the Future object
        Future<Integer> future = es.submit(() -> 3 + 5);

        // get() will block for 500 msecs max.
        // TimeUnit is an enum
        try {
            System.out.println("The answer is: " + future.get(500, TimeUnit.MICROSECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            ex.printStackTrace();
        }

        // shutdown the executor service otherwise this application will never terminate;
        // existing tasks will be allowed to complete but no new tasks accepted
        es.shutdownNow();
    }
}
