package text_adventure;

import text_adventure.objects.Message;

public interface Subscriber {
    void onMessage(Message message);
}