package com.epam.finalproject.web.command;

import com.epam.finalproject.web.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLangCommand extends  Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String lang = request.getParameter("lang");
        request.getSession().setAttribute("lang",lang);
        return null;
    }
}
