package com.epam.finalproject.db.dao.MySqlImpl;

import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.dao.SubjectDAO;
import com.epam.finalproject.db.entity.Subject;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlSubjectDAO implements SubjectDAO {
    private static final Logger log = Logger.getLogger(MySqlSubjectDAO.class);
    private static final String SQL_SUBJECTS_GET_ALL = "SELECT subject.id,name,description FROM subject\n" +
            "join subject_description sd on subject.id = sd.subject_id\n" +
            "join language l on l.id = sd.language_id where short_name = ?;";
    private static final String SQL_SUBJECTS_COUNT = "SELECT COUNT(*) FROM quizdb.subject";
    private static final String SQL_SUBJECTS_GET_BY_ID = "SELECT subject.id,name,description FROM subject\n" +
            "join subject_description sd on subject.id = sd.subject_id\n" +
            "join language l on l.id = sd.language_id where subject.id = ? and short_name = ?;";
    public static final String SQL_SUBJECT_INSERT = "INSERT INTO subject VALUES (default)";
    private static final String SQL_SUBJECT_DELETE_BY_ID = "DELETE FROM subject WHERE id=?";

    @Override
    public boolean insertSubject(Subject subject,Connection con) throws SQLException {
        Statement statement = null;
        ResultSet rs = null;
        try{
            statement = con.createStatement();
            statement.executeUpdate(SQL_SUBJECT_INSERT,Statement.RETURN_GENERATED_KEYS);
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                subject.setId(rs.getInt(1));
            }
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        }
        return true;

    }

    @Override
    public boolean insertSubjectDescription(Subject subject, String lang, Connection con) throws SQLException {
        ResultSet rs = null;
        //ResultSet lang_id = null;
        PreparedStatement ps = null;
        Statement statement = null;
        //make it transaction
        //close connections statements and resultSets
        try {
            ps = con.prepareStatement("INSERT INTO subject_description VALUES ((SELECT id from language where short_name=?),?,?,?)");
            ps.setString(1,lang);
            ps.setInt(2, subject.getId());
            ps.setString(3, subject.getName());
            ps.setString(4, subject.getDescription());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        return true;
    }

    @Override
    public boolean updateSubject(Subject subject,String lang,Connection con) throws SQLException {

        try(PreparedStatement ps = con.prepareStatement("UPDATE subject_description SET name=?,description=? " +
                "WHERE subject_id=? and language_id = (SELECT id from language where language.short_name = ?)")){
            ps.setString(1,subject.getName());
            ps.setString(2,subject.getDescription());
            ps.setInt(3,subject.getId());
            ps.setString(4,lang);
            ps.executeUpdate();

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        }

        return true;
    }

    @Override
    public boolean deleteSubjectById(int id,Connection con) throws SQLException {
        try(PreparedStatement ps = con.prepareStatement(SQL_SUBJECT_DELETE_BY_ID)){
            ps.setInt(1,id);
            ps.executeUpdate();

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        }
        return true;
    }

    @Override
    public Subject getSubjectById(int id, String language,Connection con) throws SQLException {
        ResultSet rs = null;
        Subject subject = null;
        try (PreparedStatement ps = con.prepareStatement(SQL_SUBJECTS_GET_BY_ID)) {
            ps.setInt(1, id);
            ps.setString(2, language);
            rs = ps.executeQuery();
            if (rs.next()) {
                subject = new Subject();
                subject.setId(rs.getInt("id"));
                subject.setName(rs.getString("name"));
                subject.setDescription(rs.getString("description"));
            }

        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return subject;
    }

    @Override
    public int getSubjectCount() throws SQLException {
        int count = 0;
        try (Connection con = DAOFactory.getInstance().createConnection();
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SUBJECTS_COUNT)) {
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        }
        return count;
    }

    @Override
    public List<Subject> getAllSubjects(String language, Connection con) throws SQLException {
        List<Subject> subjectList = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = con.prepareStatement(SQL_SUBJECTS_GET_ALL)) {
            statement.setString(1, language);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setId(resultSet.getInt("id"));
                subject.setName(resultSet.getString("name"));
                subject.setDescription(resultSet.getString("description"));
                subjectList.add(subject);
            }
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new SQLException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return subjectList;
    }
}
