package org.avdvanced.java.concurrency.data.race;

/*
// 1. 
        synchronized on static method
// 2. 
        public static void block
            synchronized(FixRaceWithSynchronized.class){   
// 3. 
        synchronized(lock){   
// 4. 
        synchronize on 'this'
//          - make addToCounter() an instance method
//          - synchronized(this) inside the method
//          - create an instance of the overall class FixRaceWithSynchronized in main()
//          - ensure that all the threads share the same instance!
*/
public class FixRaceWithSynchronized {
    private static Object lock = new Object();
    private static int count =0 ;
    
    public synchronized static void addToCounter(){
//    public static void addToCounter(){
//        synchronized(FixRaceWithSynchronized.class){
//        synchronized(lock){
            int c = count;
            System.out.println("Before (addToCounter). "+count + ". Thread id: "+Thread.currentThread().getId());
            count = c + 1; // not atomic
            System.out.println("After (addToCounter). "+count + ". Thread id: "+Thread.currentThread().getId());
//        }
    }
    public synchronized void addToCounter_instance(){
//        synchronized(this){
            int c = count;
            System.out.println("Before (addToCounter_instance). "+count + ". Thread id: "+Thread.currentThread().getId());
            count = c + 1; // not atomic
            System.out.println("After (addToCounter_instance). "+count + ". Thread id: "+Thread.currentThread().getId());
//        }
    } 

    public static void main(String[] args) {
        // When synch. on 'this' make sure all threads use the same instance.
        FixRaceWithSynchronized instance = new FixRaceWithSynchronized();
        for(int i=1; i<=10; i++){
            new Thread(() -> instance.addToCounter_instance())
//            new Thread(() -> addToCounter())
                    .start();
        }
    }
}
