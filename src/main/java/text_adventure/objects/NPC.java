package text_adventure.objects;

import java.util.HashMap;

import text_adventure.Game;
import text_adventure.Subscriber;
import text_adventure.interfaces.InventoryManagement;
import text_adventure.interfaces.Items;

public class NPC implements Subscriber, InventoryManagement {
    private String name;
    private Room location;
    private Inventory inventory;

    // Dialogue map
    private HashMap<String, String> dialogueMap = new HashMap<String, String>();

    // States
    private String currentState = "default";
    private boolean isAlive = true;

    //TODO: Add states as enum

    // Constructor
    public NPC(String name, Room location) {
        this.name = name;
        this.location = location;
        inventory = new Inventory();
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDialogue(String state, String dialogue) {
        this.dialogueMap.put(state, dialogue);
    }

    public void setLocation(Room location) {
        this.location = location;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    // Getters
    public String getName() {
        return name;
    }

    public String getDialogue(String currentState) {
        return dialogueMap.get(currentState);
    }

    public Room getLocation() {
        return location;
    }

    public String getCurrentState() {
        return currentState;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public String interact(String playerInput) {
        String response = "";
        // always lowercase
        switch (currentState.toLowerCase()) {
            case "greet":
                    // Print the greeting
                    response = dialogueMap.get("greeting");
                    // Change the state
                    currentState = "already_greeted";
                break;
            case "talk":
                    // Print the talking
                    if (!currentState.equals("already_talked")){
                        response = dialogueMap.get(currentState);
                        Game.showMessage(response);
                        currentState = "already_talked";
                    }
                    else{
                        response = dialogueMap.get(currentState);
                        Game.showMessage(response);
                    }                    
                break;
        }

        return response;
    }

    @Override
    public void onMessage(Message message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onMessage'");
    }

    // Inventory Management
    @Override
    public void addItem(Item item) {
        inventory.addItem(item);
    }

    @Override
    public void removeItem(Item item) {
        inventory.removeItem(item);
    }

    @Override
    public void useItem(Item item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'useItem'");
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    @Override
    public void printItems() {
        inventory.printItems();
    }

    @Override
    public boolean inInventory(Item item) {
        return inventory.inInventory(item);
    }
}