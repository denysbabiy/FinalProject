package com.epam.finalproject.db.dao;

import com.epam.finalproject.db.entity.Question;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface QuestionDAO{
    List<Question> getAllQuestionsByQuizId(int quizId,Connection con) throws SQLException;
    boolean insert(Question question, Connection con) throws SQLException;
    Question getQuestionById(int id,Connection con) throws SQLException;
    boolean update(Question question,Connection con) throws SQLException;
}
