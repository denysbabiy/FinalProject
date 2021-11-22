package com.epam.finalproject.db.dao;

import com.epam.finalproject.db.entity.Subject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SubjectDAO {
    boolean insertSubject(Subject subject, Connection con) throws SQLException;
    boolean insertSubjectDescription(Subject subject, String lang, Connection con) throws SQLException;
    boolean updateSubject(Subject subject, String lang, Connection con) throws SQLException;
    boolean deleteSubjectById(int id);
    Subject getSubjectById(int id,String language,Connection con) throws SQLException;
    int getSubjectCount() throws SQLException;
    List<Subject> getAllSubjects(String language,Connection con) throws SQLException;
}
