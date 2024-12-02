import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import text_adventure.objects.Container;
import text_adventure.objects.Item;

public class ContainerTests {
    @Test
    public void testContainer() {
        Container container = new Container("container", "description", null, null, true);
        assertEquals("container", container.getName());
        assertEquals("description", container.getDescription());
        assertNull(container.getContainer());
    }

    @Test
    public void testContainerItemCount() {
        int initialCount = Item.getInstancedItemCount();
        new Container("container", "description", null, null, true);
        assertEquals(initialCount + 1, Item.getInstancedItemCount());
    }

    @Test
    public void testContainerContainsItems() {
        Container container = new Container("container", "description", null, null, true);
        Item item1 = new Item("item1", "description", true, true, container);
        Item item2 = new Item("item2", "description", true, true, container);
        
        container.addItem(item1);
        container.addItem(item2);
        
        assertEquals(2, container.getItemCount());
    }
}
