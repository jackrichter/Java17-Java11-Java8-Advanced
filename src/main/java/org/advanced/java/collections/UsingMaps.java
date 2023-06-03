package org.advanced.java.collections;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class UsingMaps {
    public static Map<String, Integer> map = new TreeMap<>();     // sorted by keys
    public static void main(String[] args) {
        sorTedMap();
        mapIteration();
        mapInsertion();
        mapManipulate();
    }

    private static void sorTedMap() {
        map.put("John", 18);
        map.put("Mary", 21);
        map.put("Chris", 33);
        System.out.println(map.containsKey("John"));    // true
        System.out.println(map.containsValue(18));      // true
        System.out.println(map.isEmpty());              // false
        System.out.println(map.get("John"));            // 18

        for (String key : map.keySet()) {
            System.out.println(key);                    // Chris, John, Mary
        }
        for (Integer val : map.values()) {              // sort is by key
            System.out.println(val);                    // 33, 18, 21
        }

        System.out.println(map.containsKey("Paul"));    // false
        System.out.println(map.containsValue(21));      // true
        System.out.println(map.size());                 // 3
        map.clear();
        System.out.println(map.size());                 // 0
    }

    private static void mapIteration() {
        map.put("John", 18);
        map.put("Mary", 21);
        map.put("Chris", 33);

        map.forEach((key, val) -> System.out.println(key + " -> " + val));      // Chris -> 33, John -> 18, Mary -> 21

        // Alternative using entrySet, Set<Map.Entry<K, V>>
        map.entrySet().forEach(entry -> System.out.println(entry.getKey() + " maps to " + entry.getValue()));
    }

    private static void mapInsertion() {
        Set<String> keys = map.keySet();
        for (String k : keys) {
            System.out.println(k);                  // Chris, John, Mary
        }

        map.put("Mike", null);                      // Chris, John, Mary, Mike=null
        map.putIfAbsent("Chris", 99);               // Chris=33, John, Mary, Mike=null
        map.putIfAbsent("Mike", 55);                // Chris, John, Mary, Mike=55
        map.putIfAbsent("Luke", 31);                // Chris, John, Luke=31, Mary, Mike
        System.out.println(map);                    // Chris=33, John=18, Luke=31, Mary=21, Mike=55
    }

    private static void mapManipulate() {
        Integer originalVal = map.replace("Chris", 81);
        System.out.println(map);                            // Chris=81, John=18, Luke=31, Mary=21, Mike=55

        map.replaceAll((name, age) -> name.length());
        System.out.println(map);                            // Chris=5, John=4, Luke=4, Mary=4, Mike=4

        map.remove("Mike");
        System.out.println(map);                            // Chris=5, John=4, Luke=4, Mary=4
    }
}
