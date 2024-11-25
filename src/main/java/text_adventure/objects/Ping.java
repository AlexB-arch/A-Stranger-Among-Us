package text_adventure.objects;

import text_adventure.Game;
import text_adventure.interfaces.Subscriber; 

public class Ping implements Subscriber {
    public String didPing;
    
    public Ping(){
        Game.globalEventBus.registerSubscriber("PINGPONG",this);
        didPing = "";    
    }

    public void sendPing(){
        Game.globalEventBus.publish(new TextMessage("PINGPONG","PING1","ping pong!"));
       // Game.globalEventBus.publish(new TextMessage("PING","PING1","ping just ping"));

    }

    @Override
    public void onMessage(Message message) {
        // TODO Auto-generated method stub
        if(message.getChannel().equals("PINGPONG")){
            switch (message.getType()) {
                case "PONG1":
                System.out.println("Object: Ping");
                System.out.println("Channel:"+message.getChannel() );
                System.out.println("Type:"+message.getType() );
                System.out.println("Message:"+message.getMessage());
                    // Game.globalEventBus.publish(new TextMessage("PING", "PONG1", "pong"));
                System.out.println("got:" + message.getMessage());
                    break;
                default:
                    break;
             }    
            
            }
        }
    }

    
    

