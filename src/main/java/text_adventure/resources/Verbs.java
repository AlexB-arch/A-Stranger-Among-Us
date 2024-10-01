package text_adventure.resources;

import java.util.HashMap;

public class Verbs {

    public static String[] verbs = {
        "test",
        "get",
        "go",
        "take",
        "drop",
        "put",
        "look",
        "open",
        "close",
        "pull",
        "push",
        "north",
        "south",
        "west",
        "east",
        "up",
        "down",
        "quit"
    };
    
    public static HashMap<String, WordType> initializeVerbs() {
        HashMap<String, WordType> verbMap = new HashMap<>();
        for (String word : verbs) {
            verbMap.put(word, WordType.VERB);
        }
        return verbMap;
    }
}
