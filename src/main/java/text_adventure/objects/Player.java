package text_adventure.objects;


import text_adventure.resources.Directions;

public class Player implements Subscriber {
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
  // Inventory?
    // public void addItem(Item item){}
    // public void removeItem(Item item){}
    // public void showInventory(){}

@Override
public void onMessage(Message message) {
	if(message.getHeader() == "PLAYER"){
		switch (message.getType()) {
			case "PLAYER-ACTION":
				System.out.println(message.getMessage());
				break;
			default:
				break;
			}
  		}
	}

}
