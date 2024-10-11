package text_adventure.objects;

import java.util.ArrayList;


public class LaserPistol implements Item {
    private String name = "Laser Pistol";
    private String description = "A high-powered laser pistol for close combat.";
    private double value = 500.0;
    private double weight = 5.0;
    private ArrayList<String> actions = new ArrayList<>();
    
    public LaserPistol() {
        actions.add("Fire");
        actions.add("Power Cycle");
        actions.add("Safely Store");
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
        if (action.equals("Fire")) {
            System.out.println("You fire the laser pistol at an enemy target.");
            return true;
        } else if (action.equals("Power Cycle")) {
            System.out.println("You power cycle the laser pistol to reload.");
            return true;
        } else if (action.equals("Safely Store")) {
            System.out.println("You safely store the laser pistol in a secure location.");
            return true;
        } else {
            return false; // or throw an exception, depending on your design
        }
    }
}
