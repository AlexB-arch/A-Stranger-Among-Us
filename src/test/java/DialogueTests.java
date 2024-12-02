import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.json.*;
import org.junit.Test;

import text_adventure.ResourceManager;
import text_adventure.objects.NPC;
import text_adventure.objects.Room;

public class DialogueTests {
    // Initialize NPC
    private NPC npc = new NPC("alice", new Room("Test Room", "Test Description", null, null, null));

    // Initialize dialogue map
    private HashMap<String, String> dialogue = new HashMap<String, String>();

    // Get the path to the resource JSON file
    private File file = new File("src/main/java/text_adventure/resources/npcresources.json");;

    @Test
    public void testEmptyMap() {
        // Check that dialogue is empty
        int expected = 0;

        assertEquals(dialogue.size(), expected);
    }

    @Test
    public void findDialogueFile() {
        // Check that the file exists
        assertTrue(file.exists());
    }

    @Test
    public void printDialogueFromJson() {
        // Load the dialogue map
        JSONObject dialogue = ResourceManager.retrieveDialogue(file.toString());

        // Test that the dialogue file is not null
        assertTrue(dialogue != null);
    }

    @Test
    public void testJsonValues() throws IOException {
        String expected = "Who's there?";
        
        String content = new String(Files.readAllBytes(Paths.get(file.toString())));

        // Get alice's dialogue
        JSONObject dialogue = new JSONObject(content);
        JSONObject alice = dialogue.getJSONObject("alice");
        String aliceDialogue = alice.getJSONObject("dialogue").optString("default"); // Get the default dialogue
       
        assertEquals(aliceDialogue, expected);
    }

    @Test
    public void testLoadDialogueMap() {
        String currentState = "default";
        String expected = "Who's there?";
        // Load the dialogue map
        JSONObject content = ResourceManager.retrieveDialogue(file.toString());

        // Load the dialogue map
        ResourceManager.loadDialogueMap(content, npc);

        // Test that the dialogue map is not empty
       assertEquals(npc.getDialogue(currentState), expected);
    }
}