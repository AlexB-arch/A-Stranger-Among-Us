package text_adventure;

import java.util.List;

import text_adventure.objects.Player;
import text_adventure.objects.Room;

public class Game implements java.io.Serializable {

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

		// Create the rooms
		Room bridge = new Room("the Bridge", "The bridge is dark. The only source of light is the numerous buttons on the ship's controls.", null, null, null, null);
		Room hallwayA1 = new Room("Hallway A1", "A hallway connects several rooms.", null, null, null, null);
		Room capsQuarters = new Room("Captain's Quarters", "The Captain's office is completely dark. He doesn't seem to be here.", null, null, null, null);
		Room sleepingQaurters = new Room("Sleeping Quarters", "The sleeping quarters are dark and quiet. The crew is nowhere to be found.", null, null, null, null);

		// Set the directions for each room
		bridge.setExits(hallwayA1, null, null, null);
		hallwayA1.setExits(null, bridge, capsQuarters, null);
		capsQuarters.setExits(null, null, null, hallwayA1);
		sleepingQaurters.setExits(null, null, null, hallwayA1);

		// Players starts in the sleeping quarters
		player.setCurrentLocation(sleepingQaurters);

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

	if (!lowerCaseInput.equals("quit") || !lowerCaseInput.equals("exit")) {
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
		message += "Enter 'go' and north, south, west, or east to move. \n";
		message += "Or type 'quit' or 'exit' to stop the game.\n";
		
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
		showMessage(player.getCurrentLocation().getDescription());
	}
}
