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
		Room Test1 = new Room("Test Room 1", "This is the first test room.",null);
    Room Test2 = new Room("Test Room 2", "This is the second test room.",null);
    Room Test3 = new Room("Test Room 3", "This is the third test room.",null);


		// Set the directions for each room
		Test1.setExits(Test2, null, null, null, true, false, false, false, "null", "null", "null", "null"); //I'm like 80% sure there's a better way to do this, I'm just not awake enought to figure it out rn.
    Test2.setExits(Test3, Test1, null, null, true, false, false, false, "null", "Red", "null", "null");
    Test3.setExits(null, Test2, null, null, true, false, false, false, "null", "Red", "null", "null");

		// Players starts in the sleeping quarters
		player.setCurrentLocation(Test1);

		// Initialize NPCs
		NPC alice = new NPC("Alice", Test2);
		globalEventBus.registerSubscriber("NPC", alice);

		// Add NPCs to rooms
		Test1.addNpc(alice);

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
