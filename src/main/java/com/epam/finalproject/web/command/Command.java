package com.epam.finalproject.web.command;

import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.web.Router;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.sql.SQLException;

public abstract class Command implements Serializable {
    public abstract Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
