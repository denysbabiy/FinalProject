package com.epam.finalproject.service;

import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.dao.MySqlImpl.MySqlUserDAO;
import com.epam.finalproject.db.entity.User;
import com.epam.finalproject.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {
    private static final Logger log = Logger.getLogger(UserService.class);
    private static UserService instance;

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private UserService() {
    }
    public boolean insertUser(User user) throws ServiceException {
        try(Connection con = DAOFactory.getInstance().createConnection()){
            return DAOFactory.getInstance().getUserDAO().insertUser(user,con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Cannot insert user");
        }
    }
    public boolean isUserExistByEmailOrLogin(String email,String login) throws ServiceException {
        try(Connection con = DAOFactory.getInstance().createConnection()) {
            return (DAOFactory.getInstance().getUserDAO().isUserExistByEmail(email, con) ||
                    DAOFactory.getInstance().getUserDAO().isUserExistByLogin(login, con));
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Can't check if user exist by email and login");
        }
    }
    public boolean isUserExistByEmail(String email) throws ServiceException {
        try(Connection con = DAOFactory.getInstance().createConnection()) {
            return DAOFactory.getInstance().getUserDAO().isUserExistByEmail(email, con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Can't check if user exist by email");
        }
    }

    public User getUserByEmail(String email) throws ServiceException {
        try(Connection con = DAOFactory.getInstance().createConnection()){
            return DAOFactory.getInstance().getUserDAO().getUserByEmail(email,con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Can't get user by email");
        }
    }
}
