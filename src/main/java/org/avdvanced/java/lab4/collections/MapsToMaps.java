package org.avdvanced.java.lab4.collections;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class MapsToMaps {
    public static void main(String[] args) {
        mapsToMaps();
    }

    public static void mapsToMaps() {
        Map<String, Integer> channelToSubscribers = new TreeMap<>();        // channel, numSubscribers
        Map<String, String> channelToPublisher = new TreeMap<>();           // channel, publisher
        Map<String, Integer> publisherToSubscribers = new TreeMap<>();      // publisher, numSubscribers

        // channel -> number of subscribers
        // K -> V1
        channelToSubscribers.put("JustForLaughs", 120_000);
        channelToSubscribers.put("JustForGags", 10_000);
        channelToSubscribers.put("ContemplationTechniques", 10_000);
        channelToSubscribers.put("A New Earth", 20_000);
        channelToSubscribers.put("ContemplationTechniques", 10_000);
        channelToSubscribers.put("A New Earth", 20_000);

        // channel -> publisher
        // K -> V2
        channelToPublisher.put("JustForLaughs", "Charlie Chaplin");
        channelToPublisher.put("JustForGags", "Charlie Chaplin");
        channelToPublisher.put("ContemplationTechniques", "Echhart Tolle");
        channelToPublisher.put("A New Earth", "Echhart Tolle");

        // 1. Setup "publisherToSubscribers"
        // publisher -> number of subscribers (total)
        // V2 -> V1
        channelToSubscribers.forEach((channel, subscribers) -> {
            // Get the publisher for that channel
            String publisher = channelToPublisher.get(channel);

            // Check if already present in map. If so, calculate the total
            if (publisherToSubscribers.containsKey(publisher)) {
                int currentNumOfSubscribers = publisherToSubscribers.get(publisher);
                publisherToSubscribers.put(publisher, currentNumOfSubscribers + subscribers);
            } else {
                publisherToSubscribers.put(publisher, subscribers);
            }
        });


        // 2. Output "publisherToSubscribers"
        publisherToSubscribers.forEach((publisher, subscribers) ->
                System.out.println("Publisher: " + publisher + ", number of subscribers: " + subscribers));


        // 3. Who has the most/least subscribers?
        int maxNumOfSubscribers = Collections.max(publisherToSubscribers.values());
        int minNumOfSubscribers = Collections.min(publisherToSubscribers.values());
        publisherToSubscribers.forEach((publisher, subscribers) -> {
            if (subscribers == maxNumOfSubscribers) {
                System.out.println("Publisher with most subscribers: " + publisher + ", " + subscribers);
            } else if (subscribers == minNumOfSubscribers) {
                System.out.println("Publisher with least subscribers: " + publisher + ", " + subscribers);
            }
        });
    }
}
