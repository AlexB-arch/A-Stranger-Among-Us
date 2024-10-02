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
    
    public static void insertVerbs(HashMap<String, WordType> dictionary) {
        for (String word : verbs) {
            dictionary.put(word, WordType.VERB);
        }
    }
}
