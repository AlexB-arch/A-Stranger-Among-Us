import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;
import org.junit.Before;

import text_adventure.Parser;
import text_adventure.Game;
import text_adventure.resources.WordType;

public class GameTests {

    Game game;

    @Before
    public void setUp() {
        game = new Game();
    }


    @Test
    public void testGameStart() {
        Game.gameStartArt();
        assertTrue(Game.consoleMessage.equals("Welcome to the Space Station Adventure Game!"));
    }

    @Test
    public void testGameEnd() {
        Game.endGame();
        assertTrue(Game.consoleMessage.equals("You've been ejected into the cold vacuum of space. Game Over."));
    }

    // Tests dictionary initialization
    @Test
    public void testDictionary() {

        // Tests that its empty first.
        Parser.dictionary = new HashMap<String, WordType>();
        assertTrue(Parser.dictionary.size() == 0);

        // Initializes the dictionary
        Parser.initDictionary();
        assertTrue(Parser.dictionary.size() > 0);

        // Checks that the dictionary contains the word "go"
        assertTrue(Parser.dictionary.containsKey("go"));

        // Check if the dictionary has the NOUN value for "wires"
        assertTrue(Parser.dictionary.get("wires") == WordType.NOUN);
    }
    
}
