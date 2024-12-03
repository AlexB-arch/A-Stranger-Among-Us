package text_adventure.objects;

import text_adventure.resources.Directions;
import text_adventure.Subscriber;
import text_adventure.Game;

import java.util.ArrayList;
import java.util.List;

public class Room implements Subscriber{
    // Static variable to keep track of the number of rooms created
    private static int roomCount = 0;

    private String name, baseDescription, currentDescription;
    private Room north, south, west, east, up, down;
    public Inventory loot;
    public NPC npc;
    public List<String> interactables;
    public String key;

    // Constructor
    public Room(String name, String description, Inventory loot, List<String> interactables, String Key) {
        // Increment the room count each time a room is created
        roomCount++;

        setName(name);
        setBaseDescription(description);
        setKey(key);
        if (interactables != null) {
            this.interactables = interactables;
        } else {
            this.interactables = new ArrayList<>();
        }
        if (loot != null){
            this.loot = loot;
        }else {
            loot = new Inventory();
        }
        Game.globalEventBus.registerSubscriber("TRIGGER", this);
        Game.globalEventBus.registerSubscriber(getName(), this);
    }

    public Room(String name, String description, Inventory loot, List<String> interactables) {
        // Increment the room count each time a room is created
        roomCount++;

        setName(name);
        setBaseDescription(description);
        if (interactables != null) {
            this.interactables = interactables;
        } else {
            this.interactables = new ArrayList<>();
        }
        if (loot != null){
            this.loot = loot;
        }else {
            loot = new Inventory();
        }
        Game.globalEventBus.registerSubscriber("TRIGGER", this);
        Game.globalEventBus.registerSubscriber(getName(), this);
    }

    public Room(String name, String description, Inventory loot) {
        // Increment the room count each time a room is created
        roomCount++;

        setName(name);
        setBaseDescription(description);
        if (loot != null){
            this.loot = loot;
        }else {
            loot = new Inventory();
        }
        Game.globalEventBus.registerSubscriber("TRIGGER", this);
        Game.globalEventBus.registerSubscriber(getName(), this);
    }

    public Room(String name, String description) {
        // Increment the room count each time a room is created
        roomCount++;

        setName(name);
        setBaseDescription(description);
        Game.globalEventBus.registerSubscriber("TRIGGER", this);
        Game.globalEventBus.registerSubscriber(getName(), this);
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

	public String getBaseDescription() {
        return baseDescription;
    }

	public void setBaseDescription(String baseDescription) {
        this.baseDescription = baseDescription;
    }

	public String getCurrentDescription() {
		return currentDescription;
}

public void setCurrentDescription(String currentDescription) {
		this.currentDescription = currentDescription;
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
                return getNorth().getCurrentDescription();
            case SOUTH:
                return getSouth().getCurrentDescription();
            case WEST:
                return getWest().getCurrentDescription();
            case EAST:
                return getEast().getCurrentDescription();
            case UP:
                return getUp().getCurrentDescription();
            case DOWN:
                return getDown().getCurrentDescription();
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

    public static String[] MessageSplit(String message){
                    return message.split(",");
    }

    //I'm gonna run it down. This is the Great Trigger Method. -Brendan.
    @Override
    public void onMessage(Message message){
        String[] trigmessage = MessageSplit(message.getMessage()); //This splits up the message. I'm trying to stick with 2, with the first being a flag and the second being the new description.
        if(message.getHeader(). equals(getName())){
            if(message.getType() == "GEN"){
                    if(trigmessage[0] == "ON"){
                        setCurrentDescription(getBaseDescription() + trigmessage[1]);
                    }
                    else{
                        setCurrentDescription(getBaseDescription() + trigmessage[1]);
                    }
            }
        }
    }

    // When the player enters a room, the room description and available exits are displayed
    public String displayRoom(){
        String message = "";
        message += "You are in the " + getName() + ".\n";
        message += getCurrentDescription() + "\n";
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

    // Get the item in the room by name
    // public Item getCurrentRoomItem(String itemName){
    //     return loot.takeItem(itemName).orElse(null);
    // }

    public List<String> getInteractables() {
        return interactables;
    }

    public void addInteractable(String interactable) {
        interactables.add(interactable);
    }

    public void removeInteractable(String interactable) {
        interactables.remove(interactable);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
