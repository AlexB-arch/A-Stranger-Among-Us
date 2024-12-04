//Alice's First Dialog Trigger -Brendan

package text_adventure.objects.triggers;

import text_adventure.objects.Message;
import text_adventure.objects.TextMessage;
import text_adventure.Subscriber;
import text_adventure.Game;

/**
 * AliceDialog - Brendan
 * Class for Alice's dialog
 * 
 * Handles updating the dialog based on the room the player is in, the state of the generator, and how many times the player interacted with ALice.
 */

public class AliceDialog implements Subscriber {
    private String currentRoom;
		public int state;

    public AliceDialog(String RoomName) {
				this.currentRoom = RoomName;
				this.state = 1;
				Game.globalEventBus.registerSubscriber("TRIGGER", this);
				Game.globalEventBus.registerSubscriber("CONSOLE", this);
    }

	public String[] MessageSplit(String Message){
		return Message.split(",");
	}

	@Override
	public void onMessage(Message message) {
		if(message.getType().equals("DIAL")) {
			if(message.getMessage() == this.currentRoom && state == 1){
				Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT","\u001B[33mWOH! Captain don't scare me like that. I can barely see a thing in this darkenss. I bed Douglass is trying to get the generator back online. Head south and see if you can find him and get it running.\u001B[37m"));
				state++;
				}
			else if(message.getMessage() == this.currentRoom && state == 2) {
				Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT","\u001B[33mWhat are you doing? Go and turn the Generator on and find Douglass!\u001B[37m"));
				}
			else if(message.getMessage() == this.currentRoom && state == 3) {
				Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT","\u001B[33mD-Douglass is dead!!! We need to get out of here! Let's go to the Bridge and see if we can find everyone else.\u001B[37m"));
				state++;
				this.currentRoom = "Bridge";
			}
			else if(message.getMessage() == this.currentRoom && state == 4) {
				Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT","\u001B[33mThe Bridge seems to be ofline. I'm freaking out. We need to run. Let's head down to the Escape Pods on level 3. We'll need 3 oxygen tanks and 5 batteries to get the pod to launch. Hopefully we can find them on the way.\u001B[37m"));
				state++;
			}
		}
		else if(message.getType().equals("GEN")) {
			String[] trigmessage = MessageSplit(message.getMessage());
			if(trigmessage[0] == "ON") {
				state++;
			}
		}
	}
}
