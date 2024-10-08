
import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;


import text_adventure.Parser;
import text_adventure.Game;
import text_adventure.resources.WordType;

public class GameTests {

    Game game;


    public String formatTestOutput( String testName, String input,String output){
        return "-------\n"+testName+"\n"+"Input:"+input+"\nOutput:"+output+"\n------";
    }

    
    @Before
    public void setUp() {
        game = new Game();
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
        String expectedOutput = "Not yet implemented";
        String input = "up";
        String output = game.runCommands(input);
        assertTrue(formatTestOutput("testUp",input,output),expectedOutput.equals(output));
    }

    @Test
    public void testDown() {
        String expectedOutput = "Not yet implemented";
        String input = "up";
        String output = game.runCommands(input);
        assertTrue(formatTestOutput("Test Down", input, output),expectedOutput.equals(output));
    }

     @Test
     public void testQuit() {
         String expectedOutput = "You've been ejected into the cold vacuum of space. Game Over.";
         String input = "Quit";
         String output = game.runCommands(input);
         assertTrue(formatTestOutput("Test Quit", input, output),expectedOutput.equals(output));
     }
    @Test
    public void testNorth() {
        String expectedOutput = "Not yet implemented";
        String input = "north";
        String output = game.runCommands(input);
        assertTrue(formatTestOutput("Test North", input, output),expectedOutput.equals(output));
    }
    @Test
    public void testSouth() {
        String expectedOutput = "Not yet implemented";
        String input = "south";
        String output = game.runCommands(input);
        assertTrue(formatTestOutput("Test South", input, output),expectedOutput.equals(output));
    }
    @Test
    public void testEast() {
        String expectedOutput = "Not yet implemented";
        String input = "east";
        String output = game.runCommands(input);
        assertTrue(formatTestOutput("Test East", input, output),expectedOutput.equals(output));
    }
    @Test
    public void testWest() {
        String expectedOutput = "Not yet implemented";
        String input = "west";
        String output = game.runCommands(input);
        assertTrue(formatTestOutput("Test West", input, output),expectedOutput.equals(output));
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
    
    
    
}
