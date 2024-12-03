import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import text_adventure.Game;
import text_adventure.objects.Player;
import text_adventure.objects.Room;
import text_adventure.resources.Directions;

public class Deck2TraversalTest {
    private Game game;
    private Player player;

    @Before
    public void setUp() {
        game = new Game();
        player = Game.player;
        // Navigate to Deck 2 TurboLift starting point
        game.runCommands("go south"); // To Barracks
        game.runCommands("go east"); // To Barracks Hallway
        game.runCommands("go west"); // To Mess Hall
        game.runCommands("go east"); // To Botany Hallway
        game.runCommands("go north"); // To TurboLift Deck 1
        game.runCommands("go down"); // To TurboLift Deck 2
    }

    // Test starting position
    @Test
    public void testStartingLocation() {
        Room currentRoom = player.getCurrentLocation();
        assertEquals("TurboLift Deck 2 Storage", currentRoom.getName());
    }

    // Test path to Storage Bay
    @Test
    public void testPathToStorageBay() {
        game.runCommands("go south"); // To Hallway Waste
        game.runCommands("go west"); // To Storage Bay
        Room currentRoom = player.getCurrentLocation();
        assertEquals("Storage Bay", currentRoom.getName());
    }

    // Test path to Communication Hub
    @Test
    public void testPathToCommunicationHub() {
        game.runCommands("go south"); // To Hallway Waste
        game.runCommands("go west"); // To Storage Bay
        game.runCommands("go north"); // To Hallway Communication
        game.runCommands("go west"); // To Communication Hub
        Room currentRoom = player.getCurrentLocation();
        assertEquals("Communication Hub", currentRoom.getName());
    }

    // Test path to Data Center
    @Test
    public void testPathToDataCenter() {
        game.runCommands("go south"); // To Hallway Waste
        game.runCommands("go west"); // To Storage Bay
        game.runCommands("go north"); // To Hallway Communication
        game.runCommands("go east"); // To Hallway Data
        game.runCommands("go east"); // To Data Center
        Room currentRoom = player.getCurrentLocation();
        assertEquals("Data Center", currentRoom.getName());
    }

    // Test path to Medical Bay and Closet
    @Test
    public void testPathToMedicalArea() {
        game.runCommands("go south"); // To Hallway Waste
        game.runCommands("go west"); // To Storage Bay
        game.runCommands("go south"); // To Hallway Storage
        game.runCommands("go east"); // To Hallway Medical
        game.runCommands("go east"); // To Medical Bay
        Room currentRoom = player.getCurrentLocation();
        assertEquals("Medical Bay", currentRoom.getName());
        
        game.runCommands("go east"); // To Medical Closet
        currentRoom = player.getCurrentLocation();
        assertEquals("Medical Closet", currentRoom.getName());
    }

    // Test path to Thruster Bay and Thrusters
    @Test
    public void testPathToThrusterArea() {
        game.runCommands("go south"); // To Hallway Waste
        game.runCommands("go west"); // To Storage Bay
        game.runCommands("go south"); // To Hallway Storage
        game.runCommands("go south"); // To Thruster Bay
        Room currentRoom = player.getCurrentLocation();
        assertEquals("Thruster Bay", currentRoom.getName());
        
        game.runCommands("go west"); // To Thruster 1
        currentRoom = player.getCurrentLocation();
        assertEquals("Thruster 1", currentRoom.getName());
        
        game.runCommands("go east"); // Back to Thruster Bay
        game.runCommands("go east"); // To Thruster 2
        currentRoom = player.getCurrentLocation();
        assertEquals("Thruster 2", currentRoom.getName());
    }

    // Test path to Waste Control
    @Test
    public void testPathToWasteControl() {
        game.runCommands("go south"); // To Hallway Waste
        game.runCommands("go east"); // To Waste Control
        Room currentRoom = player.getCurrentLocation();
        assertEquals("Waste Control", currentRoom.getName());
        
        game.runCommands("go north"); // To Waste Control Room
        currentRoom = player.getCurrentLocation();
        assertEquals("Waste Control Room", currentRoom.getName());
    }

    // Test path to Quarantine Room
    @Test
    public void testPathToQuarantine() {
        game.runCommands("go south"); // To Hallway Waste
        game.runCommands("go west"); // To Storage Bay
        game.runCommands("go south"); // To Hallway Storage
        game.runCommands("go west"); // To Hallway 
        game.runCommands("go west"); // To Quarantine Hallway

        Room currentRoom = player.getCurrentLocation();
        assertEquals("Quarantine Room", currentRoom.getName());
    }

    // Test path to Docking Bay
    @Test
    public void testPathToDockingBay() {
        game.runCommands("go south"); // To Hallway Waste
        game.runCommands("go west"); // To Storage Bay
        game.runCommands("go west"); // To Hallway Docks
        game.runCommands("go east"); // To Docking Bay
        Room currentRoom = player.getCurrentLocation();
        assertEquals("Dock Bay", currentRoom.getName());
    }

    // Test Storage Bay exits
    @Test
    public void testStorageBayExits() {
        game.runCommands("go south"); // To Hallway Waste
        game.runCommands("go west"); // To Storage Bay
        Room currentRoom = player.getCurrentLocation();
        
        assertEquals("Communication Hub Hallway", currentRoom.getExit(Directions.NORTH).getName());
        assertEquals("Southern Storage Hallway", currentRoom.getExit(Directions.SOUTH).getName());
        assertEquals("Waste Control Hallway", currentRoom.getExit(Directions.EAST).getName());
        assertEquals("Dock Hallway", currentRoom.getExit(Directions.WEST).getName());
    }

    // Test Medical Bay exits
    @Test
    public void testMedicalBayExits() {
        game.runCommands("go south"); // To Hallway Waste
        game.runCommands("go west"); // To Storage Bay
        game.runCommands("go south"); // To Hallway Storage
        game.runCommands("go east"); // To Hallway Medical
        game.runCommands("go east"); // To Medical Bay
        Room currentRoom = player.getCurrentLocation();
        
        assertNull("North exit should be null", currentRoom.getExit(Directions.NORTH));
        assertNull("South exit should be null", currentRoom.getExit(Directions.SOUTH));
        assertEquals("Medical Closet", currentRoom.getExit(Directions.EAST).getName());
        assertEquals("Medical Bay Hallway", currentRoom.getExit(Directions.WEST).getName());
    }

    // Test return path to TurboLift
    @Test
    public void testReturnToTurboLift() {
        game.runCommands("go south"); // To Hallway Waste
        game.runCommands("go west"); // To Storage Bay
        game.runCommands("go east"); // Back to Hallway Waste
        game.runCommands("go north"); // Back to TurboLift
        Room currentRoom = player.getCurrentLocation();
        assertEquals("TurboLift Deck 2 Storage", currentRoom.getName());
    }
}