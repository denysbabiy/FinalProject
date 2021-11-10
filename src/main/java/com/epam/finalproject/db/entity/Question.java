package com.epam.finalproject.db.entity;

public class Question extends Entity{
    private String questionUa;
    private String questionEn;
    private int quizId;

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getQuestionUa() {
        return questionUa;
    }

    public void setQuestionUa(String questionUa) {
        this.questionUa = questionUa;
    }

    public String getQuestionEn() {
        return questionEn;
    }

    public void setQuestionEn(String questionEn) {
        this.questionEn = questionEn;
    }
}
