package com.kmmi.kmmiquiz.model;

public class QuestionQuiz {
    private int id;
    private String question;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionOFour;
    private int questionAnswer;

    public QuestionQuiz(int id, String question,String optionOne, String optionTwo, String optionThree, String optionFour, int questionAnswer) {
        this.id = id;
        this.question = question;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.optionOFour = optionFour;
        this.questionAnswer = questionAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public void setOptionOne(String optionOne) {
        this.optionOne = optionOne;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public void setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
    }

    public String getOptionThree() {
        return optionThree;
    }

    public void setOptionThree(String optionThree) {
        this.optionThree = optionThree;
    }

    public String getOptionOFour() {
        return optionOFour;
    }

    public void setOptionOFour(String optionOFour) {
        this.optionOFour = optionOFour;
    }

    public int getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(int questionAnswer) {
        this.questionAnswer = questionAnswer;
    }
}
