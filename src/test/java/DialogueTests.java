import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.*;
import org.junit.Test;

import text_adventure.ResourceManager;
import text_adventure.objects.NPC;
import text_adventure.objects.Player;
import text_adventure.objects.Room;

public class DialogueTests {
    // Initialize NPC
    private NPC npc = new NPC("alice", new Room("Test Room", "Test Description", null));

    // Initialize dialogue map
    private HashMap<String, String> dialogue = new HashMap<String, String>();

    // Get the path to the dialogue JSON file
    private File file = new File("src/main/java/text_adventure/resources/dialogue.json");;

    // Initialize player
    private Player player = new Player();


    @Test
    public void testEmptyMap() {
        // Check that dialogue is empty
        int expected = 0;

        assertEquals(dialogue.size(), expected);
    }

    @Test
    public void findDialogueFile() {
        // Create a test file
        file = new File("src/main/java/text_adventure/resources/dialogue.json");

        // Check that the file exists
        assertTrue(file.exists());
    }

    @Test
    public void printDialogueFromJson() {
        // Load the dialogue map
        JSONObject dialogue = ResourceManager.retrieveDialogue("src/main/java/text_adventure/resources/dialogue.json");

        // Test that the dialogue file is not null
        assertTrue(dialogue != null);
    }

    @Test
    public void testDialogueMap() throws IOException {
        String expected = "Hello, I'm Alice.";

        String content = new String(Files.readAllBytes(Paths.get("src/main/java/text_adventure/resources/dialogue.json")));

        // Get alice's dialogue
        JSONObject dialogue = new JSONObject(content);
        JSONObject alice = dialogue.getJSONObject("alice");
        String aliceDialogue = alice.getJSONObject("dialogue").optString("default"); // Get the default dialogue
       
        assertEquals(aliceDialogue, expected);
    }
}