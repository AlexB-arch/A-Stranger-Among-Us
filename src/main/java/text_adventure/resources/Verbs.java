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
        "quit",
        "exit",
        "use",
        "unlock",
        "lock",
        "read",
        "inventory",
    };
    
    public static HashMap<String, WordType> initVerbs() {
        HashMap<String, WordType> verbMap = new HashMap<>();
        for (String word : verbs) {
            verbMap.put(word, WordType.VERB);
        }
        return verbMap;
    }
}
