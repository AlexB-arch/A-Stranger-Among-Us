package text_adventure.objects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class DialogueLoader {
    private Map<String, Map<String, String>> dialogues;

    public DialogueLoader(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            Type type = new TypeToken<Map<String, Map<String, String>>>() {}.getType();
            dialogues = gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDialogue(String npcName, String state) {
        if (dialogues.containsKey(npcName)) {
            Map<String, String> npcDialogues = dialogues.get(npcName);
            return npcDialogues.getOrDefault(state, npcDialogues.get("default"));
        }
        return "The NPC doesn't respond.";
    }
}