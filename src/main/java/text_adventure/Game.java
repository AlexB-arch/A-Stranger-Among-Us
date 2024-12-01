package text_adventure;

import java.util.List;
import java.util.Map;

import text_adventure.objects.MessageBus;
import text_adventure.objects.NPC;
import text_adventure.objects.Player;
import text_adventure.objects.Room;
import text_adventure.objects.TextMessage;
import text_adventure.objects.triggers.GeneratorTrigger;
import text_adventure.objects.triggers.AliceDialog;

public class Game implements java.io.Serializable {

  public static Player player;
  public static boolean DEBUG;

  public static MessageBus globalEventBus;

  public static Map<String, Room> gameWorld;

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

		// Players starts in the sleeping quarters

		World worldBuilder = new World();
		 gameWorld = worldBuilder.initializeRooms();

		player.setCurrentLocation(gameWorld.get("Barracks Storage"));

		// Initialize NPCs
		NPC alice = new NPC("Alice", gameWorld.get("Mess Hall"));
		globalEventBus.registerSubscriber("NPC", alice);

		Trigger generatorTrigger = new GeneratorTrigger();
		Trigger AliceDial = new AliceDialog("Mess Hall");

		// Add NPCs to rooms
		gameWorld.get("Mess Hall").addNpc(alice);

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


	public static void showMessage(String msg ){
		Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT",msg));
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

}
