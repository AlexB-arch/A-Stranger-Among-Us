//Generator Trigger -Brendan

package text_adventure.objects.triggers;

import text_adventure.objects.Message;
import text_adventure.objects.TextMessage;
import text_adventure.interfaces.Trigger;
import text_adventure.Subscriber;
import text_adventure.Game;


public class GeneratorTrigger implements Trigger, Subscriber {
    private boolean isActivated;

    public GeneratorTrigger() {
        this.isActivated = false;
				Game.globalEventBus.registerSubscriber("TRIGGER", this);
				Game.globalEventBus.registerSubscriber("Engine Room", this);
				Game.globalEventBus.registerSubscriber("CONSOLE", this);
    }

		public String[] MessageSplit(String Message){
			return Message.split(",");
		}

    @Override
		public void onMessage(Message message) {
			if(message.getType().equals("GEN") && message.getMessage() == "OFF") {
        System.out.println("Generator is offline");
				this.isActivated = false;
				Game.globalEventBus.publish(new TextMessage("Engine Room", "GEN", "OFF,The generator is offline."));
				Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT","*The alarm system blares on*\n ALERT! GENERATOR OFFLINE \n*As lights flicker off.*"));
			}
      else if(message.getType().equals("GEN") && message.getMessage() == "ON") {
        activate();
      }
		}

    @Override
    public boolean Condition() {
        return isActivated;
    }

    @Override
    public void activate() {
        if (!isActivated) {
            System.out.println("Generator is online");
            isActivated = true;
            Game.globalEventBus.publish(new TextMessage("TRIGGER", "GEN", "ON,The generator is online."));
						Game.globalEventBus.publish(new TextMessage("Engine Room", "GEN", "ON,The generator is online."));
        }
    }

}
