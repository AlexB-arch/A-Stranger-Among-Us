package text_adventure;

import text_adventure.objects.Message;
import text_adventure.objects.TextMessage;
import text_adventure.Subscriber;

public class ConsoleManager implements Subscriber{
    private boolean isDebug = false;
    private boolean lockPromptInput = false;

    @Override
    public void onMessage(Message message) {
        if (message.getHeader().equals("CONSOLE")) {
            switch (message.getType()) {
                case "OUT":
                    showMessage(message.getMessage());
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
                case "LOCK_INPUT":
                         switch (message.getMessage()) {
                             case "LOCK":
                                 lockPromptInput = true;
                                 break;
                            case "UNLOCK":
                                 lockPromptInput = false;
                            case "TOGGLE":
                                lockPromptInput = !lockPromptInput;
                            default:
                                break;
                }
            
                case "INPUT":
                    if(lockPromptInput == false){
                        System.out.print(">>");
                    }
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

