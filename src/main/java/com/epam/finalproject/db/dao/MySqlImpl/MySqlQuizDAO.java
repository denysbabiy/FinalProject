package com.epam.finalproject.db.dao.MySqlImpl;

import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.dao.QuizDAO;
import com.epam.finalproject.db.entity.Quiz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlQuizDAO implements QuizDAO {
    private static final String SQL_QUIZ_INSERT = "INSERT INTO quizdb.quiz VALUES (default ,?,?,?,?,default ,?)";
    private static final String SQL_QUIZ_GET_ALL_BY_SUBJECT_ID = "SELECT * FROM quizdb.quiz WHERE subject_id=?";

    @Override
    public boolean insertQuiz(Quiz quiz) {
        int changes = 0;
        ResultSet rs = null;
        try (Connection con = DAOFactory.getInstance().createConnection();
             PreparedStatement ps = con.prepareStatement(SQL_QUIZ_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, quiz.getNameUa());
            ps.setString(2, quiz.getNameEn());
            ps.setInt(3, quiz.getComplexity());
            ps.setInt(4, quiz.getTime());
            ps.setInt(5, quiz.getSubjectId());
            changes = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                quiz.setId(rs.getInt("id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(rs);
        }
        return changes > 0;
    }



    @Override
    public boolean deleteQuizById(int id) {
        return false;
    }

    @Override
    public boolean updateQuiz(Quiz quiz) {
        return false;
    }

    @Override
    public Quiz getQuizById(int id) {
        return null;
    }

    @Override
    public int getQuizCountBySubject(int subjectId) {
        return 0;
    }

    @Override
    public List<Quiz> getAllQuizzesBySubject(int subjectId) {
        List<Quiz> quizList = new ArrayList<>();
        ResultSet rs = null;
        try(Connection con = DAOFactory.getInstance().createConnection();
        PreparedStatement ps = con.prepareStatement(SQL_QUIZ_GET_ALL_BY_SUBJECT_ID)) {
            ps.setInt(1,subjectId);
            rs = ps.executeQuery();
            while (rs.next()){
                Quiz quiz = new Quiz();
                quiz.setId(rs.getInt("id"));
                quiz.setNameEn(rs.getString("name_en"));
                quiz.setNameUa(rs.getString("name_ua"));
                quiz.setComplexity(rs.getInt("complexity"));
                quiz.setTime(rs.getInt("time"));
                quiz.setRequestQuantity(rs.getInt("request_quantity"));
                quiz.setSubjectId(rs.getInt("subject_id"));
                quizList.add(quiz);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            close(rs);
        }
        return quizList;
    }


    private void close(AutoCloseable rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
