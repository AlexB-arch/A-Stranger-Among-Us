package text_adventure;

public class Game {

  public static String consoleMessage;

  private GameManager manager;
  private Player player;

  public Game() {
    // Initialize the game
    this.manager = new GameManager();
    this.player = new Player();
    // Create the player
    // Create the rooms
    // Set that the game is running
  }

  public void start(){
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
    /* 
    Console console = System.console();
    String command = console.readLine();
    */
  }

  public void endGame(){
    consoleMessage = "You've been ejected into the cold vacuum of space. Game Over.";
    System.out.println(consoleMessage);
  }

  // Add more methods as needed
  // Keep in mind to add error checking and handling
  // state management
}
