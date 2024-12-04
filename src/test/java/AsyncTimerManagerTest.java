import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import text_adventure.AsyncTimerManager;
import text_adventure.Game;
import text_adventure.objects.Message;
import text_adventure.objects.MessageBus;
import text_adventure.objects.TextMessage;
import text_adventure.objects.TimerMessage;
import text_adventure.Subscriber;

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
    private static List<Message> receivedMessages;
    private static CountDownLatch messageLatch;
    private AsyncTimerManager manager;
    private static MessageBus testMessageBus;


    @Before
    public void setUp() {
        testMessageBus = new MessageBus(20, 3);
        testMessageBus.startMessageProcessing();

        Subscriber taskProcessor = new Subscriber(){
            @Override
            public void onMessage(Message message) {
                System.out.println(message.getHeader() + ":" +message.getType() + ":" +message.getMessage().toString());
                if("TIMER".equals(message.getHeader())){
                    receivedMessages.add(message);
                    messageLatch.countDown();
                }
            };
        };

        testMessageBus.registerSubscriber("TIMER", taskProcessor);

        receivedMessages = new ArrayList<>();
        messageLatch = null;
        System.out.println("Setup sub");
        
        manager = new AsyncTimerManager(testMessageBus);


    }

    @After
    public void tearDown() {
        manager.shutdown();
        testMessageBus.shutdown();
        receivedMessages.clear();
    }


    private void setupMessageCapture(int expectedMessageCount) {
        messageLatch = new CountDownLatch(expectedMessageCount);
        receivedMessages.clear();

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

            testMessageBus.publish(createTimerMessage("TIMER_CREATE", payload));

        // Wait for messages
        assertTrue("Did not receive expected messages in time",
                  messageLatch.await(2, TimeUnit.SECONDS));

        // Verify messages
        Message createMessage = receivedMessages.get(1);
        assertEquals("TIMER_CREATED", createMessage.getType());
        JSONObject createResponse = new JSONObject(createMessage.getMessage());
        assertEquals("created", createResponse.getString("status"));
        
        // Verify subsequent messages are ticks
        Message tickMessage = receivedMessages.get(2);
        assertEquals("TIMER_TICK", tickMessage.getType());
        JSONObject tickData = new JSONObject(tickMessage.getMessage());
        assertTrue(tickData.has("timestamp"));
        assertEquals("test-timer", tickData.getString("timerId"));
        System.out.println(receivedMessages);
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

        testMessageBus.publish(createTimerMessage("TIMER_CREATE", payload));


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
            .put("initialDelay", 1L)
            .put("interval", 100L)
            .put("isCountdown", false);

        testMessageBus.publish(createTimerMessage("TIMER_CREATE", createPayload));

        // Wait for first tick
        Thread.sleep(150);

        // Update timer interval
        JSONObject updatePayload = new JSONObject()
            .put("timerId", timerId)
            .put("initialDelay", 1L)
            .put("interval", 200L)
            .put("isCountdown", false);

        testMessageBus.publish(createTimerMessage("TIMER_UPDATE", updatePayload));

        // Wait for messages
        assertTrue("Did not receive expected messages in time",
                  messageLatch.await(1, TimeUnit.SECONDS));
        
        Thread.sleep(150);

        // Verify update message
        boolean foundUpdateMessage = receivedMessages.stream()
        .anyMatch( m -> new JSONObject(m.getMessage()).has("status") && new JSONObject(m.getMessage()).getString("status").equals("updated"));
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
            

        testMessageBus.publish(createTimerMessage("TIMER_CREATE", createPayload));

        // Wait for first tick
        Thread.sleep(150);

        // Delete timer
        JSONObject deletePayload = new JSONObject()
            .put("timerId", timerId);

        testMessageBus.publish(createTimerMessage("TIMER_DELETE", deletePayload));

        // Wait for messages
        assertTrue("Did not receive expected messages in time",
                  messageLatch.await(1, TimeUnit.SECONDS));
                  
                  
        // Verify deletion
        boolean deleteMessage = receivedMessages.stream().anyMatch(
            m -> new JSONObject(m.getMessage()).has("status") && new JSONObject(m.getMessage()).getString("status").equals("deleted")
        );
  

        assertTrue("deleted", deleteMessage);

        // Wait to ensure no more ticks
        Thread.sleep(200);
        assertEquals("Should not receive more ticks after deletion",
                    6, receivedMessages.size());
    }
    // Problem child
    @Test
     public void testConcurrentTimers() throws InterruptedException {
        // Expect: 2 creation messages + 2 ticks from each timer
        setupMessageCapture(6);

        // Create first timer
        JSONObject payload1 = new JSONObject()
            .put("timerId", "timer1")
            .put("initialDelay", 0L)
            .put("interval", 100L)
            .put("isCountdown", true)
            .put("duration", 550L);


        testMessageBus.publish(createTimerMessage("TIMER_CREATE", payload1));

        // Create second timer
        JSONObject payload2 = new JSONObject()
            .put("timerId", "timer2")
            .put("initialDelay", 0L)
            .put("interval", 100L)
            .put("isCountdown", true)
            .put("duration", 550L);


        testMessageBus.publish(createTimerMessage("TIMER_CREATE", payload2));

        // Wait for messages
        assertTrue("Did not receive expected messages in time",
                  messageLatch.await(3, TimeUnit.SECONDS));

        // Verify creation messages
        long createCount = receivedMessages.stream()
            .filter(m -> m.getType().equals("TIMER_CREATED"))
            .count();
        assertEquals("Should have 2 creation messages", 2, createCount);
        
        Thread.sleep(550);

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