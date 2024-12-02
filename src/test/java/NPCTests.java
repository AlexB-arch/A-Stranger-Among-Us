import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import text_adventure.objects.Inventory;
import text_adventure.objects.Item;
import text_adventure.objects.NPC;
import text_adventure.objects.Room;

public class NPCTests {
    
    @Test
    public void testNPCReceiveItem() {
        Room room = new Room("Room", "Description", new Inventory());
        NPC npc = new NPC("NPC", room);
        Item item = new Item("Item", "Description", null);
        npc.inventory.addItem(item);
        assertTrue(npc.inventory.inInventory(item));
    }

    @Test
    public void testNPCRemoveItem() {
        Room room = new Room("Room", "Description", new Inventory());
        NPC npc = new NPC("NPC", room);
        Item item = new Item("Item", "Description", null);
        npc.inventory.addItem(item);
        npc.inventory.removeItem("Item");
        assertFalse(npc.inventory.inInventory(item));
    }

    @Test
    public void testNPCSetLocation() {
        Room room = new Room("Room", "Description", new Inventory());
        Room room2 = new Room("Room2", "Description", new Inventory());
        NPC npc = new NPC("NPC", room);
        npc.setLocation(room2);
        assertTrue(npc.getLocation() == room2);
    }

    @Test
    public void testNPCSetCurrentState() {
        Room room = new Room("Room", "Description", new Inventory());
        NPC npc = new NPC("NPC", room);
        npc.setCurrentState("State");
        assertTrue(npc.getCurrentState().equals("State"));
    }

    @Test
    public void testNPCSetIsAlive() {
        Room room = new Room("Room", "Description", new Inventory());
        NPC npc = new NPC("NPC", room);
        npc.setIsAlive(true);
        assertTrue(npc.getIsAlive());
    }
}
