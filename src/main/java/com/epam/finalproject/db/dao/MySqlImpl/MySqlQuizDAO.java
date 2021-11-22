package com.epam.finalproject.db.dao.MySqlImpl;

import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.dao.QuizDAO;
import com.epam.finalproject.db.entity.Quiz;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlQuizDAO implements QuizDAO {
    private static final Logger log = Logger.getLogger(MySqlQuizDAO.class);
    private static final String SQL_QUIZ_INSERT = "INSERT INTO quizdb.quiz VALUES (default ,?,?,?,?)";
    private static final String SQL_QUIZ_GET_ALL_BY_SUBJECT_ID = "SELECT * FROM quizdb.quiz WHERE subject_id=?";
    private static final String SQL_QUIZ_GET_BY_ID = "SELECT * FROM quiz where id=?";
    private static final String SQL_QUIZ_UPDATE = "UPDATE quiz SET name=?,complexity=?,time=? WHERE id =?";

    @Override
    public boolean insertQuiz(Quiz quiz,Connection con) throws SQLException {
        int changes = 0;
        ResultSet rs = null;
        try (PreparedStatement ps = con.prepareStatement(SQL_QUIZ_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, quiz.getName());
            ps.setInt(2, quiz.getComplexity());
            ps.setInt(3, quiz.getTime());
            ps.setInt(4, quiz.getSubjectId());
            changes = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                quiz.setId(rs.getInt(1));
            }
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
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
    public boolean updateQuiz(Quiz quiz,Connection con) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement(SQL_QUIZ_UPDATE)) {
            ps.setString(1, quiz.getName());
            ps.setInt(2,quiz.getComplexity());
            ps.setInt(3,quiz.getTime());
            ps.setInt(4,quiz.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        }
        return true;
    }

    @Override
    public Quiz getQuizById(int id,Connection con) throws SQLException {
        Quiz quiz = null;
        ResultSet resultSet = null;
        try(PreparedStatement ps = con.prepareStatement(SQL_QUIZ_GET_BY_ID)) {
            ps.setInt(1,id);
            resultSet = ps.executeQuery();
            if(resultSet.next()){
                quiz = new Quiz();
                quiz.setId(resultSet.getInt("id"));
                quiz.setName(resultSet.getString("name"));
                quiz.setComplexity(resultSet.getInt("complexity"));
                quiz.setTime(resultSet.getInt("time"));
                quiz.setSubjectId(resultSet.getInt("subject_id"));
            }
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();

        }finally {
            close(resultSet);
        }
        return quiz;
    }

    @Override
    public int getQuizCountBySubject(int subjectId) {
        return 0;
    }

    @Override
    public List<Quiz> getAllQuizzesBySubject(int subjectId,Connection con) throws SQLException {
        List<Quiz> quizList = new ArrayList<>();
        ResultSet rs = null;
        try(PreparedStatement ps = con.prepareStatement(SQL_QUIZ_GET_ALL_BY_SUBJECT_ID)) {
            ps.setInt(1,subjectId);
            rs = ps.executeQuery();
            while (rs.next()){
                Quiz quiz = new Quiz();
                quiz.setId(rs.getInt("id"));
                quiz.setName(rs.getString("name"));
                quiz.setComplexity(rs.getInt("complexity"));
                quiz.setTime(rs.getInt("time"));
                quiz.setSubjectId(rs.getInt("subject_id"));
                quizList.add(quiz);
            }

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
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
