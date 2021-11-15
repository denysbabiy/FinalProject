package com.epam.finalproject.db.dao;

import com.epam.finalproject.exception.DAOFactoryIMPLClassNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.ResourceBundle;

public abstract class DAOFactory{
    /**
     * private field which hold the DAOFactory object
     */
    private static DAOFactory instance;
    private static String DAOFactoryFullQualifiedName = ResourceBundle.getBundle("DAOFactoryFCN")
            .getString("dao.fullQualifiedName"); // by default take it from resources

    public static void setDAOFactoryFullQualifiedName(String DAOFactoryFullQualifiedName) {
        instance = null;
        DAOFactory.DAOFactoryFullQualifiedName = DAOFactoryFullQualifiedName;
    }

    public static synchronized DAOFactory getInstance(){
        if(instance == null){
            try {
                Class<?> clazz = Class.forName(DAOFactory.DAOFactoryFullQualifiedName);
                instance = (DAOFactory) clazz.getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                //log
                throw new DAOFactoryIMPLClassNotFoundException();
            }
        }
        return instance;
    }

    public abstract Connection createConnection();
    public abstract UserDAO getUserDAO();
    public abstract QuizDAO getQuizDAO();
    public abstract SubjectDAO getSubjectDAO();
    public abstract QuestionDAO getQuestionDAO();
}
