package text_adventure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.json.*;

import text_adventure.objects.NPC;

public class DialogueManager {

    private static String filePath = "src/main/java/text_adventure/resources/dialogue.json";

    private HashMap<String, String> dialogue = new HashMap<String, String>();

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
        System.out.println(name); // for debugging

        JSONObject dialogueObject = retrieveDialogue(name);
        System.out.println(dialogueObject); // for debugging

        JSONObject dialogue = dialogueObject.getJSONObject("dialogue");
        String[] keys = JSONObject.getNames(dialogue);
        for (String key : keys) {
            String value = dialogue.getString(key);
            npc.setDialogue(key, value);
        }
    }

    public static String getFilePath() {
        return filePath;
    }
}