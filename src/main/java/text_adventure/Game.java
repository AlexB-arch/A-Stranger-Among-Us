package text_adventure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import text_adventure.objects.Player;
import text_adventure.objects.Room;
import text_adventure.resources.Directions;
import text_adventure.resources.Verbs;

public class Game implements java.io.Serializable {

  public static String consoleMessage;

  private ArrayList<Room> map;
  private Player player;

  public Game() {
    Parser.initDictionary();
    start();
  }

  public void start() {
    consoleMessage = "You're working on fixing some wires in the sleeping quarters when the lights suddenly go out. You attempt to flick them back on, only to find that they won't react. You decide to put your task on hold to investigate.";
    System.out.println(consoleMessage);

    // Initialize all rooms in this method
  }

  private void gameLoop(){
    BufferedReader in;
		String input;

		in = new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.print("Enter command: ");
			input = in.readLine();
			Parser.tokenizedInput(input);
			System.out.println("You entered '" + input + "'"); // for debugging. Delete later

      if (input.equals("quit")) {
        endGame();
        break;
      } 

		}while (!input.equals("quit"));
  }

  public static void endGame(){
    consoleMessage = "You've been ejected into the cold vacuum of space. Game Over.";
    System.out.println(consoleMessage);
  }

  // Add more methods as needed
  // Keep in mind to add error checking and handling
  // state management

  public static void gameStartArt(){
    consoleMessage = "Welcome to the Space Station Adventure Game!";
    System.out.println(consoleMessage);
  }
}
