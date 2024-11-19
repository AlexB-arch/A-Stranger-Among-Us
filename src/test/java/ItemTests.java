import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import text_adventure.objects.Item;
import text_adventure.interfaces.Items;
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
}
