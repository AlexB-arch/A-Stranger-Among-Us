package text_adventure;

import java.util.List;

import text_adventure.objects.MessageBus;
import text_adventure.objects.NPC;
import text_adventure.objects.Player;
import text_adventure.objects.Room;
import text_adventure.objects.TextMessage;

public class Game implements java.io.Serializable {

  public static Player player;
  public static boolean DEBUG;

  public static MessageBus globalEventBus;

  private ConsoleManager consoleManager;

  private boolean shouldexit;

  public Game() {
    Parser.initDictionary();
	globalEventBus = new MessageBus(10,3);

	shouldexit = false;
    start();

  }

	public void start() {

		// Initalize the Message Bus
		globalEventBus.startMessageProcessing();
		// Initialize the player
		player = new Player();
		consoleManager = new ConsoleManager();
		globalEventBus.registerSubscriber("PLAYER", player);
		globalEventBus.registerSubscriber("CONSOLE", consoleManager);

		// Create the rooms
		Room Test1 = new Room("Sleeping Quarters", "The sleeping quarters are dark and quiet. The room is empty. The rest of the crew must be in other parts of the ship.\n\nThe door to the east leads to the Mess Hall, which you locked open when you came in. ",null);


		// Set the directions for each room
		hallwayA1.setExits(null, null, MessHall, sleepingQuartersCloset);
		sleepingQuarters.setExits(sleepingQuartersCloset, null, hallwayA1, null );
		sleepingQuartersCloset.setExits(null, sleepingQuarters, null ,null);
		MessHall.setExits(null, hallwayA2, null, hallwayA1);
		hallwayA2.setExits(MessHall, GeneratorRoom, null, null);
		GeneratorRoom.setExits( hallwayA2, null,null ,GeneratorCloset );
		GeneratorCloset.setExits(null, null, GeneratorRoom, null);

		// Players starts in the sleeping quarters
		player.setCurrentLocation(sleepingQuartersCloset);

		// Initialize NPCs
		NPC alice = new NPC("Alice", MessHall);
		globalEventBus.registerSubscriber("NPC", alice);

		// Add NPCs to rooms
		MessHall.addNpc(alice);

		// Display the intro message
		showIntro();
	}

  // Call the parser to tokenize the input
  public String runCommands(String inputString) {
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
    message = "To be continued...";
    setShouldExit(true);
	return message;
  }

  	public void showIntro(){
		String message;

    	message = "Welcome to A Stranger Among Us!\n";
		message += "by Strangers in a Strange Land \n\n";
		message += "You're working on fixing some wires in the sleeping quarters when the lights suddenly go out.\nYou attempt to flick them back on, only to find that they won't react. \nYou decide to put your task on hold to investigate.\n";
		message += "Where do you want to go?\n";
		message += "Enter 'go' and north, south, west, or east to move. \n";
		message += "Or type 'quit' or 'exit' to stop the game.\n";

		globalEventBus.publish(new TextMessage("CONSOLE", "OUT", message));
  }
  public boolean getShouldExit(){
	return shouldexit;
  }

  private void setShouldExit(boolean bool){
	shouldexit = bool;
  }

  public static void showMessage(String message){
	if (message.endsWith("\n")) { // stripping any trailing newlines
		message = message.substring(0, message.length() - 1);
	}

	if (!message.isEmpty()) {
		System.out.println(message);
	}
  }

}
