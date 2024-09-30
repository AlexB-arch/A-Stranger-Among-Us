package text_adventure;

import java.io.Console;


public class Main {

    public static void main(String[] args) {
		var game = new Game();
		game.start();
		Console console = System.console();
		String command = console.readLine();
		game.endGame();

    }
}