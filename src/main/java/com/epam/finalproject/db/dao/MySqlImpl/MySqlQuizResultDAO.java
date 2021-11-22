package com.epam.finalproject.db.dao.MySqlImpl;

import com.epam.finalproject.db.dao.QuizResultDAO;
import com.epam.finalproject.db.entity.QuizResult;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlQuizResultDAO implements QuizResultDAO {
    private static final Logger log = Logger.getLogger(MySqlQuizResultDAO.class);

    private static final String SQL_QUIZRESULT_INSERT = "INSERT INTO quiz_result VALUES (default ,?,default ,?,?)";
    private static final String SQL_QUIZRESULTS_GET_BY_USERID_AND_QUIZID = "SELECT * FROM quiz_result WHERE user_id=? AND quiz_id=? ORDER BY create_time desc ";

    @Override
    public boolean insert(QuizResult quizResult, Connection con) throws SQLException {
        ResultSet rs = null;
        try (PreparedStatement ps = con.prepareStatement(SQL_QUIZRESULT_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDouble(1, quizResult.getResult());
            ps.setInt(2, quizResult.getUserId());
            ps.setInt(3, quizResult.getQuizId());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                quizResult.setId(rs.getInt(1));
            }

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        }finally {
            close(rs);
        }
        return true;
    }

    @Override
    public List<QuizResult> getQuizResults(int id, int quizId, Connection connection) throws SQLException {
        ResultSet rs = null;
        List<QuizResult> quizResults = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(SQL_QUIZRESULTS_GET_BY_USERID_AND_QUIZID)) {
            ps.setInt(1,id);
            ps.setInt(2,quizId);
            rs = ps.executeQuery();
            while (rs.next()){
                QuizResult quizResult = new QuizResult();
                quizResult.setId(rs.getInt("id"));
                quizResult.setResult(rs.getDouble("result"));
                quizResult.setCreate_time(rs.getTime("create_time"));
                quizResult.setQuizId(rs.getInt("quiz_id"));
                quizResult.setUserId(rs.getInt("user_id"));
                quizResults.add(quizResult);
            }
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        }finally {
            close(rs);
        }
        return quizResults;
    }

    private void close(AutoCloseable rs) {
        try {
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
