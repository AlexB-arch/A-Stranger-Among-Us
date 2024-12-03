package text_adventure;

import text_adventure.resources.*;

/**
 * WordProcessor - Alex
 * This class is used to process words.
 * 
 * Primarily used by the parser to handle the word and its type.
 */

public class WordProcessor {
    
    private String word;
    private WordType type;

    public WordProcessor(String word, WordType type) {
        this.word = word;
        this.type = type;
    }

    public String getWord() {
        return word;
    }

    public WordType getWordType() {
        return type;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setWordType(WordType type) {
        this.type = type;
    }

    public WordProcessor add(WordProcessor other) {
        return new WordProcessor(this.word + "-" + other.getWord(), this.type);
    }
}
