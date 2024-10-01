package text_adventure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

  public static String consoleMessage;

  private static Boolean gameOver = false;
  private Player player;
  private Parser commands;

  public Game() {
  }

  public void start() throws IOException{
    gameStartArt();
    consoleMessage = "You're working on fixing some wires in the sleeping quarters when the lights suddenly go out. You attempt to flick them back on, only to find that they won't react. You decide to put your task on hold to investigate.";
    System.out.println(consoleMessage);

    gameLoop();
  }

  private void gameLoop() throws IOException{
    BufferedReader in;
		String input;

		in = new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.print("Enter command: ");
			input = in.readLine();
			Parser.runCommand(input);
			System.out.println("You entered '" + input + "'");
		}while (!gameOver);
  }

  public static void endGame(){
    consoleMessage = "You've been ejected into the cold vacuum of space. Game Over.";
    System.out.println(consoleMessage);
    gameOver = true;
  }

  // Add more methods as needed
  // Keep in mind to add error checking and handling
  // state management

  public static void gameStartArt(){
    consoleMessage = "Welcome to the Space Station Adventure Game!";
    System.out.println(consoleMessage);
  }
}
