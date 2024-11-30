import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import text_adventure.objects.Container;
import text_adventure.objects.Item;
import text_adventure.objects.ItemHolder;
import text_adventure.objects.ItemList;
import text_adventure.objects.LootManager;

public class ItemTests {
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
}
