package text_adventure;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

import text_adventure.objects.AsyncTimer;
import text_adventure.objects.Message;
import text_adventure.objects.TimerMessage;



// Timer configuration class
class TimerConfig {
    private final String timerId;
    private final long initialDelay;
    private final long interval;
    private final boolean isCountdown;
    private final long duration;

    public TimerConfig(String timerId, long initialDelay, long interval, 
                      boolean isCountdown, long duration) {
        this.timerId = timerId;
        this.initialDelay = initialDelay;
        this.interval = interval;
        this.isCountdown = isCountdown;
        this.duration = duration;
    }

    // Getters
    public String getTimerId() { return timerId; }
    public long getInitialDelay() { return initialDelay; }
    public long getInterval() { return interval; }
    public boolean isCountdown() { return isCountdown; }
    public long getDuration() { return duration; }
}



public class AsyncTimerManager implements Subscriber {
    private final Map<String, AsyncTimer> timers;
    private final Map<String, TimerConfig> timerConfigs;
    private final List<Subscriber> subscribers;

    // Message types as constants
    private static final String TYPE_CREATE = "TIMER_CREATE";
    private static final String TYPE_UPDATE = "TIMER_UPDATE";
    private static final String TYPE_DELETE = "TIMER_DELETE";
    private static final String TYPE_TICK = "TIMER_TICK";
    private static final String TYPE_COMPLETED = "TIMER_COMPLETED";

    public AsyncTimerManager() {
        this.timers = new ConcurrentHashMap<>();
        this.timerConfigs = new ConcurrentHashMap<>();
        this.subscribers = new ArrayList<>();
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    private void publishMessage(Message message) {
        for (Subscriber subscriber : subscribers) {
            subscriber.onMessage(message);
        }
    }

    @Override
    public void onMessage(Message message) {
        if (!"TIMER".equals(message.getHeader())) {
            return; // Ignore non-timer messages
        }

        switch (message.getType()) {
            case TYPE_CREATE:
                handleCreateTimer(message.getMessage());
                break;
            case TYPE_UPDATE:
                handleUpdateTimer(message.getMessage());
                break;
            case TYPE_DELETE:
                handleDeleteTimer(message.getMessage());
                break;
        }
    }

    private void handleCreateTimer(String messageJson) {
        JSONObject payload = new JSONObject(messageJson);
        String timerId = payload.getString("timerId");
        TimerConfig config = createConfigFromPayload(payload);
        
        if (!timers.containsKey(timerId)) {
            AsyncTimer timer = new AsyncTimer();
            
            if (config.isCountdown()) {
                setupCountdownTimer(timer, config);
            } else {
                setupRegularTimer(timer, config);
            }
            
            timers.put(timerId, timer);
            timerConfigs.put(timerId, config);
            
            // Notify creation
            Map<String, Object> response = new HashMap<>();
            response.put("timerId", timerId);
            response.put("status", "created");
            publishMessage(TimerMessage.createMessage(TYPE_CREATE, response));
        }
    }

    private void handleUpdateTimer(String messageJson) {
        JSONObject payload = new JSONObject(messageJson);
        String timerId = payload.getString("timerId");
        AsyncTimer existingTimer = timers.get(timerId);
        
        if (existingTimer != null) {
            existingTimer.stop();
            TimerConfig newConfig = createConfigFromPayload(payload);
            
            if (newConfig.isCountdown()) {
                setupCountdownTimer(existingTimer, newConfig);
            } else {
                setupRegularTimer(existingTimer, newConfig);
            }
            
            timerConfigs.put(timerId, newConfig);
            
            Map<String, Object> response = new HashMap<>();
            response.put("timerId", timerId);
            response.put("status", "updated");
            publishMessage(TimerMessage.createMessage(TYPE_UPDATE, response));
        }
    }

    private void handleDeleteTimer(String messageJson) {
        JSONObject payload = new JSONObject(messageJson);
        String timerId = payload.getString("timerId");
        AsyncTimer timer = timers.remove(timerId);
        
        if (timer != null) {
            timer.stop();
            timer.shutdown();
            timerConfigs.remove(timerId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("timerId", timerId);
            response.put("status", "deleted");
            publishMessage(TimerMessage.createMessage(TYPE_DELETE, response));
        }
    }

    private TimerConfig createConfigFromPayload(JSONObject payload) {
        return new TimerConfig(
            payload.getString("timerId"),
            payload.getLong("initialDelay"),
            payload.getLong("interval"),
            payload.optBoolean("isCountdown", false),
            payload.optLong("duration", 0L)
        );
    }

    private void setupRegularTimer(AsyncTimer timer, TimerConfig config) {
        timer.start(() -> {
            Map<String, Object> tickData = new HashMap<>();
            tickData.put("timerId", config.getTimerId());
            tickData.put("timestamp", System.currentTimeMillis());
            publishMessage(TimerMessage.createMessage(TYPE_TICK, tickData));
        }, config.getInitialDelay(), config.getInterval());
    }

    private void setupCountdownTimer(AsyncTimer timer, TimerConfig config) {
        timer.startCountdown(
            (remaining) -> {
                Map<String, Object> tickData = new HashMap<>();
                tickData.put("timerId", config.getTimerId());
                tickData.put("remaining", remaining);
                tickData.put("timestamp", System.currentTimeMillis());
                publishMessage(TimerMessage.createMessage(TYPE_TICK, tickData));
            },
            () -> {
                Map<String, Object> completedData = new HashMap<>();
                completedData.put("timerId", config.getTimerId());
                completedData.put("timestamp", System.currentTimeMillis());
                publishMessage(TimerMessage.createMessage(TYPE_COMPLETED, completedData));
            },
            config.getDuration(),
            config.getInterval()
        );
    }

    public void shutdown() {
        for (AsyncTimer timer : timers.values()) {
            timer.shutdown();
        }
        timers.clear();
        timerConfigs.clear();
    }
}