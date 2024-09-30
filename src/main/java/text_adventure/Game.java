package text_adventure;

public class Game {

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
    System.out.println("Welcome to the Text Adventure Game!");

    // Start the game loop
    while (!manager.isGameOver()) {
      String input = manager.getInput();
      processInput(input);
      manager.updateGame();
    }

    System.out.println("Game Over!");
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

  // Add more methods as needed
  // Keep in mind to add error checking and handling
  // state management
}
