import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import text_adventure.Game;
import text_adventure.objects.Player;

public class TriggerTest {
    
    private Game game;
    private Player player;

    @Before
    public void setUp() {
        game = new Game();
        player = Game.player;
    }

    @Test
    public void testInteractWithGenerator() {
        // Navigate to Generator Room
        game.runCommands("go south"); // To Barracks
        game.runCommands("go east"); // To Barracks Hallway
        game.runCommands("go east"); // To Mess Hall
        game.runCommands("go south"); // To Generator Hallway
        game.runCommands("go south"); // To Generator Room

        // Interact with the generator
        game.runCommands("interact with generator");

        // Check if the generator was triggered
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertEquals(game.generatorTrigger.isActivated, true);
    }

    @Test
    public void testAliceDialog() {
        // Navigate to Mess Hall
        game.runCommands("go south"); // To Barracks
        game.runCommands("go east"); // To Barracks Hallway
        game.runCommands("go east"); // To Mess Hall

        // Check if Alice's dialog was triggered
        assertEquals(game.AliceDial.state, 1);

        game.runCommands("go south"); // To Mess Hall
        game.runCommands("go north"); // To Mess Hall

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertEquals(game.AliceDial.state, 2);
    }
}
