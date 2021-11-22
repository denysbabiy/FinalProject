package com.epam.finalproject.db.dao;

import com.epam.finalproject.db.entity.Answer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AnswerDAO {
    boolean insert(Answer answer, Connection con) throws SQLException;

    List<Answer> getAllAnswersByQuestionId(int id, Connection con) throws SQLException;

    boolean update(Answer answer, Connection con) throws SQLException;

    boolean deleteById(int id, Connection con) throws SQLException;

    Answer getAnswerById(int id, Connection con) throws SQLException;

    List<Answer> getAllCorrectAnswersByQuizId(int id, Connection con) throws SQLException;

    List<Answer> getAllCorrectAnswersByQuestionId(int questionId, Connection con) throws SQLException;
}
