package text_adventure.objects;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import text_adventure.Game;
import text_adventure.Subscriber;

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
        subscribersMap.computeIfAbsent(subscriberKey, k -> new ArrayList<>()).add(subscriber);

    }

    // Method for producers to publish messages
    public void publish(Message message) {
        try {
            messageQueue.put(message); // Blocks if the queue is full
        } catch (InterruptedException e) {
			e.printStackTrace();
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
                        List<Subscriber> subscribers = subscribersMap.getOrDefault(messageHeader, new ArrayList<>());

                        for (Subscriber subscriber : subscribers) {
                            
                            subscriber.onMessage(message); // Notify all subscribers
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Message processing interrupted.");
                }
            });
        }
    }

    // Shutdown the thread pool when no longer needed
    public void shutdown() {
        threadPool.shutdownNow();
    }
}
