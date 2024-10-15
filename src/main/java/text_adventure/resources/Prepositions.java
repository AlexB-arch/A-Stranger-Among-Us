package text_adventure.resources;

import java.util.HashMap;

public class Prepositions {
    static String[] prepositions = {
        "in",
        "on",
        "at",
        "to",
        "under",
        "over",
        "behind",
        "beside",
        "between",
        "inside",
        "outside",
        "through",
        "into",
        "onto",
        "upon",
        "up",
        "down",
        "up to",
        "down to",
        "up from",
        "down from",
        "up on",
        "down on",
        "up off",
        "down off",
    };

    public static void insertPrepositions(HashMap<String, WordType> dictionary) {
        for (String word : prepositions) {
            dictionary.put(word, WordType.PREPOSITION);
        }
    }
}
