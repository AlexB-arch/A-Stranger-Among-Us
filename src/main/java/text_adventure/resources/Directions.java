package text_adventure.resources;

import text_adventure.objects.Room;

public enum Directions {
    NORTH, 
    SOUTH, 
    EAST, 
    WEST,
    UP,
    DOWN;
    
    public static final Room NOEXIT = null;
}
