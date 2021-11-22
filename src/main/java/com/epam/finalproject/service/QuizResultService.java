package com.epam.finalproject.service;

import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.entity.QuizResult;
import com.epam.finalproject.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class QuizResultService {
    private static final Logger log = Logger.getLogger(QuizResultService.class);

    private static QuizResultService instance;

    public static synchronized QuizResultService getInstance() {
        if (instance == null) {
            instance = new QuizResultService();
        }
        return instance;
    }

    private QuizResultService() {
    }

    public boolean insertNewQuizResult(QuizResult quizResult) throws ServiceException {
        try(Connection connection = DAOFactory.getInstance().createConnection()) {
            return DAOFactory.getInstance().getQuizResultDAO().insert(quizResult,connection);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Cannot insert quiz-result");
        }
    }

    public List<QuizResult> getQuizResults(int id, int quizId) throws ServiceException {
        try(Connection connection = DAOFactory.getInstance().createConnection()){
            return DAOFactory.getInstance().getQuizResultDAO().getQuizResults(id,quizId,connection);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Cannot get quiz-results");
        }
    }
}
