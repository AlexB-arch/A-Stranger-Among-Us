import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;

import text_adventure.Game;

public class GameTests {

    Game game = new Game();

    @Test
    public void testGameStart() {
        game.start();
        assertTrue(Game.consoleMessage.equals("You're working on fixing some wires in the sleeping quarters when the lights suddenly go out. You attempt to flick them back on, only to find that they won't react. You decide to put your task on hold to investigate."));
    }

    @Test
    public void testGameEnd() {
        game.endGame();
        assertTrue(Game.consoleMessage.equals("You've been ejected into the cold vacuum of space. Game Over."));
    }
    
}
