package com.epam.finalproject.service;

import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.entity.Quiz;
import com.epam.finalproject.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class QuizService {
    private static final Logger log = Logger.getLogger(QuizService.class);
    private static QuizService instance;

    public static synchronized QuizService getInstance() {
        if (instance == null) {
            instance = new QuizService();
        }
        return instance;
    }

    private QuizService() {
    }

    public boolean insertQuiz(Quiz quiz) throws ServiceException {
        try(Connection con = DAOFactory.getInstance().createConnection()){
            return DAOFactory.getInstance().getQuizDAO().insertQuiz(quiz,con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Cannot insert quiz");
        }
    }
    public boolean updateQuiz(Quiz quiz) throws ServiceException {
        try(Connection con = DAOFactory.getInstance().createConnection()){
            return DAOFactory.getInstance().getQuizDAO().updateQuiz(quiz,con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Cannot update quiz");
        }

    }
    public Quiz getQuizById(int id) throws ServiceException {
        try(Connection con = DAOFactory.getInstance().createConnection()) {
            return DAOFactory.getInstance().getQuizDAO().getQuizById(id, con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Cannot update quiz");
        }
    }
    public List<Quiz> getAllQuizzesBySubject(int subjectId) throws ServiceException {
        try(Connection con = DAOFactory.getInstance().createConnection()) {
            return DAOFactory.getInstance().getQuizDAO().getAllQuizzesBySubject(subjectId, con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Cannot update quiz");
        }
    }
}
