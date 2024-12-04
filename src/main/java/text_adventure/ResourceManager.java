package text_adventure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.*;

import text_adventure.objects.NPC;

/**
 * ResourceManager - Alex
 * This class is responsible for managing the resources of the game.
 * 
 * Primarily, it is responsible for loading the dialogue of NPCs.
 */

public class ResourceManager {

    private static String filePath = "src/main/java/text_adventure/resources/npcresources.json";

    // If file exists, read it
    public static JSONObject retrieveDialogue(String filename) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            return new JSONObject(content);
        } catch (IOException error) {
            error.printStackTrace();
            return null;
        }
    }

    // Populate dialogue map
    public static void loadDialogueMap(JSONObject dialogueMap, NPC npc) {
        String name = npc.getName();

        // Gets all the content of the file
        JSONObject fileContent = retrieveDialogue(filePath);

        // Gets the object of the specific NPC
        JSONObject npcObject = fileContent.getJSONObject(name);

        // Get the pair of dialogue and put it in the dialogue map
        JSONObject dialogueObject = npcObject.getJSONObject("dialogue");        
       
        // Get the keys of the dialogue object
        String[] keys = JSONObject.getNames(dialogueObject);
        // Iterate over the keys
        for (String key : keys) {
            // Get the dialogue for the key
            String value = dialogueObject.getString(key);
            // Set the dialogue for the key
            npc.setDialogue(key, value);
        }
    }
}