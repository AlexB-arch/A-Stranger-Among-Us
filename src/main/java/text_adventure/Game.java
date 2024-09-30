package text_adventure;

public class Game {

  public static String consoleMessage;

  //private Player player;
  //private Room currentRoom;
  //private boolean isRunning;

  public Game() {
    // Initialize the game
    // Create the player
    // Create the rooms
    // Set that the game is running
  }

  public void start(){
   
    consoleMessage = "You're working on fixing some wires in the sleeping quarters when the lights suddenly go out. You attempt to flick them back on, only to find that they won't react. You decide to put your task on hold to investigate.";
    System.out.println(consoleMessage);
    // Start the game loop
    
  }

  private void gameLoop(){
    // While the game is running
    // Get the player's input
    // Process the player's input
    // Update the game
  }

  private void processInput(String input){
    // Check if the input is valid
    // If the input is valid, process the input
    // If the input is not valid, print an error message
  }

  public void endGame(){
    consoleMessage = "You've been ejected into the cold vacuum of space. Game Over.";
    System.out.println(consoleMessage);
  }

  // Add more methods as needed
  // Keep in mind to add error checking and handling
  // state management
}
