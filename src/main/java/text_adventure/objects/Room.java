package text_adventure.objects;

import text_adventure.interfaces.Item;
import text_adventure.resources.Directions;
import text_adventure.Subscriber;

public class Room implements java.io.Serializable, Subscriber{

    private String name, baseDescription, currentDescription;
    private Room north, south, west, east;
    public Inventory loot;
    public NPC npc;

    // Constructor
    public Room(String name, String description, Inventory loot) {
        setName(name);
        setDescription(description);
        if (loot != null){
            this.loot = loot;
        }else{
            loot = new Inventory();
        }
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

		public static String[] MessageSplit(String message){
					 return message.split(",");
		}

		//I'm gonna run it down. This is the Great Trigger Method. -Brendan.
		@Override
		public void onMessage(Message message){
			trigmessage = MessageSplit(message.getMessage()); //This splits up the message. I'm trying to stick with 2, with the first being a flag and the second being the new description.
			//Check to see if the message is for that room specifically.
			if(message.getChannel(). equals(getName())){
				if(message.getType() == "GEN"){
						if(trigmessage[0] == "ON"){
							setCurrentDescription(baseDescription() + trigmessage[1]);
						}
						else{
							setCurrentDescription(baseDescription() + trigmessage[1]);
						}
						break;
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

    // Add an item to the room
    public void addItem(Item item){
        loot.addItem(item);
    }

    // Remove an item from the room
    public void removeItem(Item item){
        loot.takeItem(item.getName());
    }

    // Get the item in the room by name
    public Item getCurrentRoomItem(String itemName){
        return loot.takeItem(itemName).orElse(null);
    }
}
