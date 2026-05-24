package com.example.myapplication;

public class TestQuestion {
    private final String sentence;
    private final String option1;
    private final String option2;
    private final int correctOption;

    public TestQuestion(String sentence, String option1, String option2, int correctOption) {
        this.sentence = sentence;
        this.option1 = option1;
        this.option2 = option2;
        this.correctOption = correctOption;
    }

    public String getSentence() {
        return sentence;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}
