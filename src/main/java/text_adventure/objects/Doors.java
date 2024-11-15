package text_adventure.objects;


public class Door implements Subscriber {
    private boolean isOpen;
    private String doorId;

    public Door(String doorId) {
        this.doorId = doorId;
        this.isOpen = false;
        MessageBus.getInstance().subscribe(this, doorId);
    }

    public void open() {
        isOpen = true;
        System.out.println("Door " + doorId + " is now open.");
    }

    public void close() {
        isOpen = false;
        System.out.println("Door " + doorId + " is now closed.");
    }

    @Override
    public void receive(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            if (textMessage.getText().equals("open")) {
                open();
            } else if (textMessage.getText().equals("close")) {
                close();
            }
        }
    }
}
