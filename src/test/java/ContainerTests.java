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

    @Test
    public void testContainerRemoveItem() {
        Container container = new Container("container", "description", null, null, true);
        Item item1 = new Item("item1", "description", true, true, container);
        Item item2 = new Item("item2", "description", true, true, container);
        
        container.addItem(item1);
        container.addItem(item2);
        
        container.removeItem(item1);
        
        assertEquals(1, container.getItemCount());
    }

    @Test
    public void testContainerGetItemByName() {
        Container container = new Container("container", "description", null, null, true);
        Item item1 = new Item("item1", "description", true, true, container);
        Item item2 = new Item("item2", "description", true, true, container);
        
        container.addItem(item1);
        container.addItem(item2);
        
        assertEquals(item1, container.getItemByName("item1"));
    }

    @Test
    public void testContainerGetItemByNameNull() {
        Container container = new Container("container", "description", null, null, true);
        Item item1 = new Item("item1", "description", true, true, container);
        Item item2 = new Item("item2", "description", true, true, container);
        
        container.addItem(item1);
        container.addItem(item2);
        
        assertNull(container.getItemByName("item3"));
    }

    @Test
    public void testEmptyContainerOpen() {
        Container container = new Container("container", "description", null, null, true);
        String expected = "You open the container and find nothing inside.";
        assertEquals(expected, container.open());
    }

    @Test
    public void testNonEmptyContainerOpen() {
        Container container = new Container("container", "description", null, null, true);
        Item item1 = new Item("item1", "description", true, true, container);
        Item item2 = new Item("item2", "description", true, true, container);
        
        container.addItem(item1);
        container.addItem(item2);
        
        String expected = "You open the container and find: item1, item2";
        assertEquals(expected, container.open());
    }

    @Test
    public void testContainerAlreadyOpen() {
        Container container = new Container("container", "description", null, null, true);
        container.open();
        String expected = "The container is already open.";
        assertEquals(expected, container.open());
    }

    @Test
    public void testNonOpenableContainerOpen() {
        Container container = new Container("container", "description", null, null, false);
        String expected = "Can't open container";
        assertEquals(expected, container.open());
    }

    @Test
    public void testEmptyContainerClose() {
        Container container = new Container("container", "description", null, null, true);
        container.open();
        String expected = "You close the container";
        assertEquals(expected, container.close());
    }

    @Test
    public void testNonEmptyContainerClose() {
        Container container = new Container("container", "description", null, null, true);
        Item item1 = new Item("item1", "description", true, true, container);
        Item item2 = new Item("item2", "description", true, true, container);
        
        container.addItem(item1);
        container.addItem(item2);
        
        container.open();
        String expected = "You close the container";
        assertEquals(expected, container.close());
    }

    @Test
    public void testContainerAlreadyClosed() {
        Container container = new Container("container", "description", null, null, true);
        String expected = "The container is already closed.";
        assertEquals(expected, container.close());
    }

    @Test
    public void testNonOpenableContainerClose() {
        Container container = new Container("container", "description", null, null, false);
        String expected = "Can't close container";
        assertEquals(expected, container.close());
    }

    @Test
    public void testContainerOpenable() {
        Container container = new Container("container", "description", null, null, true);
        assertEquals(true, container.isOpenable());
    }

    @Test
    public void testContainerNotOpenable() {
        Container container = new Container("container", "description", null, null, false);
        assertEquals(false, container.isOpenable());
    }
}