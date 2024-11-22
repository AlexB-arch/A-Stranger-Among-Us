package text_adventure.objects;

import java.util.ArrayList;

import text_adventure.interfaces.Item;

public class Key implements Item {
    private String name = "Key";
    private String description = "A small, rusty key.";
    private double value = 10.0;
    private double weight = 1.0;
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

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public ArrayList<String> getActions() {
        return actions;
    }
    @Override
    public String toString(){
        return "Name: " + getName() + 
        "\nDescription: " + getDescription() +
        "\nValue: $"+ getValue() +
        "\nWeight: " + getWeight() +
        "\nActions: " + getActions(); 
    }

    @Override
    public boolean performAction(String action) {
        if (action.equals("Use")) {
            System.out.println("You use the key to unlock a door.");
            return true;
        } else {
            return false; // or throw an exception, depending on your design
        }
    }
}
