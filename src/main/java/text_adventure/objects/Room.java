package text_adventure.objects;

import text_adventure.resources.Directions;

public class Room {

    private String name, description;
    private int north, south, east, west, up, down;

    // Constructor with all directions
    public Room( String name, String description, int north, int south, int east, int west, int up, int down ) {
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
        this.up = up;
        this.down = down;
    }

    // Room description
    public String describeRoom(){
        String roomDescription;

        roomDescription = String.format("%s. %s.", roomName(), roomDescription());

        return roomDescription;
    }

    // Room name
    public String roomName(){
        return name;
    }

    public String roomDescription(){
        return description;
    }

    // Get the direction of the room
    public int getNorth() {
        return north;
    }

    public int getSouth() {
        return south;
    }

    public int getEast() {
        return east;
    }

    public int getWest() {
        return west;
    }

    public int getUp() {
        return up;
    }

    public int getDown() {
        return down;
    }

    // Set the direction of the room
    public void setNorth(int north) {
        this.north = north;
    }

    public void setSouth(int south) {
        this.south = south;
    }

    public void setEast(int east) {
        this.east = east;
    }

    public void setWest(int west) {
        this.west = west;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public void setDown(int down) {
        this.down = down;
    }
}