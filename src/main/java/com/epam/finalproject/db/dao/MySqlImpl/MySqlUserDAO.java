package com.epam.finalproject.db.dao.MySqlImpl;

import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.dao.UserDAO;
import com.epam.finalproject.db.entity.User;

import java.sql.*;
import java.util.List;


public class MySqlUserDAO implements UserDAO {
    private static final String SQL_USER_INSERT = "INSERT INTO quizdb.user values (default ,?,?,?,default ,?,?,default)";
    private static final String SQL_USER_SELECT_BY_EMAIL = "SELECT * FROM quizdb.user WHERE email = ? ";

    @Override
    public boolean insertUser(User user) {
        ResultSet rs = null;
        try (Connection con = DAOFactory.getInstance().createConnection();
             PreparedStatement ps = con.prepareStatement(SQL_USER_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPasshash());
            ps.setString(4, user.getName());
            ps.setString(5, user.getSurname());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                user.setId(rs.getInt(1));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean blockUserById(int id) {
        return false;
    }

    @Override
    public boolean isUserExist(String email) {
        ResultSet rs= null;
        try(Connection con = DAOFactory.getInstance().createConnection();
        PreparedStatement ps = con.prepareStatement(SQL_USER_SELECT_BY_EMAIL)) {
            ps.setString(1,email);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        ResultSet rs = null;
        try(Connection con = DAOFactory.getInstance().createConnection();
            PreparedStatement ps = con.prepareStatement(SQL_USER_SELECT_BY_EMAIL)) {
            ps.setString(1,email);
            rs = ps.executeQuery();
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setLogin(rs.getString("login"));
                user.setPasshash(rs.getString("passhash"));
                user.setIsBlocked(rs.getInt("is_blocked"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setIsAdmin(rs.getInt("is_admin"));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
