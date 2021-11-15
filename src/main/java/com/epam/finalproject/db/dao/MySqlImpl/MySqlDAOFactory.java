package com.epam.finalproject.db.dao.MySqlImpl;

import com.epam.finalproject.db.connection_pool.DBConnectionPool;
import com.epam.finalproject.db.dao.*;
import com.epam.finalproject.exception.NoConnectionToDB;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * implementation DAOFactory for DBMS MySQL
 */
public class MySqlDAOFactory extends DAOFactory {
    @Override
    public Connection createConnection() {
        try {
            return DBConnectionPool.getConnection();
        } catch (SQLException e) {
            //log
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
}
