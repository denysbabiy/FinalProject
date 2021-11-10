package com.epam.finalproject.db.dao;

import com.epam.finalproject.db.entity.User;

import java.util.List;

public interface UserDAO {
    boolean insertUser(User user);
    boolean updateUser(User user);
    boolean blockUserById(int id);
    boolean isUserExist(String email);
    User getUserById(int id);
    User getUserByEmail(String email);
    List<User> getAllUsers();


}
