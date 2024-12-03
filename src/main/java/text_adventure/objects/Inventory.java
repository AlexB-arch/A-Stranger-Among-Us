package text_adventure.objects;
import java.util.HashMap;

public class Inventory {
    public HashMap<String, Item> items;
    private HashMap<String, Integer> itemCounts;

    public Inventory() {
        items = new HashMap<>();
        itemCounts = new HashMap<>();
    }

    public void addItem(Item item) {
        String itemName = item.getName().toLowerCase();
        items.put(itemName, item);
        itemCounts.put(itemName, itemCounts.getOrDefault(itemName, 0) + 1);
    }

    public void removeItem(Item item) {
        String itemName = item.getName().toLowerCase();
        items.remove(itemName);
        itemCounts.put(itemName, itemCounts.getOrDefault(itemName, 0) - 1);
        if (itemCounts.get(itemName) <= 0) {
            itemCounts.remove(itemName);
        }
    }

    public Item getItemByName(String itemName) {
        return items.get(itemName.toLowerCase());
    }

    public int size() {
        return items.size();
    }

    public boolean inInventory(Item item) {
        return items.containsValue(item);
    }

    public int getItemCount(String itemName) {
        return itemCounts.getOrDefault(itemName.toLowerCase(), 0);
    }
}