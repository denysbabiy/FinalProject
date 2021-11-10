package com.epam.finalproject.web.command;

import com.epam.finalproject.web.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        HttpSession session = request.getSession(false);
        if (session != null) {
            //log
            session.invalidate();
        } else {
            //log
        }
        router.setPage("/index.jsp");
        router.setType(Router.TransactionType.REDIRECT);
        return router;
    }
}
