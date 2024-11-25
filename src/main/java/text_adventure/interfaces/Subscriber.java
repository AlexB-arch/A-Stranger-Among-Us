package text_adventure.interfaces;

import text_adventure.objects.Message;

public interface Subscriber {
    void onMessage(Message message);
}