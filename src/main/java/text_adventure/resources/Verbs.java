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
        "quit",
        "exit",
        "use",
        "unlock",
        "lock",
        "read",
        "inventory",
        "talk",
        "interact",
        "debug"
    };
    
    public static void insertVerbs(HashMap<String, WordType> dictionary) {
        for (String word : verbs) {
            dictionary.put(word, WordType.VERB);
        }
    }
}
