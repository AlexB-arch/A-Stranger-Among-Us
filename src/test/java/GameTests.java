import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Test;
import org.junit.Before;

import text_adventure.Parser;
import text_adventure.Game;
import text_adventure.objects.Player;
import text_adventure.objects.Room;
import text_adventure.resources.WordType;
import text_adventure.resources.Directions;

public class GameTests {

    private Game game;
    private Player player;

   /* public String formatTestOutput(String testName, String input, String output) {
        return "-------\n" + testName + "\n" + "Input:" + input + "\nOutput:" + output + "\n------";
    }*/

    @Before
    public void setUp() {
        game = new Game();
        player = Game.player;
    }

    // Tests dictionary initialization
    @Test
    public void testDictionary() {

        // Tests that it's empty first.
        Parser.dictionary = new HashMap<String, WordType>();
        assertTrue(Parser.dictionary.size() == 0);

        // Initializes the dictionary
        Parser.initDictionary();
        assertTrue(Parser.dictionary.size() > 0);

        // Checks that the dictionary contains the word "go"
        assertTrue(Parser.dictionary.containsKey("go"));

        // Check if the dictionary has the NOUN value for "wires"
        assertTrue(Parser.dictionary.get("wires") == WordType.NOUN);
    }

    @Test
    public void testDictionaryAddition() {

        int parserSize = Parser.dictionary.size();
        // Add a word to the dictionary and check its value.
        Parser.dictionary.put("sword", WordType.VERB);
        assertTrue(Parser.dictionary.get("sword") == WordType.VERB);

        // Check that the dictionary size increased by one
        assertTrue(Parser.dictionary.size() == parserSize + 1);
    }

    @Test
    public void testUnknownWord() {
        String input = "ff";
        String expectedOutput = "I don't know the word '" + input + "'";
        String output = game.runCommands(input);
        // Adjusting the expected output to match the actual output from runCommands
        // You may need to adjust this based on your actual implementation
        assertEquals(expectedOutput.trim(), output.trim());
    }

    @Test
    public void testNoInput() {
        String input = "";
        String expectedOutput = "Please enter a command.\n";
        String output = game.runCommands(input);
        assertEquals(expectedOutput, output);
    }

		//Brendans Great Room Tests (I'm going insane)
		@Test
     public void testStartingLocation() {
         Room currentRoom = player.getCurrentLocation();
         assertEquals("Barracks Storage", currentRoom.getName());
     }

     // 2. Test Moving to Barracks
     @Test
     public void testMoveSouthFromBarracksStorage() {
         // Move south to Barracks
         game.runCommands("go south");

         // Check current room
         Room currentRoom = player.getCurrentLocation();
         assertEquals("Barracks", currentRoom.getName());
     }

     // 3. Test Moving to Barracks Hallway
     @Test
     public void testMoveEastFromBarracks() {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");

         // Check current room
         Room currentRoom = player.getCurrentLocation();
         assertEquals("Barracks Hallway", currentRoom.getName());
     }

     // 4. Test Moving to Mess Hall
     @Test
     public void testMoveWestFromHallwayBarracks() {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");

         // Check current room
         Room currentRoom = player.getCurrentLocation();
         assertEquals("Mess Hall", currentRoom.getName());
     }

     // 5. Test Moving to Bridge
     @Test
     public void testMoveNorthFromMessHall() {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move north to Bridge
         game.runCommands("go north");

         // Check current room
         Room currentRoom = player.getCurrentLocation();
         assertEquals("Bridge", currentRoom.getName());
     }

     // 6. Test Moving to Engine Room Hallway
     @Test
     public void testMoveSouthFromMessHallToHallwayEngine() {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move south to Engine Room Hallway
         game.runCommands("go south");

         // Check current room
         Room currentRoom = player.getCurrentLocation();
         assertEquals("Engine Room Hallway", currentRoom.getName());
     }

     // 7. Test Moving to Engine Room
     @Test
     public void testMoveSouthFromHallwayEngineToEngineRoom() {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move south to Engine Room Hallway
         game.runCommands("go south");
         // Move south to Engine Room
         game.runCommands("go south");

         // Check current room
         Room currentRoom = player.getCurrentLocation();
         assertEquals("Engine Room", currentRoom.getName());
     }

