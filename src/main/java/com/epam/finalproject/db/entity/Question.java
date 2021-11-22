package com.epam.finalproject.db.entity;

import java.util.List;

public class Question extends Entity{
    private String question;
    private int quizId;
    private List<Answer> answers;

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getCorrectAnswerCount(){
        int count = 0;
        for(Answer answer:answers){
            if(answer.getIsCorrect()==1){
                count++;
            }
        }
        return count;
    }

}
