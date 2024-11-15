```java
// Create manager
AsyncTimerManager manager = new AsyncTimerManager();

// Create a regular timer
JSONObject createPayload = new JSONObject()
    .put("timerId", "timer1")
    .put("initialDelay", 0L)
    .put("interval", 1000L)
    .put("isCountdown", false);

manager.onMessage(new TimerMessage(
    "TIMER",
    "TIMER_CREATE",
    createPayload.toString()
));

// Create a countdown timer
JSONObject countdownPayload = new JSONObject()
    .put("timerId", "timer2")
    .put("initialDelay", 0L)
    .put("interval", 1000L)
    .put("isCountdown", true)
    .put("duration", 5000L);

manager.onMessage(new TimerMessage(
    "TIMER",
    "TIMER_CREATE",
    countdownPayload.toString()
));

// Subscribe to timer events
manager.addSubscriber(message -> {
    if ("TIMER".equals(message.getHeader())) {
        JSONObject messageData = new JSONObject(message.getMessage());
        switch (message.getType()) {
            case "TIMER_TICK":
                System.out.println("Timer tick: " + messageData.getString("timerId"));
                break;
            case "TIMER_COMPLETED":
                System.out.println("Timer completed: " + messageData.getString("timerId"));
                break;
        }
    }
});
```