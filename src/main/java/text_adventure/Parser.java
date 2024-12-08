package text_adventure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import text_adventure.objects.TextMessage;
import text_adventure.resources.Articles;
import text_adventure.resources.Directions;
import text_adventure.resources.Nouns;
import text_adventure.resources.Prepositions;
import text_adventure.resources.Verbs;
import text_adventure.resources.WordType;

public class Parser {

  // Initializes a list of words for player actions
  public static HashMap<String, WordType> dictionary;

  // Intialize the dictionary
  public static void initDictionary() {
	dictionary = new HashMap<String, WordType>();

	// Pass the dictionary to the other classes to populate it
	Verbs.insertVerbs(dictionary);
	Nouns.insertNouns(dictionary);
	Articles.insertArticles(dictionary);
	Prepositions.insertPrepositions(dictionary);
  }

  // Processes the Verb-Noun-Preposition-Noun input structure
  static String processVerbNounPrepositionNoun(List<WordProcessor> input) {
    WordProcessor input1 = input.get(0);
    WordProcessor input2 = input.get(1);
    WordProcessor input3 = input.get(2);
    WordProcessor input4 = input.get(3);
    String response = "";


    // Check if the first word is a valid action
    if ((input1.getWordType() != WordType.VERB) || (input3.getWordType() != WordType.PREPOSITION)) {
      response = "Can't do that. I don't understand how to '" + input1.getWord() + " something " + input3.getWord() + "' something!";
    } else if ( input2.getWordType() != WordType.NOUN) {
      response = "Can't do that. " + input2.getWord() + " is not a valid object!\r\n";
    } else if ( input4.getWordType() != WordType.NOUN) {
      response = "Can't do that. " + input4.getWord() + " is not a valid object!\r\n";
    } else {
      switch (input1.getWord() + input3.getWord()) { // Concatenates the verb and preposition
		default:
			response = "I don't know how to '" + input1.getWord() + " " + input2.getWord() + " " + input3.getWord() + " " + input4.getWord() + "'";
			break;
      }
    }
    return response;
  }

  // Processes the Verb-Preposition-Noun input structure
  static String processVerbPreopisitionNoun(List<WordProcessor> input) {
		WordProcessor input1 = input.get(0);
		WordProcessor input2 = input.get(1);
		WordProcessor input3 = input.get(2);
		String response = "";

		// Check if the first word is a valid action
		if ((input1.getWordType() != WordType.VERB) || (input2.getWordType() != WordType.PREPOSITION)) {
			response = "Can't do that. I don't understand how to '" + input1.getWord() + " " + input2.getWord() + "' something!";
		} else if ( input3.getWordType() != WordType.NOUN) {
			response = "Can't do that. " + input3.getWord() + " is not a valid object!\r\n";
		} else {
			switch (input1.getWord() + input2.getWord()) {
				case "lookat":
					response = Main.game.lookAtObject(input3.getWord());
					break;
				case "lookin":
					response = Main.game.lookInObject(input3.getWord());
					break;
				case "talkto":
					response = "Not yet implemented";
					break;
				case "interactwith":
					Game.player.interact(input3.getWord());
					System.out.println(input3.getWord());
					break;
				default:
					response = "I don't know how to '" + input1.getWord() + " " + input2.getWord() + " " + input3.getWord() + "'";
					break;
	  		}
		}

		return response;
	}

	// Processes the Verb-Noun input structure
	static String processVerbNoun(List<WordProcessor> input) {
		WordProcessor input1 = input.get(0);
		WordProcessor input2 = input.get(1);
		String response = "";
	
		if (input1.getWordType() != WordType.VERB) {
			response = "Can't do that. " + input1.getWord() + " is not a valid action!";
		} else if (input2.getWordType() != WordType.NOUN) {
			response = "Can't do that. " + input2.getWord() + " is not a valid object!";
		} else {
			switch (input1.getWord()) {
				case "go":
					Game.player.move(Directions.valueOf(input2.getWord().toUpperCase()));
					break;
				case "interact":
					Game.player.interact(input2.getWord());
					break;
				// Handle other verbs
				case "take":
					Game.player.takeItemByName(input2.getWord());
                    break;
                case "use":
                    Game.player.useItem(input2.getWord());
                    break;
				default:
					response = "You can't do that.";
					break;
			}
		}
		return response;
	}

	// Processes the Verb input structure
	static String processVerb(List<WordProcessor> input) {
		WordProcessor input1 = input.get(0);
		String response = "";

		// Check if the first word is a valid action
		if (input1.getWordType() != WordType.VERB){
			response = "Can't do that. " + input1.getWord() + " is not a valid action!";
		} else {
			switch(input1.getWord()) {
				case "go":
					// call verb noun
					response = "Go where?";
					break;
				case "look":
					Game.globalEventBus.publish(new TextMessage("PLAYER","LOOK", "There's nothing here..."));
					break;
				case "talk":
					response = "Talk to who?";
					break;
				case "inventory":
					//response = Main.game.showInventory();
					break;
				case "quit", "exit":
					response = Main.game.endGame();
					break;
				case "debug":
					Game.globalEventBus.publish(new TextMessage("CONSOLE","DEBUG_TOGGLE", ""));
					break;
				case "take":
					response = "Take what?";
					break;
				case "drop":
					response = "Drop what?";
					break;
				case "use":
					response = "Use what?";
					break;
				case "open":
					response = "Open what?";
					break;
				case "close":
					response = "Close what?";
					break;
				case "push":
					response = "Push what?";
					break;
				case "give":
					response = "Give what?";
					break;
				case "interact":
					response = "Interact with what?";
					break;
				default:
					break;
			}
		}

		return response;
	}

	// Processes the input structure
	static String processInput(List<WordProcessor> input) {
		String response = "";

		// Check the size of the input
		if (input.size() == 0) {
			response = "You must write a command!";
		} else {
			switch(input.size()) {
				case 1:
					response = processVerb(input);
					break;
				case 2:
					response = processVerbNoun(input);
					break;
				case 3:
					response = processVerbPreopisitionNoun(input);
					break;
				case 4:
					response = processVerbNounPrepositionNoun(input);
					break;
				default:
					response = "I don't understand that command!";
					break;
			}
		}

		return response;
	}

	// Parses the input
	static String parseInput(List<String> input) {
		List<WordProcessor> processedInput = new ArrayList<>();
		String response;
		String errorMessage = "";
		int i = 0;
	
		while (i < input.size()) {
			boolean found = false;
	
			// Try to combine words to form the longest matching phrase in the dictionary
			for (int j = input.size(); j > i; j--) {
				String phrase = String.join(" ", input.subList(i, j));
				if (dictionary.containsKey(phrase)) {
					WordType type = dictionary.get(phrase);
					processedInput.add(new WordProcessor(phrase, type));
					i = j;
					found = true;
					break;
				}
			}
	
			if (!found) {
				String word = input.get(i);
				errorMessage += "I don't know the word '" + word + "'\n";
				i++;
			}
		}
	
		if (!errorMessage.equals("")) {
			response = errorMessage;
		} else {
			response = processInput(processedInput);
		}
	
		return response;
	}

	static List<String> tokenizedInput(String input) {
		String delimiters = "\s|('[^']*')|(?=\\W)";
		List<String> tokens = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(input, delimiters);

		while (tokenizer.hasMoreTokens()) {
			tokens.add(tokenizer.nextToken());
		}

		return tokens;
	}
}
