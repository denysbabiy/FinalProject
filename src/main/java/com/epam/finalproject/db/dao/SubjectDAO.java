package com.epam.finalproject.db.dao;

import com.epam.finalproject.db.entity.Subject;

import java.sql.Connection;
import java.util.List;

public interface SubjectDAO {
    boolean insertSubject(Subject subject, Connection con);
    boolean insertSubjectDescription(Subject subject, String lang, Connection con);
    boolean updateSubject(Subject subject, String lang, Connection con);
    boolean deleteSubjectById(int id);
    Subject getSubjectById(int id,String language);
    int getSubjectCount();
    List<Subject> getAllSubjects(String language);
}
