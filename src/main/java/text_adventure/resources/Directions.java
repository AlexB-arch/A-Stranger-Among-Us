package text_adventure.resources;

import text_adventure.objects.Room;

/**
 * Directions - Alex
 * This is an enum class to handle the directions of the rooms.
 * 
 * This is part of the dictionary classes.
 */

public enum Directions {
    NORTH, 
    SOUTH, 
    EAST, 
    WEST,
    UP,
    DOWN;
    
    public static final Room NOEXIT = null;
}
