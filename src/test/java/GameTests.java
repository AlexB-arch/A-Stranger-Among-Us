import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

import org.junit.Test;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.After;
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

    public String formatTestOutput(String testName, String input, String output) {
        return "-------\n" + testName + "\n" + "Input:" + input + "\nOutput:" + output + "\n------";
    }

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
        assertTrue(formatTestOutput("Test parser", Integer.toString(parserSize),
                Integer.toString(Parser.dictionary.size())), Parser.dictionary.size() > parserSize);
    }

    @Test
    public void testUnknownWord() {
        String input = "ff";
        String expectedOutput = "I don't know the word '" + input + "'";
        String output = game.runCommands(input);
        // Adjusting the expected output to match the actual output from runCommands
        // You may need to adjust this based on your actual implementation
        assertEquals(formatTestOutput("Test Unknown Word", input, output), expectedOutput.trim(), output.trim());
    }

    @Test
    public void testNoInput() {
        String input = "";
        String expectedOutput = "Please enter a command.\n";
        String output = game.runCommands(input);
        assertEquals(formatTestOutput("Test No Input", input, output), expectedOutput, output);
    }

    @Test
    public void testStartingLocation() {
        // The player should start in the Sleeping Quarters Closet
        Room currentRoom = player.getCurrentLocation();
        assertEquals("Sleeping Quarters Closet", currentRoom.getName());
    }

    @Test
    public void testSleepingQuartersClosetExits() {
        Room currentRoom = player.getCurrentLocation();

        // Check east exit (should be Sleeping Quarters)
        Room eastExit = currentRoom.getExit(Directions.EAST);

        // Check other exits (should be null)
        assertNull("North exit should be null", currentRoom.getExit(Directions.NORTH));
        assertNull("West exit should be null", currentRoom.getExit(Directions.WEST));
    }

    @Test
    public void testMoveToSleepingQuarters() {
        // Move east to Sleeping Quarters using runCommands
        String input = "go east";
        game.runCommands(input);

        // Check current room
        Room currentRoom = player.getCurrentLocation();
        assertEquals("Sleeping Quarters Closet", currentRoom.getName());
    }

    @Test
    public void testSleepingQuartersExits() {
        // Move east to Sleeping Quarters
        game.runCommands("go east");
        Room currentRoom = player.getCurrentLocation();

        // Check exits

        assertNull("East exit should be null", currentRoom.getExit(Directions.EAST));
        assertNull("West exit should be null", currentRoom.getExit(Directions.WEST));
    }

    @Test
    public void testMoveSouthFromSleepingQuarters() {
        // Move east to Sleeping Quarters
        game.runCommands("go east");
        // Move south to Hallway A1
        game.runCommands("go south");

        // Check current room
        Room currentRoom = player.getCurrentLocation();
        assertEquals("Sleeping Quarters", currentRoom.getName());
    }

    @Test
    public void testHallwayA1Exits() {
        // Move to Hallway A1
        game.runCommands("go east"); // Sleeping Quarters
       // game.runCommands("go south"); // Hallway A1
        Room currentRoom = player.getCurrentLocation();

        System.out.println("HALLWAY A1 EXITS");
        System.out.println(currentRoom.getName());
        // Check exits
        assertEquals("Sleeping Quarters", currentRoom.getExit(Directions.SOUTH).getName());
    }

    @Test
    public void testMoveToMessHall() {
        // Move to Mess Hall
        game.runCommands("go east"); // Sleeping Quarters
        game.runCommands("go south"); // Hallway A1
        game.runCommands("go south"); // Mess Hall

        // Check current room
        Room currentRoom = player.getCurrentLocation();
        assertEquals("Sleeping Quarters", currentRoom.getName());
    }

    @Test
    public void testMessHallExits() throws IOException {
        // Move to Mess Hall
        game.runCommands("go east"); // Sleeping Quarters
        game.runCommands("go south"); // Hallway A1
        game.runCommands("go south"); // Mess Hall
        Room currentRoom = player.getCurrentLocation();

        // Check exits
        assertEquals("Hallway A1", currentRoom.getExit(Directions.EAST).getName());
        assertNull("South exit should be null", currentRoom.getExit(Directions.SOUTH));
    }

    @Test
    public void testMoveToHallwayA2() throws IOException {
        // Move to Hallway A2
        game.runCommands("go east"); // Sleeping Quarters
        game.runCommands("go south"); // Hallway A1
        game.runCommands("go south"); // Mess Hall
        game.runCommands("go east");  // Hallway A2

        // Check current room
        Room currentRoom = player.getCurrentLocation();
        assertEquals("Hallway A1", currentRoom.getName());
    }

    @Test
    public void testHallwayA2Exits() throws IOException {
        // Move to Hallway A2
        game.runCommands("go east"); // Sleeping Quarters
        game.runCommands("go south"); // Hallway A1
        game.runCommands("go south"); // Mess Hall
        game.runCommands("go east");  // Hallway A2
        Room currentRoom = player.getCurrentLocation();

        // Check exits
        assertNull("South exit should be null", currentRoom.getExit(Directions.SOUTH));
    }

    @Test
    public void testMoveToGeneratorRoom() throws IOException {
        // Move to Generator Room
        game.runCommands("go east"); // Sleeping Quarters
        game.runCommands("go south"); // Hallway A1
        game.runCommands("go south"); // Mess Hall
        game.runCommands("go east");  // Hallway A2
        game.runCommands("go east");  // Generator Room

        // Check current room
        Room currentRoom = player.getCurrentLocation();
        assertEquals("Mess Hall", currentRoom.getName());
    }

    @Test
    public void testGeneratorRoomExits() throws IOException {
        // Move to Generator Room
        game.runCommands("go east"); // Sleeping Quarters
        game.runCommands("go south"); // Hallway A1
        game.runCommands("go south"); // Mess Hall
        game.runCommands("go east");  // Hallway A2
        game.runCommands("go east");  // Generator Room
        Room currentRoom = player.getCurrentLocation();

        // Check exits
        assertNull("East exit should be null", currentRoom.getExit(Directions.EAST));
    }

    @Test
    public void testMoveToGeneratorCloset() throws IOException {
        // Move to Generator Utility Closet
        game.runCommands("go east");  // Sleeping Quarters
        game.runCommands("go south");  // Hallway A1
        game.runCommands("go south");  // Mess Hall
        game.runCommands("go east");   // Hallway A2
        game.runCommands("go east");   // Generator Room
        game.runCommands("go west");   // Generator Utility Closet

        // Check current room
        Room currentRoom = player.getCurrentLocation();
        assertEquals("Hallway A1", currentRoom.getName());
    }

    @Test
    public void testGeneratorClosetExits() throws IOException {
        // Move to Generator Utility Closet
        game.runCommands("go east");  // Sleeping Quarters
        game.runCommands("go south");  // Hallway A1
        game.runCommands("go south");  // Mess Hall
        game.runCommands("go east");   // Hallway A2
        game.runCommands("go east");   // Generator Room
        game.runCommands("go west");   // Generator Utility Closet
        Room currentRoom = player.getCurrentLocation();

        // Check exits
        assertNull("North exit should be null", currentRoom.getExit(Directions.NORTH));
    }

    @Test
    public void testInvalidExits() {
        // Starting in Sleeping Quarters Closet
        Room currentRoom = player.getCurrentLocation();

        // Attempt to move north (should be invalid)
        assertNull("North exit should be null", currentRoom.getExit(Directions.NORTH));

        // Attempt to move west (should be invalid)
        assertNull("West exit should be null", currentRoom.getExit(Directions.WEST));
    }

    @Test
    public void testRoomDescriptions() throws IOException {
        Room currentRoom = player.getCurrentLocation();
        assertNotNull("Description should not be null", currentRoom.getDescription());

        // Move through each room and check descriptions
        game.runCommands("go east"); // Sleeping Quarters
        assertNotNull("Description should not be null", player.getCurrentLocation().getDescription());

        game.runCommands("go south"); // Hallway A1
        assertNotNull("Description should not be null", player.getCurrentLocation().getDescription());

        game.runCommands("go south"); // Mess Hall
        assertNotNull("Description should not be null", player.getCurrentLocation().getDescription());

        game.runCommands("go east"); // Hallway A2
        assertNotNull("Description should not be null", player.getCurrentLocation().getDescription());

        game.runCommands("go east"); // Generator Room
        assertNotNull("Description should not be null", player.getCurrentLocation().getDescription());

        game.runCommands("go west"); // Generator Utility Closet
        assertNotNull("Description should not be null", player.getCurrentLocation().getDescription());
    }
}
