package text_adventure.objects;

import java.util.ArrayList;

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
}
