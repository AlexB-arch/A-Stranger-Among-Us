package text_adventure.objects;

import java.util.ArrayList;


public class Inventory {
    private ArrayList<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }
    public Item takeItem(String Name){
        for (int i = 0; i < items.size(); i++){
            if (item.getName().equals(Name)){
                items.remove(item)
            }
        }
    }
    

    public void printItems() {
        for (Item item : items) {
            System.out.println("Name: " + item.getName());
            System.out.println("Value: " + item.getWeight());
            System.out.println("Available Actions: " + item.getActions());
        }
    }
}
    