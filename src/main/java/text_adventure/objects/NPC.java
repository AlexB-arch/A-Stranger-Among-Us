package text_adventure.objects;

import java.util.HashMap;

import org.json.JSONObject;

import text_adventure.DialogueManager;

public class NPC {
    private String name;
    private Room location;

    // Dialogue map
    private HashMap<String, String> dialogueMap = new HashMap<String, String>();

    // States
    private String currentState = "default";
    private boolean isAlive = true;
    private boolean followPlayer = false;

    // Constructor
    public NPC(String name, Room location) {
        this.name = name;
        this.location = location;
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

    public String getDialogue(String state) {
        return dialogueMap.get(state);
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

    // Follow the player
    public void followPlayer() {
        followPlayer = true;
    }

    // Stop following the player
    public void stopFollowingPlayer() {
        followPlayer = false;
    }

    // Get the NPC's dialogue   
    public void loadDialogueMap() {
        JSONObject dialogueMap = DialogueManager.retrieveDialogue(DialogueManager.getFilePath());
        DialogueManager.loadDialogueMap(dialogueMap, this);

    }
}
