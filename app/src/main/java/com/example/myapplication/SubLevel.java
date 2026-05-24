package com.example.myapplication;

public class SubLevel {
    private final int viewlevel2;
    private final String word1;
    private final String word2;

    public SubLevel(int viewlevel2, String word1, String word2) {
        this.viewlevel2 = viewlevel2;
        this.word1 = word1;
        this.word2 = word2;
    }

    public int getViewlevel2() {
        return viewlevel2;
    }

    public String getWord1() {
        return word1;
    }

    public String getWord2() {
        return word2;
    }
}
