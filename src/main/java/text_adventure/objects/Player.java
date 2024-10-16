package text_adventure.objects;

import text_adventure.resources.Directions;

public class Player {
  private Room currentLocation;
  public Inventory playerInventory;
  // what kind of attributes are needed for the player?

  public Player() {
    // Initialize the player
    // Do we want to have names for the player?
    // Player starts with a description, location, and inventory
    Inventory playerInventory = new Inventory(); 

  }

  // public void openInventory(){
  //   return playerInventory.strinFormatedItems();
  // }

  // getters and setters, if any
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

	// Interact with NPCs based on name
	public void interactWithNPC(String npcName) {
		// Find the NPC in the current room
		NPC npc = currentLocation.getCurrentRoomNpc(npcName);

		// If the NPC is not in the room, return
		if (npc == null) {
			System.out.println("There is no one here by that name.");
			return;
		}

		// Get the NPC's dialogue
		String dialogue = npc.getDialogue(npc.getCurrentState());

		// Print the NPC's dialogue
		System.out.println(dialogue);
	}
  // Inventory?
    // public void addItem(Item item){}
    // public void removeItem(Item item){}
    // public void showInventory(){}

  // Combat?
}
