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

  // Movement
  public void move(Directions direction) {
    Room nextRoom = currentLocation.getExit(direction);
    if (nextRoom != null) {
        currentLocation = nextRoom;
        System.out.println("You moved to: " + currentLocation.getName());
    } else {
        System.out.println("You can't go that way.");
    }
}
  // Inventory?
    // public void addItem(Item item){}
    // public void removeItem(Item item){}
    // public void showInventory(){}

  // Combat?
}
