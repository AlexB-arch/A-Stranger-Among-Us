package text_adventure;

import java.util.ArrayList;
import java.util.List;

import text_adventure.objects.Player;
import text_adventure.objects.Room;

public class Game implements java.io.Serializable {

  private ArrayList<Room> map;
  private Player player;

  public Game() {
    Parser.initDictionary();
    start();
  }

  public void start() {
	// Initialize the player

    // Initialize all rooms in this method
  }

  // Call the parser to tokenize the input
  String runCommands(String inputString){
	List<String> inputList;
	String string = "";
	String lowerCaseInput;

	// Cleans up the input and converts it to lowercase
	lowerCaseInput = inputString.trim().toLowerCase();

	if (!lowerCaseInput.equals("quit")) {
		if(lowerCaseInput.equals("")){
			string = "Please enter a command.\n";
		} else {
			inputList = Parser.tokenizedInput(lowerCaseInput);
			string = Parser.parseInput(inputList);
		}
	}

	return string;
  }

  public void endGame(){
	String message;

    message = "You've been ejected into the cold vacuum of space. Game Over.";
    
	showMessage(message);
  }

  public void showIntro(){
		String message;

    	message = "Welcome to A Stranger Among Us!\n";
		message += "You're working on fixing some wires in the sleeping quarters when the lights suddenly go out.\nYou attempt to flick them back on, only to find that they won't react. \nYou decide to put your task on hold to investigate.\n";
		message += "Where do you want to go?\n";
		message += "Enter: north, south, west, east, up, or down\n";
		message += "Or type 'quit' to exit the game.\n";
		
		showMessage(message);
  }

  // Display messages to the console
  public void showMessage(String message){
		if (message.endsWith("\n")) { // stripping any trailing newlines
			message = message.substring(0, message.length() - 1);
		}

		if (!message.isEmpty()) {
			System.out.println(message);
		}
  }

  // Use look method to display the player's current location
  public void look() {
		//showMessage("You are in the " + player.describeLocation());
  }

  // Call Player methods

}
