package com.epam.finalproject.db.dao.MySqlImpl;

import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.dao.SubjectDAO;
import com.epam.finalproject.db.entity.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlSubjectDAO implements SubjectDAO {
    private static final String SQL_SUBJECTS_GET_ALL = "SELECT * FROM quizdb.subject";
    private static final String SQL_SUBJECTS_COUNT = "SELECT COUNT(*) FROM quizdb.subject";
    private static final String SQL_SUBJECTS_GET_BY_ID = "SELECT * FROM quizdb.subject WHERE id=?";

    @Override
    public boolean insertSubject(Subject subject) {
        return false;
    }

    @Override
    public boolean updateSubject(Subject subject) {
        return false;
    }

    @Override
    public boolean deleteSubjectById(int id) {
        return false;
    }

    @Override
    public Subject getSubjectById(int id) {
        ResultSet rs = null;
        Subject subject = null;
        try(Connection con = DAOFactory.getInstance().createConnection();
            PreparedStatement ps = con.prepareStatement(SQL_SUBJECTS_GET_BY_ID)) {
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if(rs.next()){
                subject = new Subject();
                subject.setId(rs.getInt("id"));
                subject.setNameUa(rs.getString("name_ua"));
                subject.setNameEn(rs.getString("name_en"));
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
        return subject;
    }

    @Override
    public int getSubjectCount() {
        int count = 0;
        try(Connection con = DAOFactory.getInstance().createConnection();
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_SUBJECTS_COUNT)) {
            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> subjectList = new ArrayList<>();
        try(Connection con = DAOFactory.getInstance().createConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SUBJECTS_GET_ALL)) {
            while (resultSet.next()){
                Subject subject = new Subject();
                subject.setId(resultSet.getInt("id"));
                subject.setNameUa(resultSet.getString("name_ua"));
                subject.setNameEn(resultSet.getString("name_en"));
                subjectList.add(subject);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return subjectList;
    }
}
