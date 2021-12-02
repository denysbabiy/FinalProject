package com.epam.finalproject.db.entity;

import java.io.Serializable;
import java.util.Date;

public class QuizResult extends Entity implements Serializable {
    private double result;
    private Date create_time;
    private int userId;
    private int quizId;

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }
}
