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
    public void testContainerOpen() {
        Container container = new Container("container", "description", null, null, true);
        assertEquals("You open the container", container.open());
    }

    @Test
    public void testContainerOpenAlreadyOpen() {
        Container container = new Container("container", "description", null, null, true);
        container.open();
        assertEquals("The container is already open.", container.open());
    }

    @Test
    public void testContainerClose() {
        Container container = new Container("container", "description", null, null, true);
        container.open();
        assertEquals("You close the container", container.close());
    }

    @Test
    public void testContainerCloseAlreadyClosed() {
        Container container = new Container("container", "description", null, null, true);
        assertEquals("The container is already closed.", container.close());
    }

    @Test
    public void testContainerOpenNotOpenable() {
        Container container = new Container("container", "description", null, null, false);
        assertEquals("Can't open container", container.open());
    }

    @Test
    public void testContainerCloseNotOpenable() {
        Container container = new Container("container", "description", null, null, false);
        assertEquals("Can't close container", container.close());
    }

    @Test
    public void testContainerDescribe() {
        Container container = new Container("container", "description", null, null, true);
        assertEquals("description", container.describe());
    }

    @Test
    public void testContainerDescribeOpen() {
        Container container = new Container("container", "description", null, null, true);
        container.open();
        
        String expected = container.getName() + " is open. There is nothing in it.";

        assertEquals(expected, container.describe());
    }

    @Test
    public void testContainerDescribeClosed() {
        Container container = new Container("container", "description", null, null, true);
        
        String expected = container.getName() + " is closed.";

        assertEquals(expected, container.describe());
    }

    @Test
    public void testContainerDescribeNotOpenable() {
        Container container = new Container("container", "description", null, null, false);
        
        String expected = container.getName() + " can't be opened or closed.";

        assertEquals(expected, container.describe());
    }

    @Test
    public void testContainerDescribeOpenableNotOpen() {
        Container container = new Container("container", "description", null, null, true);
        
        String expected = container.getName() + " is closed.";

        assertEquals(expected, container.describe());
    }

    @Test
    public void testContainerDescribeOpenableOpenEmpty() {
        Container container = new Container("container", "description", null, null, true);
        container.open();
        
        String expected = container.getName() + " is open. There is nothing in it.";

        assertEquals(expected, container.describe());
    }

    /*@Test
    public void testContainerDescribeOpenableOpenWithItems() {
        Container container = new Container("container", "description", null, null, true);
        Item item1 = new Item("item1", "description", true, true, container);
        Item item2 = new Item("item2", "description", true, true, container);
        
        container.addItem(item1);
        container.addItem(item2);
        container.open();
        
        String expected = container.getName() + " is open. It contains: item1, item2";

        assertEquals(expected, container.describe());
    }*/
}