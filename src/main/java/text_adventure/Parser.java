package text_adventure;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.HashMap;

import text_adventure.resources.WordType;
import text_adventure.resources.Directions;
import text_adventure.resources.Nouns;
import text_adventure.resources.Verbs;

public class Parser {

  // Initializes a list of words for player actions
  public static HashMap<String, WordType> dictionary;

  // Intialize the dictionary
  public static void initDictionary() {
	dictionary = new HashMap<String, WordType>();

	// Pass the dictionary to the other classes to populate it
	Verbs.insertVerbs(dictionary);
	Nouns.insertNouns(dictionary);
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
		case "putin":
		case "putinto":
			//response = Main.game.putObjectIn(input2.getWord(), input4.getWord());
			break;
		
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
					//response = Main.game.lootAtObject(input3.getWord());
					break;
				case "lookin":
					//response = Main.game.lookInObject(input3.getWord());
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

		// Check if the first word is a valid action
		if (input1.getWordType() != WordType.VERB) {
			response = "Can't do that. " + input1.getWord() + " is not a valid action!";
		} else if (input2.getWordType() != WordType.NOUN) {
			response = "Can't do that. " + input2.getWord() + " is not a valid object!";
		} else {
			switch (input1.getWord()) {
				//TODO: Implement the cases
				default:
					response = "Not yet implemented";
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
				// TODO: Add the player actions such as look, take, etc.
				// Add the player movement actions here
				case "north":
					Game.player.move(Directions.NORTH);
				case "south":
					Game.player.move(Directions.SOUTH);
				case "east":
					Game.player.move(Directions.EAST);
				case "west":
					Game.player.move(Directions.WEST);
			    case "open":
					//Game.player.playerInventory.printItems();

				default:
					response = "Not yet implemented";
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
		List<WordProcessor> processedInput = new ArrayList<WordProcessor>();
		WordType type;
		String response;
		String errorMessage = "";

		for (String word : input) {
			if (dictionary.containsKey(word)){
				type = dictionary.get(word);
				
				if (type != WordType.ARTICLE) { 
					// Skip articles
					processedInput.add(new WordProcessor(word, type));
				}

			} else {
				processedInput.add(new WordProcessor(word, WordType.ERROR));
				errorMessage += "I don't know the word '" + word + "'\r\n";
			}
		}

		// Check if there is an error message to send back
		if (errorMessage != "") {
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