package org.avdvanced.java.concurrency.threading.problems;

// My thanks to Maaike van Putten (BrightBoost) for inspiring this example.
public class Deadlock {
    public static void go(){
        final String ransom    = "ransom";
        final String hostage   = "hostage";
        
        Thread criminals = new Thread( () -> {
            synchronized(hostage){
                System.out.println("The criminals have the hostage and want the ransom...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                // make sure this next block is inside the first synchronized block.
                synchronized(ransom){
                    System.out.println("The criminals have BOTH!");
                } // auto release of lock on 'ransom'
            } // auto release of lock on 'hostage'
        });

        /**
         * Solution:
         * Both threads should lock on the SAME objects in the SAME order!
         */

        Thread police = new Thread( () -> {
//            synchronized (ransom) {
            synchronized(hostage){
                System.out.println("The police have the hostage and want the ransom...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
//                    synchronized(hostage){
                synchronized(ransom){
                    System.out.println("The police have BOTH!.");
                } // auto release of lock on 'hostage'
            } // auto release of lock on 'ransom'
        });
       
        criminals.start();
        police.start();
    }
    public static void main(String[] args) {
        go();
    }
    
}
