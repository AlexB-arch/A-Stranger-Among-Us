package text_adventure.objects;

import text_adventure.Game;
import text_adventure.Subscriber;
import java.util.HashMap;

import text_adventure.resources.Directions;
import text_adventure.interfaces.InventoryManagement;

public class Player implements Subscriber, InventoryManagement {
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
      		System.out.println("You can't go that way.");
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

      		System.out.println(currentLocation.displayRoom());
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

	@Override
	public void addItem(Item item) {
		inventory.addItem(item);
	}

	@Override
	public void removeItem(Item item) {
		inventory.removeItem(item);
	}

	@Override
	public void useItem(Item item) {
	}

	@Override
	public int size() {
		return inventory.size();
	}

	@Override
	public boolean contains(Item item) {
		return inventory.contains(item);
	}

	@Override
	public void printItems() {
		inventory.printItems();
	}
}
