package text_adventure.objects;

import text_adventure.objects.Room;

public class Doors {
    private boolean isOpen;
    private int doorId;
    private Room exit;
    private String keytype;


    //Room doorout, bool open, string color, int doorId
    public Doors(Room doorout, boolean open, String color, int doorId) {
        this.doorId = doorId;
        this.isOpen = open;
        this.exit = doorout;
        this.keytype = color;
        //MessageBus.getInstance().subscribe(this, doorId);
    }

    public Room travel()
    {
      if
      return exit;
    }

    public String getKey()
    {
      return keytype;
    }

    public void open() {
        isOpen = true;
        System.out.println("Door " + doorId + " is now open.");
    }

    public void close() {
        isOpen = false;
        System.out.println("Door " + doorId + " is now closed.");
    }

    // @Override
    // public void receive(Message message) {
    //     if (message instanceof TextMessage) {
    //         TextMessage textMessage = (TextMessage) message;
    //         if (textMessage.getText().equals("open")) {
    //             open();
    //         } else if (textMessage.getText().equals("close")) {
    //             close();
    //         }
    //     }
    // }
}
