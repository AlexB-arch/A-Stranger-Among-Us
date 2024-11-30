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
        Room room = new Room("Tower", "A tall tower.", new Inventory());
        NPC npc = new NPC("Guard", room, "Shield");
        
        Item correctItem = new Item("Shield", "Protects the tower.", true, true, null);
        Item wrongItem = new Item("Helmet", "Protects the head.", true, true, null);
        
        npc.receiveItem(correctItem);
        assertTrue(npc.isPuzzleSolved());
        assertTrue(npc.getInventory().getItemByName("Shield") != null);
        
        npc.receiveItem(wrongItem);
        assertFalse(npc.getInventory().getItemByName("Helmet") != null);
    }
}
