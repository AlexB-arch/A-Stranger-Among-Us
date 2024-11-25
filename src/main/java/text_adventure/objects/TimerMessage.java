package text_adventure.objects;

import java.util.Map;

import org.json.JSONObject;

// Implementation of the Message interface for timer operations
public class TimerMessage implements Message {
    private final String channel;
    private final String type;
    private final String message;

    public TimerMessage(String channel, String type, String message) {
        this.channel = channel;
        this.type = type;
        this.message = message;
    }

    @Override
    public String getChannel() {
        return channel;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public static TimerMessage createMessage(String type, Map<String, Object> payload) {
        JSONObject json = new JSONObject(payload);
        return new TimerMessage(
            "TIMER",
            type,
            json.toString()
        );
    }
}  
