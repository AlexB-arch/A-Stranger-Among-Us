package text_adventure;

import java.util.HashMap;
import java.util.Map;

public class Room {
  private String name;
  private String description;
  private Map<String, Room> exits; // Exits are the rooms that are connected to this room

  // Constructor
  public Room(String name, String description) {
    this.name = name;
    this.description = description;
    this.exits = new HashMap<String, Room>();
  }

  // Getters
  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Map<String, Room> getExits() {
    return exits;
  }

  // Setters
  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setExits(String direction, Room room) {
    exits.put(direction.toLowerCase(), room);
  }

  // Starting Room?
}
