package com.epam.finalproject.db.entity;

public class Answer extends Entity{
    private String answerUa;
    private String answerEn;
    private int isCorrect;
    private int questionId;

    public String getAnswerUa() {
        return answerUa;
    }

    public void setAnswerUa(String answerUa) {
        this.answerUa = answerUa;
    }

    public String getAnswerEn() {
        return answerEn;
    }

    public void setAnswerEn(String answerEn) {
        this.answerEn = answerEn;
    }

    public int getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(int isCorrect) {
        this.isCorrect = isCorrect;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
