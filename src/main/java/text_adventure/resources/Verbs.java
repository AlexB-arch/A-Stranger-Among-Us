package text_adventure.resources;

import java.util.HashMap;

/**
 * Verbs - Alex
 * This is one of the dictionary classes to handle verbs.
 * 
 */

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
        "quit",
        "exit",
        "use",
        "unlock",
        "lock",
        "read",
        "inventory",
        "talk",
        "interact",
        "debug",
        "press",
    };
    
    public static void insertVerbs(HashMap<String, WordType> dictionary) {
        for (String word : verbs) {
            dictionary.put(word, WordType.VERB);
        }
    }
}
