import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import text_adventure.Game;
import text_adventure.objects.Player;
import text_adventure.objects.Room;
import text_adventure.objects.Item;

public class DynaLinksTest {
    
    private Game game;
    private Player player;

    @Before
    public void setUp() {
        game = new Game();
        player = Game.player;
    }

    @Test
    public void testDynamicLinks() {
        // Navigate to Generator Room
        game.runCommands("go south"); // To Barracks
        game.runCommands("go east"); // To Barracks Hallway
        game.runCommands("go east"); // To Mess Hall
        game.runCommands("go east"); // To Generator Hallway
        game.runCommands("go north"); // To Generator Room
        game.runCommands("go down");
        game.runCommands("go south");
        game.runCommands("go east");

        Room currentRoom = player.getCurrentLocation();
        // Check if the dynamic links were triggered
        assertEquals("Waste Control Hallway", currentRoom.getName());

        player.inventory.addItem(new Item("blue keycard"));
        game.runCommands("go east");
        Room currentRoom2 = player.getCurrentLocation();
        assertEquals("Waste Control", currentRoom2.getName());
    }
}
