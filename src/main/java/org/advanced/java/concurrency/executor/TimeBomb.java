package org.advanced.java.concurrency.executor;

class CountDown implements Runnable {
    String[] timeStr = {"Zero", "One", "Two", "Three", "Four", "Five",
            "Six", "Seven", "Eight", "Nine"};

    @Override
    public void run() {
        for (int i = 9; i >=0 ; i--) {
            try {
                System.out.println(timeStr[i]);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class TimeBomb {
    public static void main(String[] args) {
        Thread timer = new Thread(new CountDown());
        System.out.println("Starting 10 seconds count down...");
        timer.start();

        try {
            timer.join();                           // OBS! The Thread 'main' must wait until the Thread 'timer' is finished!
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Boom!!!");
    }
}
