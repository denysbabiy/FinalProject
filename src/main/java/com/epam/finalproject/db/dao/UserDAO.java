package com.epam.finalproject.db.dao;

import com.epam.finalproject.db.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    boolean insertUser(User user, Connection con) throws SQLException;
    boolean updateUser(User user, Connection con) throws SQLException;
    boolean blockUserById(int id, Connection con) throws SQLException;
    boolean unblockUserById(int id, Connection con) throws SQLException;
    boolean isUserExistByEmail(String email,Connection con) throws SQLException;
    User getUserById(int id, Connection con) throws SQLException;
    User getUserByEmail(String email,Connection con) throws SQLException;
    List<User> getAllUsers(Connection con) throws SQLException;
    boolean isUserExistByLogin(String login, Connection con) throws SQLException;

    boolean deleteUserById(int id, Connection con) throws SQLException;
}
