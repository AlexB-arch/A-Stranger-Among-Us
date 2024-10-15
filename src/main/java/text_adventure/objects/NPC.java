package text_adventure.objects;

public class NPC {
    private String name;
    private String dialogue;
    private Room location;

    // States
    private String currentState = "default";
    private boolean isAlive = true;

    // Constructor with name, dialogue, and location
    public NPC(String name, String dialogue, Room location) {
        this.name = name;
        this.dialogue = dialogue;
        this.location = location;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
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

    public String getDialogue() {
        return dialogue;
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
    
    public void interact() {
        System.out.println(dialogue);
    }
}
