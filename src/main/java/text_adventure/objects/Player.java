package text_adventure.objects;

import text_adventure.Game;
import text_adventure.Subscriber;
import java.util.HashMap;
import java.util.List;

import text_adventure.resources.Directions;

/**
 * Player - Various contributors.
 * The player class is the main character in the game. It contains the player's current location, inventory, and party members.
 * The player can move, interact with objects, and use items.
 */

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
	} 
	else if (currentLocation.getExit(direction).getKey() == null) {
		currentLocation = currentLocation.getExit(direction);
	}
	else {
		if (inventory.inInventory(inventory.getItemByName(currentLocation.getExit(direction).getKey()))) {
			Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT","You unlock the door with the key and proceed."));
			currentLocation = currentLocation.getExit(direction);
		} else {
			Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT","The door is locked. You need the "+currentLocation.getExit(direction).getKey()+" to proceed."));
		}
	}
	Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT",currentLocation.displayRoom()));;
	Game.globalEventBus.publish(new TextMessage("TRIGGER","DIAL",getCurrentLocation().getName()));;
}


public void interact(String interactable) {
    List<String> interactables = currentLocation.getInteractables();
    if (interactables.contains(interactable.toLowerCase())) {
        switch (interactable.toLowerCase()) {
            case "generator":
                Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You press the generator button. The generator hums to life!"));
                Game.globalEventBus.publish(new TextMessage("TRIGGER", "GEN", "ON"));
                break;
            case "eject button":
				Game.globalEventBus.publish(new TextMessage("TRIGGER", "END", (String.valueOf(inventory.getItemCount("batteries"))  +"," +String.valueOf(inventory.getItemCount("Oxygen Tanks") ) )));
				break;
        }
    } else {
        Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "There's nothing like that to interact with here."));
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
			// Check if the item is inside a container in the player's inventory
			for (Item invItem : inventory.items.values()) {
				if (invItem instanceof Container) {
					Container container = (Container) invItem;
					Item containerItem = container.getItemByName(itemName);
					if (containerItem != null) {
						inventory.addItem(containerItem);
						container.removeItem(containerItem);
						Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You have taken the " + itemName + " from the " + container.getName() + "."));
						return;
					}
				}
			}
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

	public void openContainerIfInInventory(String containerName) {
        Item item = inventory.getItemByName(containerName);
        if (item instanceof Container) {
            Container container = (Container) item;
            Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", container.open()));
        } else {
            Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You don't have a " + containerName + " in your inventory."));
        }
    }

    public void putItemInContainer(String string, String string2) {
        Item item = inventory.getItemByName(string);
		Item container = inventory.getItemByName(string2);
		if (item != null && container != null) {
			if (container instanceof Container) {
				((Container) container).addItem(item);
				inventory.removeItem(item);
				Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You have put the " + string + " in the " + string2 + "."));
			} else {
				Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You can't put the " + string + " in the " + string2 + "."));
			}
		} else {
			Game.globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You don't have a " + string + " or " + string2 + "."));
		}
	}
	
	public int getItemCount(String itemName) {
        return inventory.getItemCount(itemName);
    }
}
