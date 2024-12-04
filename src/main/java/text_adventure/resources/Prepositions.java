package text_adventure.resources;

import java.util.HashMap;

/**
 * Prepositions - Alex
 * This is one of the dictionary classes to handle prepositions.
 * 
 */

public class Prepositions {
    static String[] prepositions = {
        "in",
        "on",
        "at",
        "to",
        "with",
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
