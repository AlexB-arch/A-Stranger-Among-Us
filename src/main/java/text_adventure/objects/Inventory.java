package text_adventure.objects;

import java.util.ArrayList;
import java.util.Optional;


public class Inventory {
    private ArrayList<Item> inventoryItems = new ArrayList<>();

    public void addItem(Item item) {
        inventoryItems.add(item);
    }
    public int size(){
        return inventoryItems.size();
    }
    public boolean contains(Item item){
        return inventoryItems.contains(item);

    }
// better ways to do this I think
    public Optional<Item> takeItem(String Name){
        for (int i = 0; i < inventoryItems.size(); i++){
            if (inventoryItems.get(i).getName().equals(Name)){
                Item copyItem =  inventoryItems.get(i);
                inventoryItems.remove(i);
                return Optional.of(copyItem);
            }
        }
        return Optional.empty();
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
    