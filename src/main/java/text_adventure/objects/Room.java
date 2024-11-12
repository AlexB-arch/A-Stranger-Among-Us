package text_adventure.objects;

import text_adventure.resources.Directions;

public class Room implements java.io.Serializable{

    private String name, description;
    private Room north, south, west, east;
    public Inventory roomInventory;
    public NPC npc;

    // Constructor
    public Room(String name, String description, Inventory inventory) {
        setName(name);
        setDescription(description);
        if (inventory != null){
            roomInventory = inventory;
        }else{
            roomInventory = new Inventory();
        }
    }

    public Room getNorth(){
        return north;
    }

    public void setNorth(Room north){
        this.north = north;
    }

    public Room getSouth(){
        return south;
    }

    public void setSouth(Room south){
        this.south = south;
    }

    public Room getWest(){
        return west;
    }

    public void setWest(Room west){
        this.west = west;
    }

    public Room getEast(){
        return east;
    }

    public void setEast(Room east){
        this.east = east;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    // Returns the name of the room in the specified direction
    public String getRoomName(Directions direction){
        switch(direction){
            case NORTH:
                return getNorth().getName();
            case SOUTH:
                return getSouth().getName();
            case WEST:
                return getWest().getName();
            case EAST:
                return getEast().getName();
            default:
                return "Invalid Direction";
        }
    }

    // Returns the description of the room in the specified direction
    public String getRoomDescription(Directions direction){
        switch(direction){
            case NORTH:
                return getNorth().getDescription();
            case SOUTH:
                return getSouth().getDescription();
            case WEST:
                return getWest().getDescription();
            case EAST:
                return getEast().getDescription();
            default:
                return "Invalid Direction";
        }
    }

    public void setExits(Room north, Room south, Room east, Room west) {
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }

    public Room getExit(Directions direction) {
        switch (direction) {
            case NORTH:
                return north;
            case SOUTH:
                return south;
            case EAST:
                return east;
            case WEST:
                return west;
            default:
                return null;
        }
    }

    // When the player enters a room, the room description and available exits are displayed
    public String displayRoom(){
        String message = "";
        message += "You are in the " + getName() + ".\n";
        message += getDescription() + "\n";
        message += "Exits: ";
        if (north != null) {
            message += "north ";
        }
        if (south != null) {
            message += "south ";
        }
        if (east != null) {
            message += "east ";
        }
        if (west != null) {
            message += "west ";
        }
        message += "\n";
        return message;
    }

    // Add an NPC to the room
    public void addNpc(NPC npc){
        this.npc = npc;
    }

    // Get the NPC in the room by name
    public NPC getCurrentRoomNpc(String npcName){
        // If the NPC is not in the room, return
        if (npc != null && npc.getName().equalsIgnoreCase(npcName)){
            return npc;
        }
        return null;
    }

    // Remove the NPC from the room once in the party
    public void removeNpc(NPC npc){
        this.npc = null;
    }
}
