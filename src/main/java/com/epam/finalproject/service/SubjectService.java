package com.epam.finalproject.service;

import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.entity.Subject;
import com.epam.finalproject.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SubjectService {
    private static final Logger log = Logger.getLogger(SubjectService.class);
    private static SubjectService instance;

    public static synchronized SubjectService getInstance() {
        if (instance == null) {
            instance = new SubjectService();
        }
        return instance;
    }

    private SubjectService() {
    }

    public boolean addNewSubject(Subject subjectEn, Subject subjectUa) throws ServiceException {
        Connection con = null;
        try {
            con = DAOFactory.getInstance().createConnection();
            con.setAutoCommit(false);
            DAOFactory.getInstance().getSubjectDAO().insertSubject(subjectEn, con);
            subjectUa.setId(subjectEn.getId());
            DAOFactory.getInstance().getSubjectDAO().insertSubjectDescription(subjectEn, "en", con);
            DAOFactory.getInstance().getSubjectDAO().insertSubjectDescription(subjectUa, "ua", con);
            con.commit();

        } catch (SQLException throwables) {
            rollback(con);
            log.error(throwables.getMessage());
            throw new ServiceException("Cannot add new subject");
        } finally {
            setAutoCommitTrue(con);
            close(con);
        }
        return true;
    }

    public List<Subject> getAllSubjects(String language) throws ServiceException {
        try(Connection con = DAOFactory.getInstance().createConnection()){
            return DAOFactory.getInstance().getSubjectDAO().getAllSubjects(language,con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Can't get all subjects");
        }
    }
    public Subject getSubjectById(int id,String lang) throws ServiceException {
        try(Connection con = DAOFactory.getInstance().createConnection()){
            return DAOFactory.getInstance().getSubjectDAO().getSubjectById(id,lang,con);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
            throw new ServiceException("Can't get subject by id");
        }
    }
    public boolean deleteSubjectById(int id) throws ServiceException {
        try(Connection con = DAOFactory.getInstance().createConnection()){
            return DAOFactory.getInstance().getSubjectDAO().deleteSubjectById(id,con);
        }catch (SQLException throwables){
            log.error(throwables.getMessage());
            throw new ServiceException("Can't delete subject by id");
        }
    }

    private void close(AutoCloseable con) {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void rollback(Connection con) {
        try {
            con.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setAutoCommitTrue(Connection con) {
        try {
            if (con != null) {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
