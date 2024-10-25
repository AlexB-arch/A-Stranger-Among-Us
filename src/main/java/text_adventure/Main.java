package text_adventure;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import text_adventure.objects.TextMessage;


public class Main {

	static Game game;

	// Do we want to save and load the game?

    public static void main(String[] args) throws IOException {
		BufferedReader in;
		String input;
		String output;

		game = new Game();
		in = new BufferedReader(new InputStreamReader(System.in));

		do {
			input = in.readLine();
			switch (input) {
				default:
					output = game.runCommands(input);
					Game.globalEventBus.publish(new TextMessage("CONSOLE","OUT",output));
					break;
			}
			
		} while (game.getShouldExit() != true);
	}
}