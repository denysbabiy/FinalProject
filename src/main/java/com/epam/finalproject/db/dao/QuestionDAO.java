package com.epam.finalproject.db.dao;

import com.epam.finalproject.db.entity.Question;

import java.util.List;

public interface QuestionDAO{
    List<Question> getAllQuestionsByQuizId(int quizId);
    boolean insert(Question question);
}
