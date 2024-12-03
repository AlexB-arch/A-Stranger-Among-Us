package text_adventure.objects;

import java.util.Map;

import org.json.JSONObject;

/**
 * Inventory - Austin
 * Class to implenet timer based messages.
 * 
 */

// Implementation of the Message interface for timer operations
public class TimerMessage implements Message {
    private final String header;
    private final String type;
    private final String message;

    public TimerMessage(String header, String type, String message) {
        this.header = header;
        this.type = type;
        this.message = message;
    }

    @Override
    public String getHeader() {
        return header;
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
