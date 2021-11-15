package com.epam.finalproject.db.dao.MySqlImpl;

import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.dao.QuestionDAO;
import com.epam.finalproject.db.entity.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlQuestionDAO implements QuestionDAO {


    private static final String SQL_QUESTION_GET_ALL_BY_QUIZ_ID = "SELECT * FROM quizdb.question WHERE quiz_id=?";
    private static final String SQL_QUESTION_INSERT = "INSERT INTO question VALUES (default ,?,?)";

    @Override
    public List<Question> getAllQuestionsByQuizId(int quizId) {
        List<Question> questionList = new ArrayList<>();
        ResultSet rs = null;
        try(Connection con = DAOFactory.getInstance().createConnection();
            PreparedStatement ps = con.prepareStatement(SQL_QUESTION_GET_ALL_BY_QUIZ_ID)) {
            ps.setInt(1,quizId);
            rs = ps.executeQuery();
            while (rs.next()){
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestion(rs.getString("question"));
                question.setQuizId(rs.getInt("quiz_id"));
                questionList.add(question);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
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
    public boolean insert(Question question) {
        ResultSet rs = null;
        try(Connection con = DAOFactory.getInstance().createConnection();
        PreparedStatement ps = con.prepareStatement(SQL_QUESTION_INSERT, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,question.getQuestion());
            ps.setInt(2,question.getQuizId());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                question.setId(rs.getInt(1));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }finally {
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
}
