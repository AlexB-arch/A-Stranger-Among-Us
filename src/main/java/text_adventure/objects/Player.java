package text_adventure.objects;

import text_adventure.Game;
import text_adventure.Subscriber;
import java.util.HashMap;

import text_adventure.resources.Directions;

public class Player implements Subscriber {
	public Room currentLocation;
	public Inventory inventory;

	// Party. Consists of the NPC name as the key and the NPC object as the value
	public HashMap<String, NPC> party;

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
				case DOWN:
					currentLocation = currentLocation.getExit(direction);
					break;
				case UP:
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
					Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT",getCurrentLocation().displayRoom()));
					break;
				default:
					break;
			}
		}
	}

	public void addPartyMember(NPC npc) {
		party.put(npc.getName(), npc);
		if (npc.getLocation() != null) {
			npc.getLocation().removeNpc(npc);
		}
	}

	public void removePartyMember(NPC npc) {
		party.remove(npc.getName());

		// Set the NPC's location to the player's location
		npc.setLocation(currentLocation);
	}

	public HashMap<String, NPC> getParty() {
		return party;
	}

	public void takeItem(Item item) {
		inventory.addItem(item);
		Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You have taken the " + item.getName() + "."));
	}

	public void takeItemByName(String itemName) {
		Item item = currentLocation.getInventory().getItemByName(itemName);
		if (item != null) {
			inventory.addItem(item);
			currentLocation.getInventory().removeItem(item);
			Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You have taken the " + itemName + "."));
		} else {
			Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "There is no " + itemName + " here."));
		}
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void useItem(String itemName) {
        Item item = inventory.getItemByName(itemName);
		if (item != null) {
			boolean used = item.performAction("use");
			if (used) {
				Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You have used the " + itemName + "."));
			} else {
				Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You can't use the " + itemName + "."));
			}
		} else {
			Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You don't have a " + itemName + "."));
		}
    }

	public void giveItemToNPC(String string, NPC npc) {
		Item item = inventory.getItemByName(string);
		if (item != null) {
			inventory.removeItem(item);
			npc.getInventory().addItem(item);
			Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You have given the " + string + " to " + npc.getName() + "."));
		} else {
			Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You don't have a " + string + "."));
		}
	}

}
