package com.epam.finalproject.service;

import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.entity.User;
import com.epam.finalproject.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
        try (Connection con = DAOFactory.getInstance().createConnection()) {
            return DAOFactory.getInstance().getUserDAO().insertUser(user, con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Cannot insert user");
        }
    }

    public boolean isUserExistByEmailOrLogin(String email, String login) throws ServiceException {
        try (Connection con = DAOFactory.getInstance().createConnection()) {
            return (DAOFactory.getInstance().getUserDAO().isUserExistByEmail(email, con) ||
                    DAOFactory.getInstance().getUserDAO().isUserExistByLogin(login, con));
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Can't check if user exist by email and login");
        }
    }

    public boolean isUserExistByEmail(String email) throws ServiceException {
        try (Connection con = DAOFactory.getInstance().createConnection()) {
            return DAOFactory.getInstance().getUserDAO().isUserExistByEmail(email, con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Can't check if user exist by email");
        }
    }

    public boolean isUserExistByLogin(String login) throws ServiceException {
        try (Connection con = DAOFactory.getInstance().createConnection()) {
            return DAOFactory.getInstance().getUserDAO().isUserExistByLogin(login, con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Can't check if user exist by login");
        }
    }

    public User getUserByEmail(String email) throws ServiceException {
        try (Connection con = DAOFactory.getInstance().createConnection()) {
            return DAOFactory.getInstance().getUserDAO().getUserByEmail(email, con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Can't get user by email");
        }
    }

    public void updateUser(User userEdited) throws ServiceException {
        try (Connection con = DAOFactory.getInstance().createConnection()) {
            DAOFactory.getInstance().getUserDAO().updateUser(userEdited, con);

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Can't update user");
        }
    }

    public List<User> getAllUsers() throws ServiceException {
        try (Connection con = DAOFactory.getInstance().createConnection()) {
            return DAOFactory.getInstance().getUserDAO().getAllUsers(con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Can't get all users");
        }
    }

    public User getUserById(int id) throws ServiceException {
        try(Connection con = DAOFactory.getInstance().createConnection()){
            return DAOFactory.getInstance().getUserDAO().getUserById(id,con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Can't get user by id");
        }
    }

    public boolean deleteUserById(int id) throws ServiceException {
        try(Connection con = DAOFactory.getInstance().createConnection()){
            return DAOFactory.getInstance().getUserDAO().deleteUserById(id,con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Can't delete user by id");
        }
    }

    public boolean blockUserById(int id) throws ServiceException {
        try(Connection con = DAOFactory.getInstance().createConnection()){
            return DAOFactory.getInstance().getUserDAO().blockUserById(id,con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Can't block user by id");
        }
    }

    public boolean unblockUserById(int id) throws ServiceException {
        try(Connection con = DAOFactory.getInstance().createConnection()){
            return DAOFactory.getInstance().getUserDAO().unblockUserById(id,con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Can't unblock user by id");
        }
    }
}
