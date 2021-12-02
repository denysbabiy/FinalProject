package com.epam.finalproject.db.dao.MySqlImpl;

import com.epam.finalproject.db.dao.UserDAO;
import com.epam.finalproject.db.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySqlUserDAO implements UserDAO {
    private static final Logger log = Logger.getLogger(MySqlUserDAO.class);

    private static final String SQL_USER_INSERT = "INSERT INTO quizdb.user values (default ,?,?,?,default ,?,?,default)";
    private static final String SQL_USER_SELECT_BY_EMAIL = "SELECT * FROM quizdb.user WHERE email = ? ";
    private static final String SQL_USER_SELECT_BY_LOGIN = "SELECT * FROM quizdb.user WHERE login = ?";
    private static final String SQL_USER_UPDATE = "UPDATE user SET is_admin=?,surname=?,name=?,is_blocked=?,passhash=?,login=?,email=? WHERE id =?";
    private static final String SQL_USER_GET_ALL = "SELECT * FROM user";
    private static final String SQL_USER_SELECT_BY_ID = "SELECT * FROM user WHERE id = ?";
    private static final String SQL_USER_BLOCK_BY_ID = "UPDATE user SET is_blocked=1 where id=?";
    private static final String SQL_USER_UNBLOCK_BY_ID = "UPDATE user SET is_blocked=0 where id=?";
    private static final String SQL_USER_DELETE_BY_ID = "DELETE FROM user WHERE id=?";

    @Override
    public boolean insertUser(User user,Connection con) throws SQLException {
        ResultSet rs = null;
        try (PreparedStatement ps = con.prepareStatement(SQL_USER_INSERT, Statement.RETURN_GENERATED_KEYS)) {
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
            log.error(throwables.getMessage());
            throw new SQLException();
        }finally {
            close(rs);
        }
        return false;
    }


    @Override
    public boolean updateUser(User user,Connection con) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement(SQL_USER_UPDATE)){
            ps.setInt(1,user.getIsAdmin());
            ps.setString(2,user.getSurname());
            ps.setString(3,user.getName());
            ps.setInt(4,user.getIsBlocked());
            ps.setString(5,user.getPasshash());
            ps.setString(6,user.getLogin());
            ps.setString(7,user.getEmail());
            ps.setInt(8,user.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        }
        return true;
    }

    @Override
    public boolean blockUserById(int id, Connection con) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement(SQL_USER_BLOCK_BY_ID)){
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        }
        return true;
    }
    @Override
    public boolean unblockUserById(int id, Connection con) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement(SQL_USER_UNBLOCK_BY_ID)){
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        }
        return true;
    }

    @Override
    public boolean isUserExistByEmail(String email,Connection con) throws SQLException {
        ResultSet rs= null;
        try(PreparedStatement ps = con.prepareStatement(SQL_USER_SELECT_BY_EMAIL)) {
            ps.setString(1,email);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        }finally {
            close(rs);
        }
        return false;
    }
    @Override
    public boolean isUserExistByLogin(String login,Connection con) throws SQLException {
        ResultSet rs= null;
        try(PreparedStatement ps = con.prepareStatement(SQL_USER_SELECT_BY_LOGIN)) {
            ps.setString(1,login);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        }finally {
            close(rs);
        }
        return false;
    }

    @Override
    public boolean deleteUserById(int id, Connection con) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement(SQL_USER_DELETE_BY_ID)) {
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        }
        return true;
    }

    @Override
    public User getUserById(int id, Connection con) throws SQLException {
        User user = null;
        ResultSet rs = null;
        try(PreparedStatement ps = con.prepareStatement(SQL_USER_SELECT_BY_ID)) {
            ps.setInt(1,id);
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
            log.error(throwables.getMessage());
            throw new SQLException();
        }finally {
            close(rs);
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email,Connection con) throws SQLException {
        User user = null;
        ResultSet rs = null;
        try(PreparedStatement ps = con.prepareStatement(SQL_USER_SELECT_BY_EMAIL)) {
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
            log.error(throwables.getMessage());
            throw new SQLException();
        }finally {
            close(rs);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers(Connection con) throws SQLException {
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        try(PreparedStatement ps = con.prepareStatement(SQL_USER_GET_ALL)){
            rs = ps.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setPasshash(rs.getString("passhash"));
                user.setIsBlocked(rs.getInt("is_blocked"));
                user.setIsAdmin(rs.getInt("is_admin"));
                userList.add(user);
            }

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        }finally {
            close(rs);
        }
        return userList;
    }

    private void close(AutoCloseable rs) {
        try {
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
