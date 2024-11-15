import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import text_adventure.AsyncTimerManager;
import text_adventure.objects.Message;
import text_adventure.objects.TimerMessage;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class AsyncTimerManagerTest {
    private AsyncTimerManager manager;
    private List<Message> receivedMessages;
    private CountDownLatch messageLatch;

    @Before
    public void setUp() {
        manager = new AsyncTimerManager();
        receivedMessages = new ArrayList<>();
        messageLatch = null;
    }

    @After
    public void tearDown() {
        manager.shutdown();
    }

    private void setupMessageCapture(int expectedMessageCount) {
        messageLatch = new CountDownLatch(expectedMessageCount);
        receivedMessages.clear();
        
        manager.addSubscriber(message -> {
            receivedMessages.add(message);
            messageLatch.countDown();
        });
    }

    private Message createTimerMessage(String type, JSONObject payload) {
        return new TimerMessage("TIMER", type, payload.toString());
    }

    @Test
    public void testCreateRegularTimer() throws InterruptedException {
        // Expect: 1 creation message + 2 ticks
        setupMessageCapture(3);

        // Create a timer that ticks every 100ms
        JSONObject payload = new JSONObject()
            .put("timerId", "test-timer")
            .put("initialDelay", 0L)
            .put("interval", 100L)
            .put("isCountdown", false);

        manager.onMessage(createTimerMessage("TIMER_CREATE", payload));

        // Wait for messages
        assertTrue("Did not receive expected messages in time",
                  messageLatch.await(1, TimeUnit.SECONDS));

        // Verify messages
        Message createMessage = receivedMessages.get(0);
        assertEquals("TIMER_CREATE", createMessage.getType());
        JSONObject createResponse = new JSONObject(createMessage.getMessage());
        assertEquals("created", createResponse.getString("status"));
        
        // Verify subsequent messages are ticks
        Message tickMessage = receivedMessages.get(1);
        assertEquals("TIMER_TICK", tickMessage.getType());
        JSONObject tickData = new JSONObject(tickMessage.getMessage());
        assertTrue(tickData.has("timestamp"));
        assertEquals("test-timer", tickData.getString("timerId"));
    }

    @Test
    public void testCreateCountdownTimer() throws InterruptedException {
        // Expect: 1 creation message + 2 ticks + 1 completion
        setupMessageCapture(4);

        JSONObject payload = new JSONObject()
            .put("timerId", "countdown-timer")
            .put("initialDelay", 0L)
            .put("interval", 100L)
            .put("isCountdown", true)
            .put("duration", 250L);

        manager.onMessage(createTimerMessage("TIMER_CREATE", payload));


        // Wait for messages
        assertTrue("Did not receive expected messages in time",
                  messageLatch.await(1, TimeUnit.SECONDS));

        // Verify countdown completion
        Message lastMessage = receivedMessages.get(receivedMessages.size()-1);
        assertEquals("TIMER_TICK", lastMessage.getType());
        JSONObject completionData = new JSONObject(lastMessage.getMessage());
        assertEquals("countdown-timer", completionData.getString("timerId"));
    }

    @Test
    public void testUpdateTimer() throws InterruptedException {
        // Expect: 1 creation + 1 tick + 1 update + 1 tick with new interval
        setupMessageCapture(4);

        String timerId = "update-test-timer";

        // Create initial timer
        JSONObject createPayload = new JSONObject()
            .put("timerId", timerId)
            .put("initialDelay", 0L)
            .put("interval", 100L)
            .put("isCountdown", false);

        manager.onMessage(createTimerMessage("TIMER_CREATE", createPayload));

        // Wait for first tick
        Thread.sleep(150);

        // Update timer interval
        JSONObject updatePayload = new JSONObject()
            .put("timerId", timerId)
            .put("initialDelay", 0L)
            .put("interval", 200L)
            .put("isCountdown", false);

        manager.onMessage(createTimerMessage("TIMER_UPDATE", updatePayload));

        // Wait for messages
        assertTrue("Did not receive expected messages in time",
                  messageLatch.await(1, TimeUnit.SECONDS));

        // Verify update message
        boolean foundUpdateMessage = receivedMessages.stream()
            .anyMatch(m -> m.getType().equals("TIMER_UPDATE") &&
                     new JSONObject(m.getMessage()).getString("status").equals("updated"));
        assertTrue("Update message not found", foundUpdateMessage);
    }

    @Test
    public void testDeleteTimer() throws InterruptedException {
        // Expect: 1 creation + 1 tick + 1 deletion
        setupMessageCapture(3);

        String timerId = "delete-test-timer";

        // Create timer
        JSONObject createPayload = new JSONObject()
            .put("timerId", timerId)
            .put("initialDelay", 0L)
            .put("interval", 100L)
            .put("isCountdown", false);

        manager.onMessage(createTimerMessage("TIMER_CREATE", createPayload));

        // Wait for first tick
        Thread.sleep(150);

        // Delete timer
        JSONObject deletePayload = new JSONObject()
            .put("timerId", timerId);

        manager.onMessage(createTimerMessage("TIMER_DELETE", deletePayload));

        // Wait for messages
        assertTrue("Did not receive expected messages in time",
                  messageLatch.await(1, TimeUnit.SECONDS));

        // Verify deletion
        Message deleteMessage = receivedMessages.stream()
            .filter(m -> m.getType().equals("TIMER_DELETE"))
            .findFirst()
            .orElseThrow();
        JSONObject deleteResponse = new JSONObject(deleteMessage.getMessage());
        assertEquals("deleted", deleteResponse.getString("status"));

        // Wait to ensure no more ticks
        Thread.sleep(200);
        assertEquals("Should not receive more ticks after deletion",
                    3, receivedMessages.size()-1);
    }

    @Test
    public void testMultipleSubscribers() throws InterruptedException {
        AtomicInteger subscriber1Count = new AtomicInteger(0);
        AtomicInteger subscriber2Count = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(4); // 2 ticks * 2 subscribers

        // Add two subscribers
        manager.addSubscriber(message -> {
            if (message.getType().equals("TIMER_TICK")) {
                subscriber1Count.incrementAndGet();
                latch.countDown();
            }
        });

        manager.addSubscriber(message -> {
            if (message.getType().equals("TIMER_TICK")) {
                subscriber2Count.incrementAndGet();
                latch.countDown();
            }
        });

        // Create timer
        JSONObject payload = new JSONObject()
            .put("timerId", "multi-subscriber-timer")
            .put("initialDelay", 0L)
            .put("interval", 100L)
            .put("isCountdown", false);

        manager.onMessage(createTimerMessage("TIMER_CREATE", payload));

        // Wait for messages
        assertTrue("Did not receive expected messages in time",
                  latch.await(1, TimeUnit.SECONDS));

        assertEquals("Subscriber 1 should receive 2 ticks", 2, subscriber1Count.get());
        assertEquals("Subscriber 2 should receive 2 ticks", 2, subscriber2Count.get());
    }

    @Test
    public void testConcurrentTimers() throws InterruptedException {
        // Expect: 2 creation messages + 2 ticks from each timer
        setupMessageCapture(6);

        // Create first timer
        JSONObject payload1 = new JSONObject()
            .put("timerId", "timer1")
            .put("initialDelay", 0L)
            .put("interval", 100L)
            .put("isCountdown", false);

        manager.onMessage(createTimerMessage("TIMER_CREATE", payload1));

        // Create second timer
        JSONObject payload2 = new JSONObject()
            .put("timerId", "timer2")
            .put("initialDelay", 0L)
            .put("interval", 100L)
            .put("isCountdown", false);

        manager.onMessage(createTimerMessage("TIMER_CREATE", payload2));

        // Wait for messages
        assertTrue("Did not receive expected messages in time",
                  messageLatch.await(1, TimeUnit.SECONDS));

        // Verify creation messages
        long createCount = receivedMessages.stream()
            .filter(m -> m.getType().equals("TIMER_CREATE"))
            .count();
        assertEquals("Should have 2 creation messages", 2, createCount);

        // Verify ticks from both timers
        long tickCount = receivedMessages.stream()
            .filter(m -> m.getType().equals("TIMER_TICK"))
            .count();
        assertTrue("Should have at least 4 ticks", tickCount >= 4);

        // Verify ticks come from both timers
        Set<String> timerIds = receivedMessages.stream()
            .filter(m -> m.getType().equals("TIMER_TICK"))
            .map(m -> new JSONObject(m.getMessage()).getString("timerId"))
            .collect(Collectors.toSet());
        assertEquals("Should have ticks from both timers", 2, timerIds.size());
    }

}