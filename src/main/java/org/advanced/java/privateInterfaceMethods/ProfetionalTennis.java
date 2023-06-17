package org.advanced.java.privateInterfaceMethods;

public class ProfetionalTennis implements Tennis {
    public static void main(String[] args) {
        Tennis tennis = new ProfetionalTennis();
        tennis.backhand();
        Tennis.forehand();
        tennis.hitHard();

//        tennis.hit();
//        tennis.smash();
    }
}

interface Tennis {
    private static void hit(String stroke) {
        System.out.println("Hitting a " + stroke);
    }

    static void forehand () {
        hit("forehand");
    }

    default void backhand() {
        hit("backhand");
    }

    default void hitHard() {
        smash();
    }

    private void smash() {
        hit("smash");
    }

//    private default something() {}            // private default is not ok

//    void volley() { hit("backhand");}         // Interface abstract method cannot have a body
}
