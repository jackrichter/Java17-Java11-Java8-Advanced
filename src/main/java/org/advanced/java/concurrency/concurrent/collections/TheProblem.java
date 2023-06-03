package org.advanced.java.concurrency.concurrent.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TheProblem {
    public static void main(String[] args) {
        Map<String, String> capitalCities = new HashMap<>();
//        Map<String, String> capitalCities = new ConcurrentHashMap<>();      // Fixes the problem

        capitalCities.put("Oslo", "Norway");
        capitalCities.put("Copenhagen", "Denmark");

//        for (String key : capitalCities.keySet()) {
//            System.out.println(key + " is the capital of " + capitalCities.get(key));
//            capitalCities.remove(key);                                      // Throws ConcurrentModificationException
//        }

        /**
         * Another option is to use Iterator that has no concurrency modification issues
         */
        for (var iter = capitalCities.keySet().iterator(); iter.hasNext();) {
            var key = iter.next();
            System.out.println(key + " is the capital of " + capitalCities.get(key));
            iter.remove();
        }
        System.out.println("After modification: " + capitalCities);
    }
}
