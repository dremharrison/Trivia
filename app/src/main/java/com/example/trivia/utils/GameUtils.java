package com.example.trivia.utils;

import java.util.HashMap;
import java.util.Map;

public class GameUtils {

    private static final Map<String, String> categories= new HashMap<>();
    static {
        categories.put("General Knowledge", "9");
        categories.put("Entertainment: Books", "10");
        categories.put("Entertainment: Film", "11");
        categories.put("Entertainment: Music", "12");
        categories.put("Entertainment: Musical and Theatres", "13");
        categories.put("Entertainment: Television", "14");
        categories.put("Entertainment: Video Games","15");
        categories.put("Entertainment: Board Games", "16");
        categories.put("Science and Nature", "17");
        categories.put("Science: Computers", "18");
        categories.put("Science: Mathematics", "19");
        categories.put("Mythology", "20");
        categories.put("Sports","21");
        categories.put("Geography", "22");
        categories.put("History", "23");
        categories.put("Politics", "24");
        categories.put("Art", "25");
        categories.put("Celebrities", "26");
        categories.put("Animals", "27");
        categories.put("Vehicles", "28");
        categories.put("Entertainment: Comics", "29");
        categories.put("Science: Gadgets", "30");
        categories.put("Entertainment: Japanese Anime and Menga", "31");
        categories.put("Entertainment: Cartoon and Animations", "32");
    }


    public static String getCategoryNumber(String categoryName) {
        return categories.get(categoryName);
    }
}
