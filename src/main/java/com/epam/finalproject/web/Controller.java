package com.epam.finalproject.web;

import com.epam.finalproject.Path;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.web.command.Command;
import com.epam.finalproject.web.command.CommandContainer;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    private static final Logger log = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Controller starts");

        // extract command name from the request
        String commandName = request.getParameter("command");
        log.trace("Request parameter: command --> " + commandName);


        // obtain command object by its name
        Command command = CommandContainer.get(commandName);
        log.trace("Obtained command --> " + command);
        System.out.println(command);

        // execute command and get forward address
        Router router = null;
        try {
            router = command.execute(request, response);
        } catch (ServiceException e) {
            RequestDispatcher disp = request.getRequestDispatcher(Path.PAGE_ERROR);
            disp.forward(request, response);
        }
        String routerPage = router.getPage();

        if (router.getType() == Router.TransactionType.FORWARD) {
            RequestDispatcher disp = request.getRequestDispatcher(routerPage);
            disp.forward(request, response);
            log.trace("Forward address --> " + routerPage);
        } else {
            response.sendRedirect(request.getContextPath() + routerPage);
            log.trace("Redirect address --> " + routerPage);
        }


        log.debug("Controller finished, now go to address --> " + routerPage);


    }
}
