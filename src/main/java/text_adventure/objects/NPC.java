package text_adventure.objects;

import java.util.HashMap;

import text_adventure.Game;
import text_adventure.Subscriber;

public class NPC implements Subscriber {
    private String name;
    private Room location;
    private Inventory inventory;

    // Required item for puzzle
    private String requiredItem;
    private boolean puzzleSolved = false;

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

    public NPC(String name, Room location, String requiredItem) {
        this.name = name;
        this.location = location;
        this.inventory = new Inventory();
        this.requiredItem = requiredItem;
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

    public void setRequiredItemName(String itemName) {
        requiredItem = itemName;
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

    public void receiveItem(Item item) {
        if(item.getName().equalsIgnoreCase(requiredItem)) {
            inventory.addItem(item);
            puzzleSolved = true;
            Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", name + " received the " + item.getName() + ". Puzzle solved!"));
            // Notify Puzzle system if applicable
            // Puzzle.markAsSolved(this);
        } else {
            Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", name + " does not need the " + item.getName() + "."));
        }
    }

    public boolean isPuzzleSolved() {
        return puzzleSolved;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void onMessage(Message message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onMessage'");
    }
}