package text_adventure.objects.triggers;

import org.json.JSONObject;

import text_adventure.Game;
import text_adventure.Subscriber;
import text_adventure.objects.Message;
import text_adventure.objects.TimerMessage;
 
public class GameOverTimer implements Subscriber {


    public GameOverTimer(){

        JSONObject countdownPayload = new JSONObject()
        .put("timerId", "stranger_timer")
        .put("initialDelay", 0L)
        .put("interval", 5000L)
        .put("isCountdown", true)
        .put("duration", 30000L);

        Game.globalEventBus.registerSubscriber("TIMER", this);

        Game.globalEventBus.publish(new TimerMessage("TIMER",
        "TIMER_CREATE",
        countdownPayload.toString()
        ));



    }


    @Override
    public void onMessage(Message message) {
        if ("TIMER".equals(message.getHeader())) {
        JSONObject messageData = new JSONObject(message.getMessage());
        switch (message.getType()) {
            case "TIMER_TICK":
                System.out.println("SPOOPY tick: " + messageData.getString("timerId"));
                break;
            case "TIMER_COMPLETED":
                System.out.println("DEAD completed: " + messageData.getString("timerId"));
                break;
        }
     }
    }
    
};