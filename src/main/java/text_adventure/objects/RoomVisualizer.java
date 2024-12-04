package text_adventure.objects;

import java.io.FileWriter;
import java.io.IOException;


/**
 * Room visualizer that takes rooms from our world and builds a graphic visualization. -Austin
 * We will use this to build our visualization for our rooms. 
 * Currently it only supports the standard type of room. Will add support to add Elevators 
 * when elevators are in.
 */

public class RoomVisualizer {
    private StringBuilder dot;
    private FileWriter writer;

    public RoomVisualizer() {
        dot = new StringBuilder();
        dot.append("digraph GameMap {\n");
        dot.append("    node [shape=box, style=filled, fillcolor=lightgray];\n");
        dot.append("    edge [dir=forward];\n");
    }
    
    public void addRoom(Room room) {
        String roomId = room.getName().replaceAll("[\\s:;.-]+", "_");
        dot.append(String.format("    %s [label=\"%s\"];\n", 
                   roomId, room.getName()));
        
        addConnection(room, room.getNorth(), "north");
        addConnection(room, room.getSouth(), "south");
        addConnection(room, room.getEast(), "east");
        addConnection(room, room.getWest(), "west");
        addConnection(room, room.getUp(), "up");
        addConnection(room, room.getDown(), "down");

    }
    
    private void addConnection(Room from, Room to, String direction) {
        if (to != null) {
            String fromId = from.getName().replaceAll("[\\s:;.-]+", "_");
            String toId = to.getName().replaceAll("[\\s:;.-]+", "_");
            dot.append(String.format("    %s -> %s [label=\"%s\"];\n", 
                       fromId, toId, direction));
        }
    }
    
    public void saveToFile(String filename) throws IOException {
        dot.append("}");
        try {
        writer = new FileWriter(filename);
        writer.write(dot.toString());
        writer.close();
        } catch (IOException e){
            throw e;
        }
        
    }
}

    