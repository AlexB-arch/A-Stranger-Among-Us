package text_adventure.objects;

import text_adventure.Game;
import text_adventure.Subscriber;
import java.util.HashMap;

import text_adventure.resources.Directions;

public class Player implements Subscriber {
	private Room currentLocation;
	public Inventory inventory;

	// Party. Consists of the NPC name as the key and the NPC object as the value
	private HashMap<String, NPC> party;

	public Player() {
		inventory = new Inventory();
		party = new HashMap<String, NPC>();
	}

  public Room getCurrentLocation() {
	return currentLocation;
  }

  
  public void setCurrentLocation(Room currentLocation) {
    this.currentLocation = currentLocation;
  }

  public void move(Directions direction) {

		if (currentLocation.getExit(direction) == null) {
			Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT","You can't go that way."));
		} else {
			switch (direction) {
				case NORTH:
         			currentLocation = currentLocation.getExit(direction);
					break;
				case SOUTH:
          			currentLocation = currentLocation.getExit(direction);
					break;
				case EAST:
          			currentLocation = currentLocation.getExit(direction);
					break;
				case WEST:
          			currentLocation = currentLocation.getExit(direction);
					break;
				default:
					break;
			}

			Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT",currentLocation.displayRoom()));;
		}
	}

	@Override
	public void onMessage(Message message) {
		if(message.getHeader() == "PLAYER"){
			switch (message.getType()) {
				case "LOOK":
					Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT",getCurrentLocation().getDescription()));
					break;
				default:
					break;
			}
		}
	}

	public void addPartyMember(NPC npc) {
		party.put(npc.getName(), npc);
		npc.getLocation().removeNpc(npc);
	}

	public void removePartyMember(NPC npc) {
		party.remove(npc.getName());

		// Set the NPC's location to the player's location
		npc.setLocation(currentLocation);
	}

	public HashMap<String, NPC> getParty() {
		return party;
	}

	public void lootItem(String itemName) {
        Room currentRoom = getCurrentLocation();
        Inventory roomInventory = currentRoom.getInventory();
        Item item = roomInventory.getItemByName(itemName);
        
        if (item != null) {
            roomInventory.removeItem(itemName);
            inventory.addItem(item);
            Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You have looted: " + itemName));
        } else {
            Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "Item not found in the room."));
        }
    }

	// Method to give Items to NPC
	public void giveItemToNPC(String itemName, NPC npc) {
        Item item = inventory.getItemByName(itemName);
        if (item != null) {
            inventory.removeItem(itemName);
            npc.receiveItem(item);
            Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You have given " + itemName + " to " + npc.getName() + "."));
        } else {
            Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You do not have " + itemName + " in your inventory."));
        }
    }

	public Inventory getInventory() {
		return inventory;
	}

}