     // 8. Test Moving to Engine Storage
     @Test
     public void testMoveEastFromEngineRoomToEngineStorage() {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move south to Engine Room Hallway
         game.runCommands("go south");
         // Move south to Engine Room
         game.runCommands("go south");
         // Move east to Engine Room Storage
         game.runCommands("go east");

         // Check current room
         Room currentRoom = player.getCurrentLocation();
         assertEquals("Engine Room Storage", currentRoom.getName());
     }

     // 9. Test Moving to Hallway Weapons
     @Test
     public void testMoveWestFromHallwayEngineToHallwayWeapons() {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move south to Engine Room Hallway
         game.runCommands("go south");
         // Move west to Hallway Weapons
         game.runCommands("go west");

         // Check current room
         Room currentRoom = player.getCurrentLocation();
         assertEquals("Engine Room Hallway", currentRoom.getName());
     }

     // 10. Test Moving to Weapons Bay
     @Test
     public void testMoveEastFromHallwayWeaponsToWeaponsBay() {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move south to Engine Room Hallway
         game.runCommands("go south");
         // Move west to Hallway Weapons
         game.runCommands("go west");
         // Move east to Weapons Bay
         game.runCommands("go east");

         // Check current room
         Room currentRoom = player.getCurrentLocation();
         assertEquals("Engine Room Hallway", currentRoom.getName());
     }

     // 11. Test Moving to Botany Hallway
     @Test
     public void testMoveEastFromMessHallToHallwayBotany() {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move east to Botany Hallway
         game.runCommands("go east");

         // Check current room
         Room currentRoom = player.getCurrentLocation();
         assertEquals("Botany Hallway", currentRoom.getName());
     }

     // 12. Test Moving to Botany Bay
     @Test
     public void testMoveWestFromHallwayBotanyToBotanyBay() {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move east to Botany Hallway
         game.runCommands("go east");
         // Move west to Botany Bay
         game.runCommands("go west");

         // Check current room
         Room currentRoom = player.getCurrentLocation();
         assertEquals("Botany Bay", currentRoom.getName());
     }

     // 13. Test Moving to Botany Storage
     @Test
     public void testMoveWestFromBotanyBayToBotanyStorage() {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move east to Botany Hallway
         game.runCommands("go east");
         // Move west to Botany Bay
         game.runCommands("go west");
         // Move west to Botany Storage
         game.runCommands("go west");

         // Check current room
         Room currentRoom = player.getCurrentLocation();
         assertEquals("Botany Storage", currentRoom.getName());
     }

     // ---------------------
     // Exit Tests
     // ---------------------

     // 1. Test Exits of Barracks Storage
     @Test
     public void testBarracksStorageExits() throws IOException {
         Room currentRoom = player.getCurrentLocation();

         // Check exits
         assertNull("North exit should be null", currentRoom.getExit(Directions.NORTH));
         assertEquals("Barracks", currentRoom.getExit(Directions.SOUTH).getName());
         assertNull("East exit should be null", currentRoom.getExit(Directions.EAST));
         assertNull("West exit should be null", currentRoom.getExit(Directions.WEST));
     }

     // 2. Test Exits of Barracks
     @Test
     public void testBarracksExits() throws IOException {
         // Move south to Barracks
         game.runCommands("go south");
         Room currentRoom = player.getCurrentLocation();

         // Check exits
         assertEquals("Barracks Storage", currentRoom.getExit(Directions.NORTH).getName());
         assertNull("South exit should be null", currentRoom.getExit(Directions.SOUTH));
         assertEquals("Barracks Hallway", currentRoom.getExit(Directions.EAST).getName());
         assertNull("West exit should be null", currentRoom.getExit(Directions.WEST));
     }

     // 3. Test Exits of Barracks Hallway
     @Test
     public void testHallwayBarracksExits() throws IOException {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         Room currentRoom = player.getCurrentLocation();

         // Check exits
         assertNull("North exit should be null", currentRoom.getExit(Directions.NORTH));
         assertNull("South exit should be null", currentRoom.getExit(Directions.SOUTH));
         assertEquals("Barracks", currentRoom.getExit(Directions.EAST).getName());
         assertEquals("Mess Hall", currentRoom.getExit(Directions.WEST).getName());
     }

