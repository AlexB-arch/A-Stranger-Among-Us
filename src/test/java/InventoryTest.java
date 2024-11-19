
import static org.junit.Assert.*;

import org.junit.Test;

import text_adventure.items.LaserPistol;
import text_adventure.objects.Inventory;
import text_adventure.objects.Item;

public class InventoryTest {
    
        @Test
        public void testAddItem() {
            Inventory inventory = new Inventory();
            Item item = new LaserPistol();
            inventory.addItem(item);
            assertEquals(1, inventory.size());
        }
    
        @Test
        public void testRemoveItem() {
            Inventory inventory = new Inventory();
            Item item = new LaserPistol();
            inventory.addItem(item);
            inventory.removeItem(item);
            assertEquals(0, inventory.size());
        }
    
        @Test
        public void testInInventory() {
            Inventory inventory = new Inventory();
            Items item = new LaserPistol();
            inventory.addItem(item);
            assertTrue(inventory.inInventory(item));
        }
    
        @Test
        public void testToString() {
            Inventory inventory = new Inventory();
            Items item = new LaserPistol();
            inventory.addItem(item);
            String expected = "------------------\nName: Laser Pistol\nDescription: A high-powered laser pistol for close combat.\nActions: [Fire, Power Cycle, Safely Store]";
            assertEquals(expected, inventory.toString());
        }
    
        @Test
        public void testPrintItems() {
            Inventory inventory = new Inventory();
            Items item = new LaserPistol();
            inventory.addItem(item);        }
    }