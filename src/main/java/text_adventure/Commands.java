package text_adventure;

public class Commands {
  private String action;
  private String target;

  public Commands(String action, String target) {
    this.action = action;
    this.target = target;
  }

  // Getters
  public String getAction() {
    return action;
  }

  public String getTarget() {
    return target;
  }

  // Parse
  public static Commands parseCommand(String input){
    String[] parts = input.trim().toLowerCase().split(" ", 2);
    String action = parts[0];
    String target = parts.length > 1 ? parts[1] : "";

    return new Commands(action, target);
  }

  // Execute Commands
  
}
