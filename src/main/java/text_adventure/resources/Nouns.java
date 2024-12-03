package text_adventure.resources;

import java.util.HashMap;


// A list of nouns that the player can interact with
// Can also be used for items in the game

/**
 * Nouns - Alex
 * This is one of the dictionary classes to handle nouns.
 * 
 */

public class Nouns {

    static String[] nouns = {
        "wires",
        "sleeping quarters",
        "lights",
        "task",
        "investigate",
        "north",
        "south",
        "east",
        "west",
        "up",
        "down",
        "alice",
        "douglass",
        "generator",
        "blue keycard",
        "yellow keycard",
        "red keycard",
        "eject button",
        "batteries",
        "oxygen tanks"
        // Add more nouns as needed
    };

    public static void insertNouns(HashMap<String, WordType> dictionary) {
        for (String word : nouns) {
            dictionary.put(word, WordType.NOUN);
        }
    }
}
