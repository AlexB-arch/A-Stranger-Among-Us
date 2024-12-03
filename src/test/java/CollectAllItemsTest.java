import org.junit.Before;
import org.junit.Test;

import text_adventure.Game;
import text_adventure.objects.Item;
import text_adventure.objects.Player;
import text_adventure.objects.Room;
import text_adventure.objects.Inventory;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class CollectAllItemsTest {

    private Game game;
    private Player player;

    @Before
    public void setUp() {
        game = new Game();
        player = Game.player;
    }

    @Test
    public void testCollectAllItemsInRooms() {
        Map<String, Room> rooms = game.gameWorld;
        Map<String, Integer> itemQuantities = new HashMap<>();

        for (Room room : rooms.values()) {
            Inventory inventory = room.getInventory();
            if (inventory != null && inventory.size() > 0) {
                // Teleport player to the room
                player.setCurrentLocation(room);

                System.out.println("In room: " + room.getName());

                // Create a copy to avoid concurrent modification
                for (Item item : new ArrayList<>(inventory.items.values())) {
                    player.takeItem(item);
                    inventory.removeItem(item);

                    String itemName = item.getName();
                    itemQuantities.put(itemName, itemQuantities.getOrDefault(itemName, 0) + 1);

                    System.out.println("Picked up: " + itemName);
                }
            }
        }

        System.out.println("\nTotal items collected:");
        for (Map.Entry<String, Integer> entry : itemQuantities.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}