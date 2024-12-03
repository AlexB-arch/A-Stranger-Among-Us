//Generator Trigger -Brendan

package text_adventure.objects.triggers;

import org.json.JSONObject;

import text_adventure.Game;
import text_adventure.Subscriber;
import text_adventure.interfaces.Trigger;
import text_adventure.objects.Message;
import text_adventure.objects.TextMessage;
import text_adventure.objects.TimerMessage;


public class GeneratorTrigger implements Trigger, Subscriber {
    private boolean isActivated;

    public GeneratorTrigger() {
        this.isActivated = false;

        JSONObject countdownPayload = new JSONObject()
        .put("timerId", "genny_timer")
        .put("initialDelay", 60000L)
        .put("interval", 270000L)
        .put("isCountdown", false)
        .put("duration", 900000);

        Game.globalEventBus.registerSubscriber("TIMER", this);

         Game.globalEventBus.publish(new TimerMessage("TIMER",
         "TIMER_CREATE",
         countdownPayload.toString()));

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

      if("TIMER".equals(message.getHeader())){
        JSONObject messageData = new JSONObject(message.getMessage());
        if (messageData.getString("timerId").equals("genny_timer")){
            switch (message.getType()) {
                case "TIMER_TICK":
                    this.toggle();
                    break;
                case "TIMER_COMPLETED":
                    this.activate();
                    break;
                default:
                    break;
            }
        }
      }
		}

    @Override
    public boolean Condition() {
        return isActivated;
    }

    private void toggle() {
      if(isActivated == true){
        Game.globalEventBus.publish(new TextMessage("Engine Room", "GEN", "OFF,The generator is offline."));
				Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT","*The alarm system blares on*\n ALERT! GENERATOR OFFLINE \n*As lights flicker off.*"));
        this.isActivated = false;

      }else{
        this.activate();
      }

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