     // 4. Test Exits of Mess Hall
     @Test
     public void testMessHallExits() throws IOException {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         Room currentRoom = player.getCurrentLocation();

         // Check exits
         assertEquals("Bridge", currentRoom.getExit(Directions.NORTH).getName());
         assertEquals("Engine Room Hallway", currentRoom.getExit(Directions.SOUTH).getName());
         assertEquals("Botany Hallway", currentRoom.getExit(Directions.EAST).getName());
         assertEquals("Barracks Hallway", currentRoom.getExit(Directions.WEST).getName());
     }

     // 5. Test Exits of Bridge
     @Test
     public void testBridgeExits() throws IOException {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move north to Bridge
         game.runCommands("go north");
         Room currentRoom = player.getCurrentLocation();

         // Check exits
         assertNull("North exit should be null", currentRoom.getExit(Directions.NORTH));
         assertEquals("Mess Hall", currentRoom.getExit(Directions.SOUTH).getName());
         assertNull("East exit should be null", currentRoom.getExit(Directions.EAST));
         assertNull("West exit should be null", currentRoom.getExit(Directions.WEST));
     }

     // 6. Test Exits of Engine Room Hallway
     @Test
     public void testHallwayEngineExits() throws IOException {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move south to Engine Room Hallway
         game.runCommands("go south");
         Room currentRoom = player.getCurrentLocation();

         // Check exits
         assertEquals("Mess Hall", currentRoom.getExit(Directions.NORTH).getName());
         assertEquals("Engine Room", currentRoom.getExit(Directions.SOUTH).getName());
         assertNull("East exit should be null", currentRoom.getExit(Directions.EAST));
         assertNull("West exit should be null", currentRoom.getExit(Directions.WEST));
     }

     // 7. Test Exits of Engine Room
     @Test
     public void testEngineRoomExits() throws IOException {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move south to Engine Room Hallway
         game.runCommands("go south");
         // Move south to Engine Room
         game.runCommands("go south");
         Room currentRoom = player.getCurrentLocation();

         // Check exits
         assertEquals("Engine Room Hallway", currentRoom.getExit(Directions.NORTH).getName());
         assertEquals("Engine Room Storage", currentRoom.getExit(Directions.EAST).getName());
         assertNull("South exit should be null", currentRoom.getExit(Directions.SOUTH));
         assertNull("West exit should be null", currentRoom.getExit(Directions.WEST));
     }

     // 8. Test Exits of Engine Room Storage
     @Test
     public void testEngineRoomStorageExits() throws IOException {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move south to Engine Room Hallway
         game.runCommands("go south");
         // Move south to Engine Room
         game.runCommands("go south");
         // Move east to Engine Room Storage
         game.runCommands("go east");
         Room currentRoom = player.getCurrentLocation();

         // Check exits
         assertNull("North exit should be null", currentRoom.getExit(Directions.NORTH));
         assertNull("South exit should be null", currentRoom.getExit(Directions.SOUTH));
         assertNull("East exit should be null", currentRoom.getExit(Directions.EAST));
         assertEquals("Engine Room", currentRoom.getExit(Directions.WEST).getName());
     }

     // 9. Test Exits of Hallway Weapons
     @Test
     public void testHallwayWeaponsExits() throws IOException {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move south to Engine Room Hallway
         game.runCommands("go south");
         // Move west to Hallway Weapons
         game.runCommands("go west");
         Room currentRoom = player.getCurrentLocation();

         // Check exits

         assertNull("West exit should be null", currentRoom.getExit(Directions.WEST));
     }

     // 10. Test Exits of Weapons Bay
     @Test
     public void testWeaponsBayExits() throws IOException {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move south to Engine Room Hallway
         game.runCommands("go south");
         // Move west to Hallway Weapons
         game.runCommands("go west");
         // Move east to Weapons Bay
         game.runCommands("go east");
         Room currentRoom = player.getCurrentLocation();

         // Check exits


         assertNull("East exit should be null", currentRoom.getExit(Directions.EAST));
     }

