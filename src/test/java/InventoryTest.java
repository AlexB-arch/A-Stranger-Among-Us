
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Optional;

import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;


import text_adventure.Parser;
import text_adventure.Game;
import text_adventure.resources.WordType;


import text_adventure.objects.Item;
import text_adventure.objects.LaserPistol;
import text_adventure.objects.Key;
import text_adventure.objects.Inventory;

public class InventoryTest {

    @Test
    public void testAddItem() {
        Inventory inventory = new Inventory();
        Item item = new LaserPistol();
        inventory.addItem(item);

        assertEquals(1, inventory.size());
        assertTrue(inventory.contains(item));
    }

    @Test
    public void testTakeItemExistingItem() {
        Inventory inventory = new Inventory();
        Item item = new LaserPistol();
        inventory.addItem(item);

        Optional<Item>takenItem = inventory.takeItem("Laser Pistol");
        assertTrue(takenItem.isPresent());
        assertEquals(item, takenItem.get());

        assertEquals(0, inventory.size());
    }

    @Test
    public void testTakeItemNonExistingItem() {
        Inventory inventory = new Inventory();
        Item item = new LaserPistol();
        inventory.addItem(item);

        Optional<Item> takenItem = inventory.takeItem("Shield");
        assertFalse(takenItem.isPresent());

        assertEquals(1, inventory.size());
    }

}
