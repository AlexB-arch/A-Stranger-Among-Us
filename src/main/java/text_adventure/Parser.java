package text_adventure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Parser {

  public static void parseCommand(List<String> commands){

    String action;
    String target;

    // List of valid actions and objects
    List<String> actions = new ArrayList<>(Arrays.asList("take", "drop", "quit", "exit"));
    List<String> objects = new ArrayList<>(Arrays.asList("wrench", "keycard", "screwdriver"));

    // Check if the command is valid
    if (commands.size() != 2) {
      System.out.println("Only 2 word commands allowed!");
    } else {
      action = commands.get(0);
      target = commands.get(1);
      if (!actions.contains(action)) {
        System.out.println(action + " is not a known verb!");
      }
      if (!objects.contains(target)) {
        System.out.println(target + " is not a known noun!");
      }
    }
  }

  // Tokenize the input string
  public static List<String> wordList(String input) {
    String delims = " \t,.:;?!\"'";
    List<String> stringlist = new ArrayList<>();
    StringTokenizer tokenizer = new StringTokenizer(input, delims);
    String token;

    while (tokenizer.hasMoreTokens()) {
      token = tokenizer.nextToken();
      stringlist.add(token);
    }
    return stringlist;
  }

  // Run the command
  public static String runCommand(String inputString) {
    List<String> wordList;
    String output = "ok";
    String lowerString = inputString.trim().toLowerCase();

    if (!lowerString.equals("quit")) {
      if (lowerString.equals("")) {
        output = "You must enter a command";
      } else {
        wordList = wordList(lowerString);
        parseCommand(wordList);
      }
    } else {
      output = "quit";
      Game.endGame();
    }
    return output;
  }
}