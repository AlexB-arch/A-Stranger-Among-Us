//Generator Trigger -Brendan

package text_adventure.objects.triggers;

import text_adventure.objects.MessageBus;
import text_adventure.objects.Room;
import text_adventure.Subscriber;


public class AliceDialog implements Subscriber {
    private String currentRoom;
		private int state;
		private boolean genstate;

    public AliceDialog(String RoomName) {
				this.currentRoom = RoomName;
        this.genstate = false;
				this.state = 1;
				Game.globalEventBus.registerSubscriber("TRIGGER", this);
				Game.globalEventBus.registerSubscriber("Mess Hall", this);
				Game.globalEventBus.registerSubscriber("CONSOLE", this);
    }

		public String[] MessageSplit(String Message){
			return Message.split(",");
		}

		@Override
		public void onMessage(Message message) {
			trigmessage = MessageSplit(message);
			if(message.getType().equals("DIAL")) {
				if(message.getMessage() == this.currentRoom && genstate = false && state == 1){
					Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT","Hi I'm Alice."));
					state++;
					}
				else if(message.getMessage() == this.currentRoom && state == 2) {
					Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT","Go slap the Genny Baka!"));
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
	}
}
