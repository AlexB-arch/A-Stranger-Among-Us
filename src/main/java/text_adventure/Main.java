package text_adventure;

import java.io.Console;


public class Main {
    private static String[] rooms = {
        "You are in a lucious, green area with trees, not Abilene.\nWhat is your command: north or exit?",
	"Welcome to Canada. north or exit?",
	"Welcome to the North Pole. north or exit?" };

    public static void main(String[] args) {
       
        // TODO: rename the author to your name
        System.out.println("Adventure V2, by Bubba");

        System.out.println(rooms[0]);

        Console console = System.console();
        String command = console.readLine();

        if (command.equals("north")) {
            System.out.println(rooms[1]);
            command = console.readLine();
	    
            if (command.equals("north")) {
                System.out.println(rooms[2]);
                command = console.readLine();
		
                if (command.equals("north")) {
                    System.out.println("You seem to be in Valhalla.");
                    System.exit(0);
                } else if (command.equals("exit")) {
                    System.exit(0);
		} else {
		    System.out.println("Hmmm");
		    System.exit(0);
		}
		
	    } else if (command.equals("exit")) {
		// TODO: don't be so rude to the user
		System.out.println("Good call, you coward! Bye for now.");
		System.exit(0);
	    } else {
		// how did the user arrive at this branch?
		// TODO: be more useful to the user
		System.exit(0);
	    }
	    
	} else if (command.equals("exit")) {
	    // TODO: be more useful to the user
	    System.exit(0);
	} else {
	    // how did the user arrive at this branch?
	    // TODO: be more useful to the user
	    System.out.println("Bail");
	    System.exit(0);
	}
	
    }
}