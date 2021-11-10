package com.epam.finalproject;


import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.dao.UserDAO;
import com.epam.finalproject.db.entity.User;

import java.sql.Connection;
import java.util.ResourceBundle;

public class Test {
    public static void main(String[] args) {
        int count = DAOFactory.getInstance().getSubjectDAO().getSubjectCount();
        System.out.println(count);



    }
}
