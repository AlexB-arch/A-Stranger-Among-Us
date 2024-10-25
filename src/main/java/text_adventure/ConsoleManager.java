package text_adventure;

import text_adventure.objects.Message;
import text_adventure.Subscriber;

public class ConsoleManager implements Subscriber{


    @Override
    public void onMessage(Message message) {
        if (message.getHeader().equals("CONSOLE")) {
            switch (message.getType()) {
                case "OUT":
                    showMessage(message.getMessage());
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