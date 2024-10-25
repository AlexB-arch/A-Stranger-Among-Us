package text_adventure;

import text_adventure.objects.Message;
import text_adventure.objects.TextMessage;
import text_adventure.Subscriber;

public class ConsoleManager implements Subscriber{
    private boolean isDebug = false;

    @Override
    public void onMessage(Message message) {
        if (message.getHeader().equals("CONSOLE")) {
            switch (message.getType()) {
                case "OUT":
                    showMessage(message.getMessage());
                    Game.globalEventBus.publish(new TextMessage("CONSOLE", "INPUT", ""));
                    break;
                case "DEBUG":
                    if (isDebug){
                        showMessage("DEBUG: "+message.getMessage());
                    }
                    break;
                case "DEBUG_TOGGLE":
                    isDebug = !isDebug;
                    showMessage("Debug messages: " + isDebug);
                    break;

                case "INPUT":
                    Game.globalEventBus.publish(new TextMessage("CONSOLE", "DEBUG", "Doing the console"));
                    System.out.print(">>");
                    break;
                
                default:
                    break;
            }
    }
    
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

