package com.epam.finalproject.web.validation;


import javax.servlet.http.HttpServletRequest;

public class EditPassValidation {
    public static boolean isPassValid(HttpServletRequest request) {
        if(request.getParameter("new-pass").isEmpty() ||request.getParameter("new-pass").length()>45){
            return false;
        }
        return true;
    }
}
