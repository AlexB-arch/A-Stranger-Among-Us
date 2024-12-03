package text_adventure.objects;

import text_adventure.resources.Directions;

public class Room implements java.io.Serializable{

    // Static variable to keep track of the number of rooms created
    private static int roomCount = 0;

    private String name, description;
    private Room north, south, west, east, up, down;
    public Inventory loot;
    public NPC npc;

    // Constructor
    public Room(String name, String description, Inventory loot) {
        // Increment the room count each time a room is created
        roomCount++;

        setName(name);
        setDescription(description);
        if (loot != null){
            this.loot = loot;
        }else{
            loot = new Inventory();
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

    public void setUp(Room up){
        this.up = up;
    }
    public Room getUp(){
        return up;
    }

    public void setDown(Room down){
        this.down = down;
    }
    public Room getDown(){
        return down;
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
            case UP:
                return getUp().getName();
            case DOWN: 
                return getDown().getName();
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
            case UP:
                return getUp().getDescription();
            case DOWN:
                return getDown().getDescription();
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
    public void setExits(Room north, Room south, Room east, Room west,Room up, Room down){
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
        this.up = up;
        this.down = down;
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
            case UP:
                return up;
            case DOWN:
                return down;
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
        if (up != null){
            message += "up ";
        }
        if (down != null){
            message += "down ";
        }
        message += "\n";
        return message;
    }

    // Add an NPC to the room
    public void addNpc(NPC npc){
        this.npc = npc;
    }

    // Get the NPC in the room by name
    public boolean getCurrentRoomNpc(String npcName){
        // If the NPC is in the room, return true
        if (npc != null && npc.getName().equalsIgnoreCase(npcName)){
            return true;
        }
        return false;
    }

    public NPC getNPC(){
        return npc;
    }

    // Remove the NPC from the room once in the party
    public void removeNpc(NPC npc){
        this.npc = null;
    }

    public Inventory getInventory() {
        return loot;
    }

    // Get the number of rooms created
    public static int getRoomCount(){
        return roomCount;
    }

    // Get the number of exits in the room
    public int getExitCount(){
        int count = 0;
        if (north != null) {
            count++;
        }
        if (south != null) {
            count++;
        }
        if (east != null) {
            count++;
        }
        if (west != null) {
            count++;
        }
        if (up != null){
            count++;
        }
        if (down != null){
            count++;
        }
        return count;
    }
}
