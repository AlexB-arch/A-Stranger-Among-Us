package text_adventure.objects;

public class Message {
    
    private String message;
    private String header;

    public Message(String header, String message) {
        this.header = header;
        this.message = message;
    }
    public String getHeader() {
        return header;
    }
    public String getMessage() {
        return message;
    }
    
    public void printMessage() {
        System.out.println(header);
        System.out.println(message);
    }
    
     
}
