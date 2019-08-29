package com.example.trivia.model;

public class CategoryEvent {
    private String categoryNumber;
    private String categoryText;

    public CategoryEvent(String categoryNumber, String categoryText) {
        this.categoryNumber = categoryNumber;
        this.categoryText = categoryText;
    }

    public String getCategoryNumber() {
        return categoryNumber;
    }

    public String getCategoryText() {
        return categoryText;
    }
}