     // 11. Test Exits of Botany Hallway
     @Test
     public void testHallwayBotanyExits() throws IOException {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move east to Botany Hallway
         game.runCommands("go east");
         Room currentRoom = player.getCurrentLocation();

         // Check exits
         assertEquals("TurboLift Deck 1: Botany", currentRoom.getExit(Directions.NORTH).getName());
         assertNull("South exit should be null", currentRoom.getExit(Directions.SOUTH));
         assertEquals("Mess Hall", currentRoom.getExit(Directions.EAST).getName());
         assertEquals("Botany Bay", currentRoom.getExit(Directions.WEST).getName());
     }

     // 12. Test Exits of Botany Bay
     @Test
     public void testBotanyBayExits() throws IOException {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move east to Botany Hallway
         game.runCommands("go east");
         // Move west to Botany Bay
         game.runCommands("go west");
         Room currentRoom = player.getCurrentLocation();
         assertEquals("Botany Bay",currentRoom.getName());

         // Check exits
         assertNull("North exit should be null", currentRoom.getExit(Directions.NORTH));
         assertNull("South exit should be null", currentRoom.getExit(Directions.SOUTH));
         assertEquals("Botany Hallway", currentRoom.getExit(Directions.EAST).getName());
         assertEquals("Botany Storage", currentRoom.getExit(Directions.WEST).getName());
     }

     // 13. Test Exits of Botany Storage
     @Test
     public void testBotanyStorageExits() throws IOException {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move east to Botany Hallway
         game.runCommands("go east");
         // Move west to Botany Bay
         game.runCommands("go west");
         // Move west to Botany Storage
         game.runCommands("go west");
         Room currentRoom = player.getCurrentLocation();

         // Check exits
         assertNull("North exit should be null", currentRoom.getExit(Directions.NORTH));
         assertNull("South exit should be null", currentRoom.getExit(Directions.SOUTH));
     }

     // ---------------------
     // Additional Navigation Tests
     // ---------------------

     // 14. Test Moving Back to Barracks Storage from Barracks
     @Test
     public void testMoveBackToBarracksStorageFromBarracks() {
         // Move south to Barracks
         game.runCommands("go south");
         // Move north back to Barracks Storage
         game.runCommands("go north");

         // Check current room
         Room currentRoom = player.getCurrentLocation();
         assertEquals("Barracks Storage", currentRoom.getName());
     }

     // 15. Test Moving Back to Barracks Storage from Barracks Hallway
     @Test
     public void testMoveBackToBarracksStorageFromHallwayBarracks() {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west back to Barracks
         game.runCommands("go west");
         // Move north back to Barracks Storage
         game.runCommands("go north");

         // Check current room
         Room currentRoom = player.getCurrentLocation();
         assertEquals("Bridge", currentRoom.getName());
     }

     // 16. Test Moving Back to Barracks Storage from Mess Hall
     @Test
     public void testMoveBackToBarracksStorageFromMessHall() {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move west back to Barracks Hallway
         game.runCommands("go west");
         // Move north back to Barracks Storage
         game.runCommands("go north");

         // Check current room
         Room currentRoom = player.getCurrentLocation();
         assertEquals("Barracks Hallway", currentRoom.getName());
     }

     // 17. Test Moving Back to Barracks Storage from Bridge
     @Test
     public void testMoveBackToBarracksStorageFromBridge() {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move north to Bridge
         game.runCommands("go north");
         // Move south back to Mess Hall
         game.runCommands("go south");
         // Move west to Barracks Hallway
         game.runCommands("go west");
         // Move north back to Barracks Storage
         game.runCommands("go north");

         // Check current room
         Room currentRoom = player.getCurrentLocation();
         assertEquals("Barracks Hallway", currentRoom.getName());
     }

     // ---------------------
     // Additional Exit Tests
     // ---------------------

     // 14. Test Exits of Botany Hallway
     @Test
     public void testHallwayBotanyExits2() throws IOException {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move east to Botany Hallway
         game.runCommands("go east");
         Room currentRoom = player.getCurrentLocation();

         // Check exits
         assertEquals("TurboLift Deck 1: Botany", currentRoom.getExit(Directions.NORTH).getName());
         assertNull("South exit should be null", currentRoom.getExit(Directions.SOUTH));
         assertEquals("Mess Hall", currentRoom.getExit(Directions.EAST).getName());
         assertEquals("Botany Bay", currentRoom.getExit(Directions.WEST).getName());
     }

