package text_adventure.objects;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import text_adventure.Subscriber;

/**
 * MessageBus - Austin
 * This is the MessageBus system. It is used to send messages from object to object, allowing the whole game to communicate with itself with ease.
 * 
 * Hail the MessageBus! The MessageBus is mighty and powerful!
 * 
 */

public class MessageBus {
    private BlockingQueue<Message> messageQueue;
    private Map<String, List<Subscriber>> subscribersMap;
    private ExecutorService threadPool;
    public MessageBus(int queueCapacity, int threadPoolSize) {
        messageQueue = new ArrayBlockingQueue<>(queueCapacity); // Bounded queue
        subscribersMap = new ConcurrentHashMap<>(); // Thread-safe map for subscribers
        threadPool = Executors.newFixedThreadPool(threadPoolSize); // Thread pool for consumers
    }

    // Register a subscriber for a specific message type
    public void registerSubscriber(String subscriberKey, Subscriber subscriber) {
        subscribersMap.computeIfAbsent(subscriberKey, k -> new CopyOnWriteArrayList<>()).add(subscriber);

    }

    // Method for producers to publish messages
    public void publish(Message message) {
        try {
            messageQueue.put(message); // Blocks if the queue is full
        } catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
    }

    // Start message processing (consumers)
    public void startMessageProcessing() {
        // start with one Thread update to add more threads.
        for (int i = 0; i < 3; i++) { // Start multiple consumers, adjust the count as needed
            threadPool.execute(() -> {
                try {
                    while (true) {
                        Message message = messageQueue.take(); // Blocks if queue is empty
                        String messageHeader = message.getHeader();
                        List<Subscriber> subscribers = subscribersMap.getOrDefault(messageHeader, new CopyOnWriteArrayList<>());

                        for (Subscriber subscriber : subscribers) {
                            subscriber.onMessage(message); // Notify all subscribers
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }

    // Shutdown the thread pool when no longer needed
    public void shutdown() {
        threadPool.shutdown();

        try {
            if (!threadPool.awaitTermination(1, TimeUnit.SECONDS)) {
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // If the executor is still running after the specified time, force shutdown
        if (!threadPool.isTerminated()) {
            threadPool.shutdownNow();
        }
    }    

    // Get the ampunt of subscribers
    public int getSubscriberCount() {
        return subscribersMap.size();
    }
}

