package text_adventure;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


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
			System.out.print(">> ");
			input = in.readLine();
			
			switch (input) {
				default:
					output = game.runCommands(input);
					break;
			}

			if (!output.trim().isEmpty()){
				Game.showMessage(output);
			}
			
		} while (game.getShouldExit() != true);
	}
}