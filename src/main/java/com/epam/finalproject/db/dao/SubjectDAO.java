package com.epam.finalproject.db.dao;

import com.epam.finalproject.db.entity.Subject;

import java.util.List;

public interface SubjectDAO {
    boolean insertSubject(Subject subject);
    boolean updateSubject(Subject subject);
    boolean deleteSubjectById(int id);
    Subject getSubjectById(int id);
    int getSubjectCount();
    List<Subject> getAllSubjects();
}
