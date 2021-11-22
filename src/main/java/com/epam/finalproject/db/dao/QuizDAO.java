package com.epam.finalproject.db.dao;

import com.epam.finalproject.db.entity.Quiz;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface QuizDAO {
    boolean insertQuiz(Quiz quiz, Connection con) throws SQLException;
    boolean deleteQuizById(int id);
    boolean updateQuiz(Quiz quiz, Connection con) throws SQLException;
    Quiz getQuizById(int id,Connection con) throws SQLException;
    int getQuizCountBySubject(int subjectId);
    List<Quiz> getAllQuizzesBySubject(int subjectId,Connection con) throws SQLException;
}
