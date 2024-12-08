import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import text_adventure.Game;
import text_adventure.objects.Inventory;
import text_adventure.objects.Item;
import text_adventure.objects.Player;
import text_adventure.objects.Room;

public class RoomTest {
    Game game;
    Player player;

    @Before
    public void setUp() {
        game = new Game();
        player = Game.player;
    }


    @Test
    public void testAddItemsToRoom() {
        Room room = new Room("Garden", "A beautiful garden.", new Inventory());
        Item item = new Item("Flower", "A red rose.", true, true, null);
        room.getInventory().addItem(item);
        assertEquals(item, room.getInventory().getItemByName("Flower"));
    }

    @Test
    public void testRoomCount() {
        int initialCount = Room.getRoomCount();
        new Room("Dungeon", "A dark dungeon.", null);
        assertEquals(initialCount + 1, Room.getRoomCount());
    }

    @Test
    public void testRoomConnections() {
        Room room1 = new Room("Room1", "Description1", null);
        Room room2 = new Room("Room2", "Description2", null);
        Room room3 = new Room("Room3", "Description3", null);
        Room room4 = new Room("Room4", "Description4", null);

        room1.setNorth(room2);
        room2.setSouth(room1);

        room2.setEast(room3);
        room3.setWest(room2);

        room3.setSouth(room4);
        room4.setNorth(room3);

        assertEquals(room2, room1.getNorth());
        assertEquals(room1, room2.getSouth());

        assertEquals(room3, room2.getEast());
        assertEquals(room2, room3.getWest());

        assertEquals(room4, room3.getSouth());
        assertEquals(room3, room4.getNorth());
    }

    @Test
    public void testRoomLoot() {
        Room room = new Room("Room", "Description", new Inventory());
        Item item = new Item("Sword", "A sharp sword.", true, true, null);
        room.getInventory().addItem(item);
        assertEquals(item, room.getInventory().getItemByName("Sword"));

        room.getInventory().removeItem(item);
        assertEquals(null, room.getInventory().getItemByName("Sword"));
    }

    @Test
    public void testRoomRemoveItem() {
        Room room = new Room("Room", "Description", new Inventory());
        Item item = new Item("Sword", "A sharp sword.", true, true, null);
        room.getInventory().addItem(item);
        assertEquals(item, room.getInventory().getItemByName("Sword"));

        room.getInventory().removeItem(item);
        assertEquals(null, room.getInventory().getItemByName("Sword"));
    }

    @Test
    public void testRoomGetItemByName() {
        Room room = new Room("Room", "Description", new Inventory());
        Item item = new Item("Sword", "A sharp sword.", true, true, null);
        room.getInventory().addItem(item);
        assertEquals(item, room.getInventory().getItemByName("Sword"));
    }

    @Test
    public void testRoomGetInventory() {
        Room room = new Room("Room", "Description", new Inventory());
        assertEquals(0, room.getInventory().size());
    }

    @Test
    public void testRoomGetDescription() {
        Room room = new Room("Room", "Description", new Inventory());
        assertEquals("Description", room.getBaseDescription());
    }

}