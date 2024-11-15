import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import text_adventure.objects.Item;
import text_adventure.objects.LaserPistol;
import text_adventure.objects.NPC;
import text_adventure.objects.Player;
import text_adventure.objects.Room;

public class PlayerTests {
    
    Player player = new Player();

    @Test
    public void testAddPartyMember() {

        Room room = new Room("Sleeping Quarters", "A room with beds", null);
        NPC npc = new NPC("Alice", room);

        player.addPartyMember(npc);
        assertTrue(player.getParty().containsKey(npc.getName()));
    }
    
    @Test
    public void testRemovePartyMember() {
        Room room = new Room("Sleeping Quarters", "A room with beds", null);
        NPC npc = new NPC("Bob", room);

        player.addPartyMember(npc);
        player.removePartyMember(npc);
        assertFalse(player.getParty().containsKey(npc.getName()));
    }
    
    @Test
    public void testAddItem() {
        
        Item item = new LaserPistol();
        player.addItem(item);
        assertTrue(player.contains(item));
    }
    
    @Test
    public void testRemoveItem() {
       
        Item item = new LaserPistol();
        player.addItem(item);
        player.removeItem(item);
        assertFalse(player.contains(item));
    }
    
    @Test
    public void testInventorySize() {
        int expected = 1;

        Item item = new LaserPistol();
        player.addItem(item);
        assertEquals(expected, player.size());
    }
    
    @Test
    public void testContains() {
        Item item = new LaserPistol();
        player.addItem(item);
        assertTrue(player.contains(item));
    }
    
    @Test
    public void testPrintItems() {
        Item item = new LaserPistol();
        player.addItem(item);
        player.printItems();
    }
}
