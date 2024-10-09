
import static org.junit.Assert.*;

import java.io.PrintStream;
import java.util.HashMap;

import org.junit.Test;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.After;
import org.junit.Before;


import text_adventure.Parser;
import text_adventure.Game;
import text_adventure.resources.WordType;

public class GameTests {

    Game game;


    // Captures text from the console
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    // Standard output. Needs to be restored afterwards.
    private final PrintStream output = System.out;




    public String formatTestOutput( String testName, String input,String output){
        return "-------\n"+testName+"\n"+"Input:"+input+"\nOutput:"+output+"\n------";
    }

    
    @Before
    public void setUp() {
        game = new Game();
        System.setOut(new PrintStream(outputStreamCaptor));

    }

    @After
    public void tearDown() {
        System.setOut(output);
    }
    


    // Tests dictionary initialization
    @Test
    public void testDictionary() {

        // Tests that its empty first.
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
        assertTrue(formatTestOutput("Test praser",Integer.toString(parserSize),Integer.toString(Parser.dictionary.size())),Parser.dictionary.size() > parserSize);

    }
    
    

    @Test
    public void testUp() {
        // Test using standard output
        String expectedOutput = "You can't go that way.";
        String input = "go up";
        game.runCommands("go up");
        output.println(outputStreamCaptor);
        assertTrue(formatTestOutput("Test Up", input, outputStreamCaptor.toString()),expectedOutput.equals(outputStreamCaptor.toString().trim()));
        
    }

    @Test
    public void testDown() {
        String expectedOutput = "You can't go that way.";
        String input = "go up";
        game.runCommands(input);
        assertTrue(formatTestOutput("Test Down", input, outputStreamCaptor.toString()),expectedOutput.equals(outputStreamCaptor.toString().trim()));
    }

     @Test
     public void testQuit() {
        String expectedOutput = "You've been ejected into the cold vacuum of space. Game Over.";
        String input = "Quit";
        game.runCommands(input);
        assertTrue(formatTestOutput("Test Quit", input, outputStreamCaptor.toString()),expectedOutput.equals(outputStreamCaptor.toString().trim()));
    }

    @Test
    public void testNorth() {
        String expectedOutput = "You can't go that way.";
        String input = "go north";
        game.runCommands(input);
        assertTrue(formatTestOutput("Test North", input, outputStreamCaptor.toString()),expectedOutput.equals(outputStreamCaptor.toString().trim()));
    }
    @Test
    public void testSouth() {
        String expectedOutput = "You can't go that way.";
        String input = "go south";
        game.runCommands(input);
        assertTrue(formatTestOutput("Test South", input, outputStreamCaptor.toString()),expectedOutput.equals(outputStreamCaptor.toString().trim()));
    }
    @Test
    public void testEast() {
        String expectedOutput = "You can't go that way.";
        String input = "go east";
        game.runCommands(input);
        assertTrue(formatTestOutput("Test East", input, outputStreamCaptor.toString()),expectedOutput.equals(outputStreamCaptor.toString().trim()));
    }
    @Test
    public void testWest() {
        String expectedOutput = "You are in the Hallway A1.\n" + "A hallway connects several rooms.\n" + "Exits: south east";
        String input = "go west";
        game.runCommands(input);
        assertTrue(formatTestOutput("Test West", input, outputStreamCaptor.toString()),expectedOutput.equals(outputStreamCaptor.toString().trim()));
    }

    @Test
    public void testUnknownWord() {
        String input = "ff";
        String expectedOutput = "I don\'t know the word \'"+input+"\'";
        String output = game.runCommands(input);
        System.out.print(output);
        assertTrue(formatTestOutput("Test Unknown Word", input, output),expectedOutput.equals(output.trim()));
    }

    @Test
    public void testNoInput(){
        String input = "";
        String expectedOutput = "Please enter a command.\n";
        String output = game.runCommands(input);
        assertTrue(formatTestOutput("Test Non input", input, output),expectedOutput.equals(output));
    }

    @Test
    public void testLookAt(){
        String input = "look at wires";
        String expectedOutput = "I don't know the word 'at'";
        game.runCommands(input);
        assertTrue(formatTestOutput("Test Look At", input, outputStreamCaptor.toString()),expectedOutput.equals(outputStreamCaptor.toString().trim()));
    }
    
    @Test
    public void testGo(){
        String input = "go";
        String expectedOutput = "Go where?";
        String output = game.runCommands(input);
        assertTrue(formatTestOutput("Test Go", input, output),expectedOutput.equals(output));
    }
    
}
