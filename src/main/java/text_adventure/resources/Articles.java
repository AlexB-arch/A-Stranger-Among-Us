package text_adventure.resources;

import java.util.HashMap;

public class Articles {

    static String[] articles = {
        "a",
        "at",
        "the",
        "an",
        "some",

    };

    public static void insertArticles(HashMap<String, WordType> dictionary) {
        for (String word : articles) {
            dictionary.put(word, WordType.ARTICLE);
        }
    }
    
}
