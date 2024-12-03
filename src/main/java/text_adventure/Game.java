package text_adventure;

import java.util.List;
import java.util.Map;

import text_adventure.objects.Container;
import text_adventure.objects.Item;
import text_adventure.objects.MessageBus;
import text_adventure.objects.NPC;
import text_adventure.objects.Player;
import text_adventure.objects.Room;
import text_adventure.objects.TextMessage;
import text_adventure.objects.triggers.GeneratorTrigger;
import text_adventure.objects.triggers.AliceDialog;
import text_adventure.objects.triggers.GameOverTimer;

public class Game implements java.io.Serializable {
	private static Game instance;
  	public static Player player;
  	public static boolean DEBUG;

 	public static MessageBus globalEventBus;

  	public static Map<String, Room> gameWorld;

  	private ConsoleManager consoleManager;

	private AsyncTimerManager timerManager;

  	private boolean shouldexit;

  	public Game() {
		instance = this;
		Parser.initDictionary();
		globalEventBus = new MessageBus(70,5);
		shouldexit = false;
    	start();
  	}

	public void start() {

		// Initalize the Message Bus
		globalEventBus.startMessageProcessing();

		// Timer Manager
		timerManager = new AsyncTimerManager();


		// Initialize the player
		player = new Player();
		consoleManager = new ConsoleManager();
		globalEventBus.registerSubscriber("PLAYER", player);
		globalEventBus.registerSubscriber("CONSOLE", consoleManager);
		
		// Initialize the world
		World worldBuilder = new World();
		gameWorld = worldBuilder.initializeRooms();
		worldBuilder.visualizeWorld();


		// Timers
		GameOverTimer gameTimer = new GameOverTimer();
		// Set the player's starting location
		player.setCurrentLocation(gameWorld.get("Barracks Storage"));

		// Initialize NPCs
		NPC alice = new NPC("Alice", gameWorld.get("Mess Hall"));
		globalEventBus.registerSubscriber("NPC", alice);

		GeneratorTrigger generatorTrigger = new GeneratorTrigger();
		AliceDialog AliceDial = new AliceDialog("Mess Hall");

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
		} else {
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

  	public String useItem(String itemName) {
        Item item = player.getInventory().getItemByName(itemName);
        if (item != null) {
            boolean used = item.performAction("use");
            if (used) {
                globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You have used the " + itemName + "."));
                return "You have used the " + itemName + ".";
            } else {
                globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You can't use the " + itemName + "."));
                return "You can't use the " + itemName + ".";
            }
        } else {
            globalEventBus.publish(new TextMessage("CONSOLE", "OUT", "You don't have a " + itemName + "."));
            return "You don't have a " + itemName + ".";
        }
    }

	public static Game getInstance() {
		return instance;
	}

	public void shutdownAsync(){
		timerManager.shutdown();
		globalEventBus.shutdown();
	}

	public void takeItem(String itemName) {
		player.takeItem(itemName);
	}

	public String lookAtObject(String itemName) {
		Room currentRoom = player.getCurrentLocation();
		Item item = currentRoom.getInventory().getItemByName(itemName);
		if (item == null) {
			item = player.getInventory().getItemByName(itemName);
		}

		if (item != null) {
			String description = item.getDescription();
			globalEventBus.publish(new TextMessage("CONSOLE", "OUT", description));
			return description;
		} else {
			String message = "You don't see a " + itemName + " here.";
			globalEventBus.publish(new TextMessage("CONSOLE", "OUT", message));
			return message;
		}
	}

    public String lookInObject(String itemName) {
		Item item = player.getInventory().getItemByName(itemName);
		if (item == null) {
			item = player.getCurrentLocation().getInventory().getItemByName(itemName);
		}

		if (item instanceof Container) {
			Container container = (Container) item;
			if (container.isOpen()) {
				String contents = container.describeItems();
				globalEventBus.publish(new TextMessage("CONSOLE", "OUT", contents));
				return contents;
			} else {
				String message = "The " + itemName + " is closed.";
				globalEventBus.publish(new TextMessage("CONSOLE", "OUT", message));
				return message;
			}
		} else {
			String message = "You can't look inside the " + itemName + ".";
			globalEventBus.publish(new TextMessage("CONSOLE", "OUT", message));
			return message;
		}
    }
}