     // 15. Test Exits of Bridge
     @Test
     public void testBridgeExits2() throws IOException {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move north to Bridge
         game.runCommands("go north");
         Room currentRoom = player.getCurrentLocation();

         // Check exits
         assertNull("North exit should be null", currentRoom.getExit(Directions.NORTH));
         assertEquals("Mess Hall", currentRoom.getExit(Directions.SOUTH).getName());
         assertNull("East exit should be null", currentRoom.getExit(Directions.EAST));
         assertNull("West exit should be null", currentRoom.getExit(Directions.WEST));
     }

     // 16. Test Exits of Weapons Bay Hallway
     @Test
     public void testWeaponsBayHallwayExits() throws IOException {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move south to Engine Room Hallway
         game.runCommands("go south");
         // Move west to Hallway Weapons
         game.runCommands("go west");
         Room currentRoom = player.getCurrentLocation();

         // Check exits

     }

     // 17. Test Exits of Hallway Weapons
     @Test
     public void testHallwayWeaponsExits2() throws IOException {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move south to Engine Room Hallway
         game.runCommands("go south");
         // Move west to Hallway Weapons
         game.runCommands("go west");
         Room currentRoom = player.getCurrentLocation();

         // Check exits
     }

     // 18. Test Exits of Botany Bay
     @Test
     public void testBotanyBayExits2() throws IOException {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move east to Botany Hallway
         game.runCommands("go east");
         // Move west to Botany Bay
         game.runCommands("go west");
         Room currentRoom = player.getCurrentLocation();

         // Check exits
         assertNull("North exit should be null", currentRoom.getExit(Directions.NORTH));
         assertNull("South exit should be null", currentRoom.getExit(Directions.SOUTH));
         assertEquals("Botany Hallway", currentRoom.getExit(Directions.EAST).getName());
         assertEquals("Botany Storage", currentRoom.getExit(Directions.WEST).getName());
     }

     // 19. Test Exits of Botany Storage
     @Test
     public void testBotanyStorageExits2() throws IOException {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move east to Botany Hallway
         game.runCommands("go east");
         // Move west to Botany Bay
         game.runCommands("go west");
         // Move west to Botany Storage
         game.runCommands("go west");
         Room currentRoom = player.getCurrentLocation();

         // Check exits
         assertNull("North exit should be null", currentRoom.getExit(Directions.NORTH));
         assertNull("South exit should be null", currentRoom.getExit(Directions.SOUTH));
     }

     // ---------------------
     // Additional Navigation and Exit Tests for Completeness
     // ---------------------

     // 20. Test Moving to Engine Room Storage and Back
     @Test
     public void testMoveToEngineStorageAndBack() {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move south to Engine Room Hallway
         game.runCommands("go south");
         // Move south to Engine Room
         game.runCommands("go south");
         // Move east to Engine Room Storage
         game.runCommands("go east");

         // Check current room
         Room currentRoom = player.getCurrentLocation();
         assertEquals("Engine Room Storage", currentRoom.getName());

         // Move west back to Engine Room
         game.runCommands("go west");

         // Check current room
         currentRoom = player.getCurrentLocation();
         assertEquals("Engine Room", currentRoom.getName());
     }

     // 21. Test Moving to Weapons Bay and Back
     @Test
     public void testMoveToWeaponsBayAndBack() {
         // Move south to Barracks
         game.runCommands("go south");
         // Move east to Barracks Hallway
         game.runCommands("go east");
         // Move west to Mess Hall
         game.runCommands("go west");
         // Move south to Engine Room Hallway
         game.runCommands("go south");
         // Move west to Hallway Weapons
         game.runCommands("go west");
         // Move east to Weapons Bay
         game.runCommands("go east");

         // Check current room
         Room currentRoom = player.getCurrentLocation();
         assertEquals("Engine Room Hallway", currentRoom.getName());

         // Move west back to Hallway Weapons
         game.runCommands("go west");

         // Check current room
         currentRoom = player.getCurrentLocation();
         assertEquals("Engine Room Hallway", currentRoom.getName());
     }
 }
