package text_adventure;

import text_adventure.objects.Message;

/**
 * Subscriber - Austin
 * This is an interface for objects that can subscribe to the EventBus.
 * 
 */

public interface Subscriber {
    void onMessage(Message message);
}