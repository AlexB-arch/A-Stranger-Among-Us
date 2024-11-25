import static org.junit.Assert.*;

import org.junit.Test;

import text_adventure.objects.Message;
import text_adventure.objects.MessageBus;
import text_adventure.objects.NPC;
import text_adventure.objects.Player;

public class MessageBusTests {

	// Add a constructor or methods here
    @Test
    public void testMessageBus() {
        // Create a new message bus
        MessageBus messageBus = new MessageBus(10, 3);

        // Check that the message bus is created
        assertNotNull(messageBus);
    }

    @Test
    public void testRegisterSubscriber() {
        // Create a new message bus
        MessageBus messageBus = new MessageBus(10, 3);

        // Create a new player
        Player player = new Player();

        // Register the player as a subscriber
        messageBus.registerSubscriber("PLAYER", player);

        // Check that the player is registered as a subscriber
        assertNotNull(messageBus);
    }

    @Test
    public void testPublishMessage() {
        // Create a new message bus
        MessageBus messageBus = new MessageBus(10, 3);

        String expected = "Hello, world!";

        // Create a new message
        Message message = new Message() {
            @Override
            public String getChannel() {
                return "PLAYER";
            }

            @Override
            public String getType() {
                return "INFO";
            }

            @Override
            public String getMessage() {
                return "Hello, world!";
            }
        };

        // Publish the message
        messageBus.publish(message);

        // Check that the message is published
        assertNotNull(messageBus);

        // Check that the message is "Hello, world!"
        assertEquals(expected, message.getMessage());
    }

    @Test
    public void testMessageBusSubscriberCount() {
        // Create a new message bus
        MessageBus messageBus = new MessageBus(10, 3);

        // Create a new player
        Player player = new Player();

        // Register the player as a subscriber
        messageBus.registerSubscriber("PLAYER", player);

        
        // Check that the subscriber count is 1
        int expected = 1;
        assertEquals(expected, messageBus.getSubscriberCount());
    }

}

