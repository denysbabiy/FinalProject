package com.epam.finalproject.service;

import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.entity.Answer;
import com.epam.finalproject.db.entity.Question;
import com.epam.finalproject.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class QuestionService {
    private static final Logger log = Logger.getLogger(QuestionService.class);
    private static QuestionService instance;
    public static synchronized QuestionService getInstance(){
        if(instance == null){
            instance = new QuestionService();
        }
        return instance;
    }
    private QuestionService(){
    }

    public boolean addNewQuestion(Question question) throws ServiceException {
        Connection con = null;
        try {
            con = DAOFactory.getInstance().createConnection();
            con.setAutoCommit(false);
            DAOFactory.getInstance().getQuestionDAO().insert(question, con);
            for (Answer answer : question.getAnswers()) {
                DAOFactory.getInstance().getAnswerDAO().insert(answer, con);
            }
            con.commit();
        } catch (SQLException throwables) {
            rollback(con);
            log.error(throwables.getMessage());
            throw new ServiceException("Cannot add new question");
        }finally {
            setAutoCommitTrue(con);
            close(con);
        }
        return true;
    }
    public List<Question> getAllQuestionsByQuizId(int id) throws ServiceException {
        List<Question> questions;
        try(Connection con = DAOFactory.getInstance().createConnection()){
            questions = DAOFactory.getInstance().getQuestionDAO().getAllQuestionsByQuizId(id,con);
            for (Question question:questions) {
                question.setAnswers(DAOFactory.getInstance().getAnswerDAO().getAllAnswersByQuestionId(question.getId(),con));
            }
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Cannot get all questions by quiz id");
        }
        return questions;
    }
    public Question getQuestionById(int id) throws ServiceException {
        Question question = null;
        try(Connection con = DAOFactory.getInstance().createConnection()){
            question = DAOFactory.getInstance().getQuestionDAO().getQuestionById(id,con);
            question.setAnswers(DAOFactory.getInstance().getAnswerDAO().getAllAnswersByQuestionId(question.getId(),con));
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Cannot get question by id");
        }
        return question;
    }

    public boolean updateQuestion(Question question) throws ServiceException {
        Connection con = null;
        try {
            con = DAOFactory.getInstance().createConnection();
            con.setAutoCommit(false);
            DAOFactory.getInstance().getQuestionDAO().update(question,con);
            for (Answer answer: question.getAnswers()) {
                DAOFactory.getInstance().getAnswerDAO().update(answer,con);
            }
            con.commit();

        } catch (SQLException throwables) {
            rollback(con);
            setAutoCommitTrue(con);
            log.error(throwables.getMessage());
            throw new ServiceException("Cannot update question");
        }finally {
            close(con);
        }
        return true;
    }

    public boolean deleteQuestionById(int questionId) throws ServiceException {
        try(Connection con = DAOFactory.getInstance().createConnection()){
            DAOFactory.getInstance().getQuestionDAO().deleteQuestionById(questionId,con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Cannot delete question by id");
        }
        return true;
    }



    private void close(AutoCloseable con) {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void rollback(Connection con) {
        try {
            con.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setAutoCommitTrue(Connection con) {
        try {
            if (con != null) {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
