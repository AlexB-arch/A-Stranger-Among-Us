package text_adventure.objects;

public class TextMessage implements Message {
    private final String type;
    private final String channel;
    private final String content;

    public TextMessage(String channel, String type, String content) {
        this.channel = channel;
        this.type = type;
        this.content = content;
    }

    @Override
    public String getType() {
        return type;
    }
    @Override
    public String getMessage() {
        return content;
    }

    @Override
    public String getChannel() {
        return channel;
    }
}
