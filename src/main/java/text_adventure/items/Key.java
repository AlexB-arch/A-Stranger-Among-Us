package text_adventure.items;

import java.util.ArrayList;

import text_adventure.interfaces.Item;

public class Key implements Item {
    private String name = "Key";
    private String description = "A small, rusty key.";
    private ArrayList<String> actions = new ArrayList<>();
    
    public Key() {
        actions.add("Use");
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public ArrayList<String> getActions() {
        return actions;
    }
    @Override
    public String toString(){
        return "Name: " + getName() + 
        "\nDescription: " + getDescription() +
        "\nActions: " + getActions(); 
    }

    public boolean performAction(String action) {
        if (action.equals("Use")) {
            System.out.println("You use the key to unlock a door.");
            return true;
        } else {
            return false; // or throw an exception, depending on your design
        }
    }
}
