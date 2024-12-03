import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import text_adventure.Game;
import text_adventure.objects.Container;
import text_adventure.objects.Inventory;
import text_adventure.objects.Item;
import text_adventure.objects.ItemList;
import text_adventure.objects.NPC;
import text_adventure.objects.Player;
import text_adventure.objects.Room;
import text_adventure.resources.Directions;

public class PlayerTests {

    Game game = new Game();

    @Test
    public void testPlayerInventory() {
        Player player = new Player();
        Inventory inventory = player.inventory;
        Item item = new Item("Sword", "A sharp sword.", true, true, null);
        inventory.addItem(item);
        assertEquals(item, inventory.getItemByName("Sword"));
        inventory.removeItem(item);
        assertNull(inventory.getItemByName("Sword"));
    }

    @Test
    public void testPlayerParty() {
        Player player = new Player();
        NPC npc = new NPC("Bob", null);

        player.party.put("Bob", npc);
        assertTrue(player.party.containsKey("Bob"));

        player.party.remove("Bob");
        assertFalse(player.party.containsKey("Bob"));
    }

    @Test
    public void testPlayerTakeItem() {
        Player player = new Player();
        Room room = new Room("Room", "Description", new Inventory());
        Item item = new Item("Sword", "A sharp sword.", true, true, null);
        room.getInventory().addItem(item);
        player.currentLocation = room;
        player.takeItemByName("Sword");
        assertEquals(item, player.inventory.getItemByName("Sword"));
    }

    @Test
    public void testPlayerGiveItemToNPC() {
        Player player = new Player();
        NPC npc = new NPC("Bob", null);
        Item item = new Item("Sword", "A sharp sword.", true, true, null);
        Room room = new Room("Room", "Description", new Inventory());
        room.getInventory().addItem(item);
        player.currentLocation = room;

        player.takeItemByName("Sword");
        player.giveItemToNPC("Sword", npc);
        assertTrue(npc.inventory.inInventory(item));
        assertFalse(player.inventory.inInventory(item));
    }

    @Test
    public void testNPCGiveItemToPlayer() {
        Player player = new Player();
        NPC npc = new NPC("Bob", null);
        Item item = new Item("Sword", "A sharp sword.", true, true, null);
        Room room = new Room("Room", "Description", new Inventory());
        room.getInventory().addItem(item);
        player.currentLocation = room;

        npc.inventory.addItem(item);
        npc.giveItemToPlayer("Sword", player);
        assertTrue(player.inventory.inInventory(item));
        assertFalse(npc.inventory.inInventory(item));
    }

    /*public void testPlayerUseItem() {
        Player player = new Player();
        Item item = new Item("Sword", "A sharp sword.", true, true, null);
        player.inventory.addItem(item);
        player.useItem("Sword");
        assertFalse(player.inventory.inInventory(item));
    }*/

    @Test
    public void testPlayerAddPartyMember() {
        Player player = new Player();
        NPC npc = new NPC("Bob", null);
        player.addPartyMember(npc);
        assertTrue(player.party.containsKey("Bob"));
    }

    @Test
    public void testPlayerRemovePartyMember() {
        Player player = new Player();
        NPC npc = new NPC("Bob", null);
        player.addPartyMember(npc);
        player.removePartyMember(npc);
        assertFalse(player.party.containsKey("Bob"));
    }
}