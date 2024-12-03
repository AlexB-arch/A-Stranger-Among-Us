package text_adventure.objects;
import java.util.HashMap;


public class Inventory {
    public HashMap<String, Item> items;

    public Inventory() {
        items = new HashMap<String, Item>();
    }

    public void addItem(Item item) {
        items.put(item.getName().toLowerCase(), item);
    }

    public void removeItem(Item item) {
        items.remove(item.getName().toLowerCase());
    }

    public Item getItemByName(String itemName) {
        return items.get(itemName.toLowerCase());
    }
    
    public int size(){
        return items.size();
    }
    
    // method to check if an item is in the inventory
    public boolean inInventory(Item item){
        return items.containsValue(item);
    }
}
    