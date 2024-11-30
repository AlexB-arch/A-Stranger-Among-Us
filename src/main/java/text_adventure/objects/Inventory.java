package text_adventure.objects;
import java.util.ArrayList;

import text_adventure.interfaces.Item;


public class Inventory {
    private ArrayList<Item> inventoryItems = new ArrayList<>();

    public void addItem(Item item) {
        inventoryItems.add((Item) item);
    }

    public void removeItem(Item item) {
        inventoryItems.remove(item);
    }
    
    public int size(){
        return inventoryItems.size();
    }
    
    // method to check if an item is in the inventory
    public boolean inInventory(Item item){
        return inventoryItems.contains(item);
    }

    public String toString(){
        String inventoryPP = "------------------";
        for (Item item: inventoryItems){
            inventoryPP += "\n" + item.toString();  
        }
        return inventoryPP;
    }
    public void printItems() {
        System.out.print(toString());
    }
}
    