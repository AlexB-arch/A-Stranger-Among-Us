import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.Before;
import text_adventure.Game;
import text_adventure.objects.Item;
import text_adventure.objects.Player;
import text_adventure.objects.Room;
import text_adventure.resources.Directions;
import text_adventure.objects.Inventory;

public class CompletetheGameTest {
    private Game game;
    private Player player;

    @Before
    public void setUp() {
        game = new Game();
        player = Game.player;
    }

    // Helper method to collect items
    private void collectItems(String itemName, int quantity) {
        for (Room room : game.gameWorld.values()) {
            Inventory inventory = room.getInventory();
            if (inventory != null && inventory.size() > 0) {
                player.setCurrentLocation(room);
                for (Item item : new ArrayList<>(inventory.items.values())) {
                    if (item.getName().equals(itemName) && quantity > 0) {
                        player.takeItem(item);
                        inventory.removeItem(item);
                        quantity--;
                    }
                }
            }
        }
    }

    // Test to collect 3 oxygen tanks and 5 batteries, then go to ejection bay and interact with the eject button
    @Test
    public void testCompleteGame() {
        // Collect 3 oxygen tanks
        collectItems("Oxygen Tanks", 3);

        // Collect 5 batteries
        collectItems("Batteries", 5);

        //collect yellow keycard
        collectItems("Yellow Keycard", 1);
        collectItems("Blue Keycard", 1);

        
        game.endGame.genState = true;


        // Navigate to Ejection Bay
        game.runCommands("go south"); // To Barracks
        game.runCommands("go east"); // To Barracks Hallway
        game.runCommands("go east"); // To Mess Hall
        game.runCommands("go east"); // To Botany Hallway
        game.runCommands("go north"); // To TurboLift Deck 1
        game.runCommands("go down"); // To TurboLift Deck 2
        game.runCommands("go south"); // To Hallway Waste
        game.runCommands("go west"); // To Waste Control
        game.runCommands("go south");
        game.runCommands("go south"); 
        game.runCommands("go south");
        game.runCommands("go south");
        game.runCommands("go down"); // To TurboLift Deck 3 Waste Ejection
        game.runCommands("go west");
        game.runCommands("go west"); // To Hallway Ejection
        game.runCommands("go west"); 
        game.runCommands("go west"); 

        // Interact with the eject button
        game.runCommands("interact with eject button");

    }
}