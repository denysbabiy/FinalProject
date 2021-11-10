package com.epam.finalproject.db.dao;

import com.epam.finalproject.db.entity.Quiz;

import java.util.List;

public interface QuizDAO {
    boolean insertQuiz(Quiz quiz);
    boolean deleteQuizById(int id);
    boolean updateQuiz(Quiz quiz);
    Quiz getQuizById(int id);
    int getQuizCountBySubject(int subjectId);
    List<Quiz> getAllQuizzesBySubject(int subjectId);
}
