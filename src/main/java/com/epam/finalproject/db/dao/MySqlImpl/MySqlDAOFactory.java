package com.epam.finalproject.db.dao.MySqlImpl;

import com.epam.finalproject.db.connection_pool.DBConnectionPool;
import com.epam.finalproject.db.dao.*;
import com.epam.finalproject.exception.NoConnectionToDB;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * implementation DAOFactory for DBMS MySQL
 */
public class MySqlDAOFactory extends DAOFactory {
    private static final Logger log = Logger.getLogger(MySqlDAOFactory.class);
    @Override
    public Connection createConnection() {
        try {
            return DBConnectionPool.getConnection();
        } catch (SQLException e) {
            log.error("No connection to DATABASE");
            throw new NoConnectionToDB(e.getMessage());
        }
    }


    @Override
    public UserDAO getUserDAO() {
        return new MySqlUserDAO();
    }

    @Override
    public QuizDAO getQuizDAO() {
        return new MySqlQuizDAO();
    }

    @Override
    public SubjectDAO getSubjectDAO() {
        return new MySqlSubjectDAO();
    }

    @Override
    public QuestionDAO getQuestionDAO() {
        return new MySqlQuestionDAO();
    }

    @Override
    public QuizResultDAO getQuizResultDAO() {
        return new MySqlQuizResultDAO();
    }

    @Override
    public AnswerDAO getAnswerDAO() {
        return new MySqlAnswerDAO();
    }
}
