package text_adventure.objects;

public class TextMessage implements Message {
    private final String type;
    private final String header;
    private final String content;

    public TextMessage(String header, String type, String content) {
        this.header = header;
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
    public String getHeader() {
        return header;
    }
}
