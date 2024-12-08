package text_adventure.objects.triggers;

import text_adventure.objects.Message;
import text_adventure.objects.TextMessage;
import text_adventure.Subscriber;
import text_adventure.Game;

/**
 * EndGame - Brendan
 * This class is a trigger that is activated when the player has collected all the necessary items to escape the ship.
 * 
 * The player must have collected 5 batteries, 3 oxygen tanks, and the generator must be running.
 */

public class EndGame implements Subscriber{

    public boolean genState;
    public EndGame() {
        Game.globalEventBus.registerSubscriber("TRIGGER", this);
        Game.globalEventBus.registerSubscriber("Escape Pod Room", this);
        Game.globalEventBus.registerSubscriber("CONSOLE", this);
        this.genState = false;
    }

		public String[] MessageSplit(String Message){
			return Message.split(",");
		}

    @Override
	public void onMessage(Message message) {
        if(message.getType().equals("END")) {
            String[] text = MessageSplit(message.getMessage());
            activate(Integer.parseInt(text[0]), Integer.parseInt(text[1]));
        }
        else if(message.getType().equals("GEN")) {
            switch (message.getMessage()) {
                case "ON":
                    genState = true;
                    break;
                case "OFF":
                    genState = false;
                    break;
                default:
                    break;
            } 
        }
	}

    public void activate(int batteries, int oxygen) {
        System.out.println("EndGame: Batteries: " + batteries + " Oxygen: " + oxygen);
        System.out.println("EndGame: Generator: " + genState);
        if (genState && batteries == 5 && oxygen == 3) {
            Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You laod the batteries and oxygen into the escape pod and eject the pod. You've managed to escape the ship and survive!"));
            Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", Game.getInstance().endGame()));
        }
        else {
            Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You need to find " +(5-batteries)+" more batteries and "+(3-oxygen)+" more oxygen tanks first! Make sure the generator is running too!"));
        }
    }

}
