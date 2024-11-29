import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import text_adventure.objects.Inventory;
import text_adventure.objects.Room;
import text_adventure.objects.RoomVisualizer;

public class RoomVisualizerTest {
    private RoomVisualizer visualizer;
    private Room kitchen;
    private Room hall;
    private Room backRoom;
    private String testFilePath;
    
    @Before
    public void setUp() {
        visualizer = new RoomVisualizer();
        kitchen = new Room("Kitchen","A test Kitchen", new Inventory());
        hall = new Room("Hall", "A test Hall", new Inventory());
        backRoom = new Room("BackRoom", "A test backroom", new Inventory());

        testFilePath = "test_map.dot";
    }
    
    @After
    public void cleanup() {
        new File(testFilePath).delete();
    }
    
    @Test
    public void testFileCreation() throws IOException {
        visualizer.saveToFile(testFilePath);
        assertTrue(new File(testFilePath).exists());
    }
    
    @Test
    public void testBasicRoomConnection()throws IOException{
        kitchen.setExits(hall, null, backRoom, null);
        visualizer.addRoom(kitchen);
        System.out.println("REEE");
        try {
            visualizer.saveToFile(testFilePath);
        } catch(IOException e){
            System.err.println(e);
            assertTrue("IO Exception", e != null);
        }
        String content = Files.readString(Path.of(testFilePath));
        assertTrue(content.contains("Kitchen"));
        assertTrue(content.contains("Hall"));
        assertTrue(content.contains("north"));
        
    }
    
    @Test
    public void testMultipleConnections() throws IOException {
        Room dining = new Room("Dining", "Test Dining room", new Inventory());
        Room garden = new Room("Garden", "A test Garden",new Inventory());
        
        kitchen.setExits(hall, dining, garden, null);
        visualizer.addRoom(kitchen);
        
        visualizer.saveToFile(testFilePath);
        String content = Files.readString(Path.of(testFilePath));
        
        assertTrue(content.contains("-> Hall [label=\"north\"]"));
        assertTrue(content.contains("-> Dining [label=\"south\"]"));
        assertTrue(content.contains("-> Garden [label=\"east\"]"));
    }
    
    @Test(expected = IOException.class)
    public void testInvalidFilePath() throws IOException {
        visualizer.saveToFile("/invalid/path/map.dot");
    }
}