package text_adventure;

import java.util.ArrayList;
import java.util.List;

import text_adventure.objects.Player;
import text_adventure.objects.Room;

public class Game implements java.io.Serializable {

  private ArrayList<Room> map;

  public static Player player;

//   public static Player getPlayer() {
//     return player;
//   }
  private boolean shouldexit;

  public Game() {
    Parser.initDictionary();
	shouldexit = false;
    start();

  }

	public void start() {
		// Initialize the player
		player = new Player();

		// Initialize the map
		map = new ArrayList<Room>();

		// Create the rooms
		Room bridge = new Room("the Bridge", "The bridge is dark. The only source of light is the numerous buttons on the ship's controls.", null, null, null, null);
		Room hallwayA1 = new Room("Hallway A1", "An ample hallway connects the bridge with several rooms.", null, null, null, null);
		Room capsOffice = new Room("Captain's Office", "The Captain's office is completely dark. He doesn't seem to be here.", null, null, null, null);

		// Set the directions for each room
		bridge.setExits(hallwayA1, null, null, null);
		hallwayA1.setExits(null, bridge, capsOffice, null);
		capsOffice.setExits(null, null, null, hallwayA1);


		// Add the rooms to the map
		map.add(bridge);
		map.add(hallwayA1);
		map.add(capsOffice);

		// Set the current room
		player.setCurrentLocation(map.get(0));
		// Display the intro message
		showIntro();
	}

  // Call the parser to tokenize the input
  public String runCommands(String inputString){
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
	}else{
		string = endGame();
	}

		return string;
	}

  public String endGame(){
	String message;

    message = "You've been ejected into the cold vacuum of space. Game Over.";
    setShouldExit(true);
	return message;
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
  public boolean getShouldExit(){
	return shouldexit;
  }

  private void setShouldExit(boolean bool){
	shouldexit = bool;
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
}
