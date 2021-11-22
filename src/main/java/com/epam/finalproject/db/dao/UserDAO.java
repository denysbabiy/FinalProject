package com.epam.finalproject.db.dao;

import com.epam.finalproject.db.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    boolean insertUser(User user, Connection con) throws SQLException;
    boolean updateUser(User user);
    boolean blockUserById(int id);
    boolean isUserExistByEmail(String email,Connection con) throws SQLException;
    User getUserById(int id);
    User getUserByEmail(String email,Connection con) throws SQLException;
    List<User> getAllUsers();
    boolean isUserExistByLogin(String login, Connection con) throws SQLException;
}
