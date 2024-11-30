import static org.junit.Assert.assertEquals;

import org.junit.Test;

import text_adventure.objects.Inventory;
import text_adventure.objects.Item;
import text_adventure.objects.Room;

public class RoomTest {

    @Test
    public void testAddItemsToRoom() {
        Inventory roomInventory = new Inventory();
        Room room = new Room("Hall", "A spacious hall.", roomInventory);
        
        Item item = new Item("Lantern", "Lights up the room.", true, true, null);
        roomInventory.addItem(item);
        
        assertEquals(item, roomInventory.getItemByName("Lantern"));
    }

    @Test
    public void testRoomCount() {
        int initialCount = Room.getRoomCount();
        new Room("Dungeon", "A dark dungeon.", null);
        assertEquals(initialCount + 1, Room.getRoomCount());
    }
}