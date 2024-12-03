package text_adventure.objects.triggers;

import org.json.JSONObject;

import text_adventure.Game;
import text_adventure.Subscriber;
import text_adventure.objects.Message;
import text_adventure.objects.TextMessage;
import text_adventure.objects.TimerMessage;
import text_adventure.resources.AmbienceSelector;
 

/*
 * Class that manages the game over and periodic messages as the game is being played.
 * 
 *         .put("initialDelay", 25000L)
        .put("interval", 210000L)
        .put("isCountdown", true)
        .put("duration", 900000L);

 */
public class GameOverTimer implements Subscriber {


    public GameOverTimer(){

        JSONObject countdownPayload = new JSONObject()
        .put("timerId", "stranger_timer")
        .put("initialDelay", 2000L)
        .put("interval", 10000L)
        .put("isCountdown", true)
        .put("duration", 90000L);

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
                Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT",AmbienceSelector.getRandomAmbience()));
                break;
            case "TIMER_COMPLETED":
                Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT","Out of the shadows a figure approaches and cuts you down from behind.."));
                Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT",Game.getInstance().endGame()));
                break;
        }
     }
    }
    
};