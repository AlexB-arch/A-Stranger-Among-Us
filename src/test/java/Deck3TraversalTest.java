import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import text_adventure.Game;
import text_adventure.objects.Item;
import text_adventure.objects.Player;
import text_adventure.objects.Room;
import text_adventure.resources.Directions;

public class Deck3TraversalTest {
    private Game game;
    private Player player;

    @Before
    public void setUp() {
        game = new Game();
        player = Game.player;
    }

    // Helper method to get to Fuel Storage TurboLift
    private void navigateToFuelStorageLift() {
        player.inventory.addItem(new Item("blue keycard"));
        game.runCommands("go south"); // To Barracks
        game.runCommands("go east"); // To Barracks Hallway
        game.runCommands("go east"); // To Mess Hall
        game.runCommands("go east"); // To Botany Hallway
        game.runCommands("go north"); // To TurboLift Deck 1
        game.runCommands("go down"); // To TurboLift Deck 2
        game.runCommands("go south"); // To Hallway Waste
        game.runCommands("go west"); // To Storage Bay
        game.runCommands("go south"); // To Hallway Storage
        game.runCommands("go south"); // To Thruster Bay
        game.runCommands("go south");
        game.runCommands("go down"); // To TurboLift Deck 3 Fuel Storage
    }

    // Helper method to get to Waste Ejection TurboLift
    private void navigateToWasteEjectionLift() {
        player.inventory.addItem(new Item("blue keycard"));
        game.runCommands("go south"); // To Barracks
        game.runCommands("go east"); // To Barracks Hallway
        game.runCommands("go east"); // To Mess Hall
        game.runCommands("go east"); // To Botany Hallway
        game.runCommands("go north"); // To TurboLift Deck 1
        game.runCommands("go down"); // To TurboLift Deck 2
        game.runCommands("go south"); // To Hallway Waste
        game.runCommands("go east"); // To Waste Control
        game.runCommands("go south");
        game.runCommands("go down"); // To TurboLift Deck 3 Waste Ejection
    }

    // Test starting position at Fuel Storage TurboLift
    @Test
    public void testFuelStorageLiftStartingLocation() {
        navigateToFuelStorageLift();
        Room currentRoom = player.getCurrentLocation();
        assertEquals("TurboLift Deck 3 Fuel Storage", currentRoom.getName());
    }

    // Test starting position at Waste Ejection TurboLift
    @Test
    public void testWasteEjectionLiftStartingLocation() {
        navigateToWasteEjectionLift();
        Room currentRoom = player.getCurrentLocation();
        assertEquals("TurboLift Deck 3 Waste Ejection", currentRoom.getName());
    }

    // Test path from Fuel Storage TurboLift to Fuel Storage
    @Test
    public void testPathToFuelStorage() {
        navigateToFuelStorageLift();
        game.runCommands("go west");
        game.runCommands("go south"); // To Fuel Storage
        Room currentRoom = player.getCurrentLocation();
        assertEquals("Fuel Storage Hallway", currentRoom.getName());
    }

    // Test path to Fuel Control
    @Test
    public void testPathToFuelControl() {
        navigateToFuelStorageLift();
        game.runCommands("go west");
        game.runCommands("go north"); // To Fuel Storage
        game.runCommands("go north"); // To Fuel Control
        Room currentRoom = player.getCurrentLocation();
        assertEquals("Fuel Control", currentRoom.getName());
    }



    // Test path from Waste Ejection TurboLift to Waste Ejection
    @Test
    public void testPathToWasteEjection() {
        navigateToWasteEjectionLift();
        player.inventory.addItem(new Item("blue keycard"));
        game.runCommands("go west");
        game.runCommands("go south"); // To Hallway Ejection
        game.runCommands("go north"); // To Waste Ejection
        Room currentRoom = player.getCurrentLocation();
        assertEquals("Waste Ejection", currentRoom.getName());
    }

    // Test path to Ejection Control
    @Test
    public void testPathToEjectionControl() {
        navigateToWasteEjectionLift();
        player.inventory.addItem(new Item("blue keycard"));
        game.runCommands("go west");
        game.runCommands("go south"); // To Hallway Ejection
        game.runCommands("go north"); // To Waste Ejection
        game.runCommands("go east"); // To Ejection Control
        Room currentRoom = player.getCurrentLocation();
        assertEquals("Ejection Control", currentRoom.getName());
    }


    // Test Fuel Storage exits
    @Test
    public void testFuelStorageExits() {
        navigateToFuelStorageLift();
        game.runCommands("go west");
        game.runCommands("go south"); // To Fuel Storage
        game.runCommands("go north");
        Room currentRoom = player.getCurrentLocation();
    
        
        assertEquals("Fuel Control", currentRoom.getExit(Directions.NORTH).getName());
        assertEquals("Fuel Storage Hallway", currentRoom.getExit(Directions.SOUTH).getName());
        assertNull("East exit should be null", currentRoom.getExit(Directions.EAST));
        assertNull("West exit should be null", currentRoom.getExit(Directions.WEST));
    }

    // Test Waste Ejection exits
    @Test
    public void testWasteEjectionExits() {
        navigateToWasteEjectionLift();
        game.runCommands("go west");
        game.runCommands("go south"); // To Hallway Ejection
        game.runCommands("go north"); // To Waste Ejection
        Room currentRoom = player.getCurrentLocation();
        
        assertNull("North exit should be null", currentRoom.getExit(Directions.NORTH));
        assertEquals("Waste Ejection Hallway", currentRoom.getExit(Directions.SOUTH).getName());
        assertEquals("Ejection Control", currentRoom.getExit(Directions.EAST).getName());
        assertNull("West exit should be null", currentRoom.getExit(Directions.WEST));
    }


    // Test return paths to TurboLifts
    @Test
    public void testReturnToFuelStorageLift() {
        navigateToFuelStorageLift();
        game.runCommands("go south"); // To Fuel Storage
        game.runCommands("go south"); // To Hallway Fuel
        game.runCommands("go east"); // Back to TurboLift
        Room currentRoom = player.getCurrentLocation();
        assertEquals("TurboLift Deck 3 Fuel Storage", currentRoom.getName());
    }

    @Test
    public void testReturnToWasteEjectionLift() {
        navigateToWasteEjectionLift();
        game.runCommands("go south"); // To Hallway Ejection
        game.runCommands("go east"); // Back to TurboLift
        Room currentRoom = player.getCurrentLocation();
        assertEquals("TurboLift Deck 3 Waste Ejection", currentRoom.getName());
    }
}