package text_adventure.objects;


import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {
    private LinkedBlockingQueue<Message> messageQueue;

    public MessageQueue() {
        this.messageQueue = new LinkedBlockingQueue<>();
    }

    public void enqueue(Message message) {
        messageQueue.add(message);
    }
    public Message dequeue() {
        return messageQueue.poll();
    }
}
