package com.epam.finalproject.db.dao.MySqlImpl;

import com.epam.finalproject.db.dao.AnswerDAO;
import com.epam.finalproject.db.entity.Answer;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlAnswerDAO implements AnswerDAO {
    private static final Logger log = Logger.getLogger(MySqlAnswerDAO.class);

    private static final String SQL_ANSWER_INSERT = "INSERT INTO answer VALUES (default ,?,?,?)";
    private static final String SQL_ANSWER_GET_ALL_BY_QUESTION_ID = "SELECT * FROM answer WHERE question_id =? ORDER BY RAND()";
    private static final String SQL_ANSWER_UPDATE = "UPDATE answer SET answer=?,is_correct=? WHERE id=?";
    private static final String SQL_ANSWER_DELETE_BY_ID = "DELETE FROM answer WHERE id=?";
    private static final String SQL_ANSWER_GET_BY_ID = "SELECT * FROM answer WHERE id=?";
    private static final String SQL_ANSWER_GET_ALL_CORRECT_ANSWER_BY_QUIZ_ID = "SELECT answer.id,answer,is_correct,question_id FROM answer\n" +
            "join question q on answer.question_id = q.id\n" +
            "join quiz q2 on q.quiz_id = q2.id where q2.id=? AND is_correct=1";
    private static final String SQL_ANSWER_GET_ALL_CORRECT_ANSWER_BY_QUESTION_ID = "SELECT answer.id,answer,is_correct,question_id FROM answer\n"+
                       "join question q on answer.question_id = q.id\n" +
                        "join quiz q2 on q.quiz_id = q2.id where q.id=? AND is_correct=1";

    @Override
    public boolean insert(Answer answer, Connection con) throws SQLException {
        ResultSet rs = null;
        try(PreparedStatement ps = con.prepareStatement(SQL_ANSWER_INSERT, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1,answer.getAnswer());
            ps.setInt(2,answer.getIsCorrect());
            ps.setInt(3,answer.getQuestionId());

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                answer.setId(rs.getInt(1));
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
    public List<Answer> getAllAnswersByQuestionId(int id, Connection con) throws SQLException {
        List<Answer> answers = new ArrayList<>();
        ResultSet rs = null;
        try(PreparedStatement ps = con.prepareStatement(SQL_ANSWER_GET_ALL_BY_QUESTION_ID)) {
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
                Answer answer = new Answer();
                answer.setId(rs.getInt("id"));
                answer.setQuestionId(rs.getInt("question_id"));
                answer.setIsCorrect(rs.getInt("is_correct"));
                answer.setAnswer(rs.getString("answer"));
                answers.add(answer);
            }
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        }finally {
            close(rs);
        }
        return answers;
    }

    @Override
    public boolean update(Answer answer, Connection con) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement(SQL_ANSWER_UPDATE)){
            ps.setString(1,answer.getAnswer());
            ps.setInt(2,answer.getIsCorrect());
            ps.setInt(3,answer.getId());
            ps.executeUpdate();
        }catch (SQLException throwables){
            log.error(throwables.getMessage());
            throw new SQLException();
        }
        return true;
    }

    @Override
    public boolean deleteById(int id, Connection con) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement(SQL_ANSWER_DELETE_BY_ID)){
            ps.setInt(1,id);
            ps.executeUpdate();

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        }
        return true;
    }

    @Override
    public Answer getAnswerById(int id, Connection con) throws SQLException {
        Answer answer = new Answer();
        ResultSet rs = null;
        try(PreparedStatement ps = con.prepareStatement(SQL_ANSWER_GET_BY_ID)){
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if(rs.next()){
                answer.setId(rs.getInt("id"));
                answer.setAnswer(rs.getString("answer"));
                answer.setIsCorrect(rs.getInt("is_correct"));
                answer.setQuestionId(rs.getInt("question_id"));
            }
        }catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        }finally {
            close(rs);
        }
        return answer;
    }

    @Override
    public List<Answer> getAllCorrectAnswersByQuizId(int id, Connection con) throws SQLException {
        List<Answer> answerList = new ArrayList<>();
        ResultSet rs = null;
        try(PreparedStatement ps = con.prepareStatement(SQL_ANSWER_GET_ALL_CORRECT_ANSWER_BY_QUIZ_ID)){
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
                Answer answer = new Answer();
                answer.setId(rs.getInt("id"));
                answer.setAnswer(rs.getString("answer"));
                answer.setIsCorrect(rs.getInt("is_correct"));
                answer.setQuestionId(rs.getInt("question_id"));
                answerList.add(answer);
            }
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        }finally {
            close(rs);
        }
        return answerList;
    }

    @Override
    public List<Answer> getAllCorrectAnswersByQuestionId(int questionId, Connection con) throws SQLException {
        List<Answer> answerList = new ArrayList<>();
        ResultSet rs = null;
        try(PreparedStatement ps = con.prepareStatement(SQL_ANSWER_GET_ALL_CORRECT_ANSWER_BY_QUESTION_ID)){
            ps.setInt(1,questionId);
            rs = ps.executeQuery();
            while (rs.next()){
                Answer answer = new Answer();
                answer.setId(rs.getInt("id"));
                answer.setAnswer(rs.getString("answer"));
                answer.setIsCorrect(rs.getInt("is_correct"));
                answer.setQuestionId(rs.getInt("question_id"));
                answerList.add(answer);
            }
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        }finally {
            close(rs);
        }
        return answerList;
    }


    private void close(AutoCloseable rs) {
        try {
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
