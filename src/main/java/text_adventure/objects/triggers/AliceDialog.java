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
				Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT","Hi I'm Alice."));
				state++;
				}
			else if(message.getMessage() == this.currentRoom && state == 2) {
				Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT","Go slap the Genny Baka!"));
				}
			else if(message.getMessage() == "Engine Room Storage" && state == 2) {
				state++;
				}
			else if(message.getMessage() == this.currentRoom && state == 3) {
				Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT","Douglass is dead! We need go get the gun!"));
				state++;
				this.currentRoom = "Weapons Bay";
			}
			else if(message.getMessage() == this.currentRoom && state == 4) {
				Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT","We need Calhoun, let's go to the Bridge"));
				state++;
				this.currentRoom = "Bridge";
			}
			else if(message.getMessage() == this.currentRoom && state == 5) {
				Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT","Bridge no worko! We need to find Calhoun ourselves."));
				state = 10;
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
