package text_adventure.objects;

public class NPC {
    private String name;
    private String dialogue;
    private Room location;

    // Basic empty constructor
    public NPC() {
        
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
    
    public void interact() {
        System.out.println(dialogue);
    }
}
