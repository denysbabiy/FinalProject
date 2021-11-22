package com.epam.finalproject.service;

import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.entity.Answer;
import com.epam.finalproject.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AnswerService {
    private static final Logger log = Logger.getLogger(AnswerService.class);
    private static AnswerService instance;

    public static synchronized AnswerService getInstance() {
        if (instance == null) {
            instance = new AnswerService();
        }
        return instance;
    }

    private AnswerService() {
    }

    public String insertAndReturnId(int questionId) throws ServiceException {
        Answer answer;
        try (Connection con = DAOFactory.getInstance().createConnection()) {
            answer = new Answer();
            answer.setAnswer("delete me");
            answer.setIsCorrect(-1);
            answer.setQuestionId(questionId);
            DAOFactory.getInstance().getAnswerDAO().insert(answer, con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Cannot insert answer");
        }

        return String.valueOf(answer.getId());
    }

    public boolean deleteById(int id) throws ServiceException {
        try (Connection con = DAOFactory.getInstance().createConnection()) {
            DAOFactory.getInstance().getAnswerDAO().deleteById(id, con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Cannot delete answer by id");
        }
        return true;

    }

    public Answer getAnswerById(int id) throws ServiceException {
        try (Connection con = DAOFactory.getInstance().createConnection()) {
            return DAOFactory.getInstance().getAnswerDAO().getAnswerById(id, con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Cannot get answer by id");
        }
    }

    public List<Answer> getAllCorrectAnswersByQuizId(int id) throws ServiceException {
        try (Connection con = DAOFactory.getInstance().createConnection()) {
            return DAOFactory.getInstance().getAnswerDAO().getAllCorrectAnswersByQuizId(id, con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Cannot get all correct answers by quiz id");
        }
    }
    public List<Answer> getAllAnswersByQuestionId(int questionId) throws ServiceException {
        try(Connection connection = DAOFactory.getInstance().createConnection()){
            return DAOFactory.getInstance().getAnswerDAO().getAllAnswersByQuestionId(questionId,connection);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Cannot get all answers by question id");
        }
    }

    public List<Answer> getAllCorrectAnswersByQuestionId(int questionId) throws ServiceException {
        try (Connection con = DAOFactory.getInstance().createConnection()) {
            return DAOFactory.getInstance().getAnswerDAO().getAllCorrectAnswersByQuestionId(questionId, con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Cannot get all correct answers by question id");
        }
    }
}
