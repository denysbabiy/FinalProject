package com.epam.finalproject.db.dao.MySqlImpl;

import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.dao.QuestionDAO;
import com.epam.finalproject.db.entity.Answer;
import com.epam.finalproject.db.entity.Question;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlQuestionDAO implements QuestionDAO {
    private static final Logger log = Logger.getLogger(MySqlQuestionDAO.class);


    private static final String SQL_QUESTION_GET_ALL_BY_QUIZ_ID = "SELECT * FROM quizdb.question WHERE quiz_id=?";
    private static final String SQL_QUESTION_INSERT = "INSERT INTO question VALUES (default ,?,?)";
    private static final String SQL_QUESTION_GET_BY_ID = "SELECT * FROM question WHERE id=?";
    private static final String SQL_QUESTION_UPDATE = "UPDATE question SET question=? WHERE id=?";

    @Override
    public List<Question> getAllQuestionsByQuizId(int quizId,Connection con) throws SQLException {
        List<Question> questionList = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = con.prepareStatement(SQL_QUESTION_GET_ALL_BY_QUIZ_ID)) {
            ps.setInt(1, quizId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestion(rs.getString("question"));
                question.setQuizId(rs.getInt("quiz_id"));
                questionList.add(question);
            }
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return questionList;
    }

    @Override
    public boolean insert(Question question, Connection con) throws SQLException {
        ResultSet rs = null;
        try (PreparedStatement ps = con.prepareStatement(SQL_QUESTION_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, question.getQuestion());
            ps.setInt(2, question.getQuizId());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                question.setId(rs.getInt(1));
                for (Answer answer: question.getAnswers()) {
                    answer.setQuestionId(rs.getInt(1));
                }
            }

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public Question getQuestionById(int id, Connection con) throws SQLException {
        ResultSet rs = null;
        Question question = null;
        try(PreparedStatement ps = con.prepareStatement(SQL_QUESTION_GET_BY_ID)){
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if(rs.next()){
                question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuizId(rs.getInt("quiz_id"));
                question.setQuestion(rs.getString("question"));
            }
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        }finally {
            if (rs != null) {
                rs.close();
            }
        }
        return question;
    }

    @Override
    public boolean update(Question question, Connection con) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement(SQL_QUESTION_UPDATE)){
            ps.setString(1,question.getQuestion());
            ps.setInt(2,question.getId());
            ps.executeUpdate();

        }catch (SQLException throwables){
            log.error(throwables.getMessage());
            throw new SQLException();
        }
        return true;
    }
}
