package com.epam.finalproject.db.dao;

import com.epam.finalproject.db.entity.QuizResult;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface QuizResultDAO {
    boolean insert(QuizResult quizResult, Connection con) throws SQLException;
    List<QuizResult> getQuizResults(int id, int quizId, Connection connection) throws SQLException;
}
