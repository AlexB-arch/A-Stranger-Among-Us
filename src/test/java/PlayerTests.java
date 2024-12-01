import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import text_adventure.objects.Inventory;
import text_adventure.objects.Item;
import text_adventure.objects.NPC;
import text_adventure.objects.Player;
import text_adventure.objects.Room;

public class PlayerTests {
    
    @Test
    public void testPlayerLootItem() {
        Room room = new Room("Garden", "A beautiful garden.", new Inventory());
        Item item = new Item("Flower", "A red rose.", true, true, null);
        room.getInventory().addItem(item);
        
        Player player = new Player();
        player.setCurrentLocation(room);
        player.takeItem("Flower");
        
        assertTrue(player.inventory.getItemByName("Flower") != null);
        assertNull(room.getInventory().getItemByName("Flower"));
    }

    @Test
    public void testPlayerGiveItemToNPC() {
        Room room = new Room("Library", "Quiet library.", new Inventory());
        Item item = new Item("Book", "An old manuscript.", true, true, null);
        room.getInventory().addItem(item);
        
        Player player = new Player();
        player.setCurrentLocation(room);
        player.takeItem("Book");
        
        NPC npc = new NPC("Librarian", room, "Book");
        player.giveItemToNPC("Book", npc);
        
        assertTrue(npc.getInventory().getItemByName("Book") != null);
        assertTrue(player.inventory.getItemByName("Book") == null);
        assertTrue(npc.isPuzzleSolved());
    }
}
