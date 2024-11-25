package text_adventure.objects;
import text_adventure.Game;
import text_adventure.interfaces.Subscriber; 


public class Pong implements Subscriber{


    public String didPong;
    
    public Pong(){
        Game.globalEventBus.registerSubscriber("PINGPONG",this);
        //Game.globalEventBus.registerSubscriber("PING",this);
        didPong = "";    
    }


    @Override
    public void onMessage(Message message) {
        if(message.getChannel().equals("PINGPONG")){
        switch (message.getType()) {
            case "PING1":
                System.out.println("Object: Pong");
                System.out.println("Channel:"+message.getChannel() );
                System.out.println("Type:"+message.getType() );
                System.out.println("Message:"+message.getMessage());

                Game.globalEventBus.publish(new TextMessage("PINGPONG", "PONG1", "pong"));
                break;
            default:
                break;
         }
        }
    }
    //     if(message.getChannel().equals("PING")){
    //         switch (message.getType()) {
    //             case "PING1":
    //                 System.out.println(message.getMessage());
    //                 break;
    //             default:
    //                 break;
    //          }
    //     }
    // }
    
    
}
