package text_adventure.objects;

/**
 * Message - Austin
 * Interface for the Message Bus systems messages.
 * 
 */

public interface Message {
    String getHeader();
    String getType();
    String getMessage();
}

