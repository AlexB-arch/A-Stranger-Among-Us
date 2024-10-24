package text_adventure.objects;

public interface Subscriber {
    void onMessage(Message message);
}