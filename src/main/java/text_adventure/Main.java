package text_adventure;

import java.io.Console;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    public static void main(String[] args) throws IOException {
		Game game = new Game();

		game.start();
	}
}