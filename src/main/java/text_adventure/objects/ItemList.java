package text_adventure.objects;

import java.util.ArrayList;

/**
 * ItemList - Alex
 * Class for a list of items.
 * 
 * Used to get a list of items from a room or inventory.
 */

public class ItemList extends ArrayList<Item> {
    private String name;

    public ItemList() {
        super();
        name = "unnamed";
    }

    public ItemList(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item getItemByName(String name) {
        for (Item item : this) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public boolean inInventory(Item item) {
        return this.contains(item);
    }
}
