package com.epam.finalproject.web.validation;

import com.epam.finalproject.EntityFields;

import javax.servlet.http.HttpServletRequest;

public class RegistrationValidator {
    private static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$";

    private RegistrationValidator(){

    }
    public static boolean isValidUserParameters(HttpServletRequest request){

        if(request.getParameter(EntityFields.USER_LOGIN).isEmpty() || request.getParameter(EntityFields.USER_LOGIN).length()>45){
            return false;
        }
        if(request.getParameter(EntityFields.USER_NAME).isEmpty() ||request.getParameter(EntityFields.USER_NAME).length()>45){
            return false;
        }
        if(request.getParameter(EntityFields.USER_SURNAME).isEmpty() ||request.getParameter(EntityFields.USER_SURNAME).length()>45){
            return false;
        }
        if(request.getParameter(EntityFields.USER_PASSHASH).isEmpty() ||request.getParameter(EntityFields.USER_PASSHASH).length()>45){
            return false;
        }
        if(isValidEmail(request.getParameter(EntityFields.USER_EMAIL))){
            return false;
        }
        return true;
    }
    private static boolean isValidEmail(String email){
        return email.matches(EMAIL_REGEX);
    }
}
