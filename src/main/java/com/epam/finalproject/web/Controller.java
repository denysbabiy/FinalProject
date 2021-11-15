package com.epam.finalproject.web;

import com.epam.finalproject.web.command.Command;
import com.epam.finalproject.web.command.CommandContainer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //        log.debug("Controller starts");

        // extract command name from the request
        String commandName = request.getParameter("command");
//        log.trace("Request parameter: command --> " + commandName);


        // obtain command object by its name
        Command command = CommandContainer.get(commandName);
//        log.trace("Obtained command --> " + command);
        System.out.println(command);

        // execute command and get forward address
        Router router = command.execute(request, response);
        String routerPage = router.getPage();

        if (router.getType() == Router.TransactionType.FORWARD) {
            RequestDispatcher disp = request.getRequestDispatcher(routerPage);
            disp.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + routerPage);
        }

//        log.trace("Forward address --> " + forward);

//        log.debug("Controller finished, now go to forward address --> " + forward);


    }
}
