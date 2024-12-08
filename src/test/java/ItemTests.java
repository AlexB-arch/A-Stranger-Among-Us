import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import text_adventure.Game;
import text_adventure.objects.Container;
import text_adventure.objects.Inventory;
import text_adventure.objects.Item;
import text_adventure.objects.ItemHolder;
import text_adventure.objects.ItemList;
import text_adventure.objects.LootManager;
import text_adventure.objects.Player;
import text_adventure.objects.Room;

public class ItemTests {


    Game game;
    Player player;

    @Before
    public void setUp() {
        game = new Game();
        player = Game.player;
    }

    @Test
    public void testItemHolder() {
        ItemHolder itemHolder = new ItemHolder("itemHolder", "description", null, null);
        assertEquals("itemHolder", itemHolder.getName());
        assertEquals("description", itemHolder.getDescription());
        assertNull(itemHolder.getContainer());
    }

    @Test
    public void testItemList() {
        ItemList itemList = new ItemList("itemList");
        assertEquals("itemList", itemList.getName());
    }

    @Test
    public void testLootManager() {
        Item item = new Item("item", "description", null);
        ItemHolder itemHolder = new ItemHolder("itemHolder", "description", null, null);
        LootManager lootManager = new LootManager(item, itemHolder);
        assertEquals(item, lootManager.geItem());
        assertEquals(itemHolder, lootManager.getItemHolder());
        assertEquals(itemHolder.getItems(), lootManager.getItems());
    }

    @Test
    public void testItemCreation() {
        Item item = new Item("Sword", "A sharp blade.", true, true, null);
        assertEquals("Sword", item.getName());
        assertEquals("A sharp blade.", item.getDescription());
        assertTrue(item.isTakable());
        assertTrue(item.isMovable());
    }

    @Test
    public void testItemCount() {
        int initialCount = Item.getInstancedItemCount();
        new Item("Shield", "Protects against attacks.", true, true, null);
        assertEquals(initialCount + 1, Item.getInstancedItemCount());
    }

    @Test
    public void testContainerContainsItems() {
        Container container = new Container("Chest", "A wooden chest.", null, null, true);
        Item item1 = new Item("Key", "Opens a door.", true, true, container);
        Item item2 = new Item("Coin", "A shiny gold coin.", true, true, container);
        
        container.addItem(item1);
        container.addItem(item2);
        
        assertTrue(container.getInventory().getItemByName("Key") != null);
        assertTrue(container.getInventory().getItemByName("Coin") != null);
    }

    @Test
    public void testCollectAllItemsInRooms() {
        Map<String, Room> rooms = Game.gameWorld;
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
