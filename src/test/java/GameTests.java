import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;

import text_adventure.Parser;
import text_adventure.Game;

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
    
}
