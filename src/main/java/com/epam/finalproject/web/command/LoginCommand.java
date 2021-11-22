package com.epam.finalproject.web.command;

import com.epam.finalproject.EntityFields;
import com.epam.finalproject.Path;
import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.dao.UserDAO;
import com.epam.finalproject.db.entity.User;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.UserService;
import com.epam.finalproject.web.Router;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand extends Command {
    private static final Logger log = Logger.getLogger(LoginCommand.class);
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        String email = request.getParameter(EntityFields.USER_EMAIL);
        String pass = request.getParameter(EntityFields.USER_PASSHASH);
        HttpSession session = request.getSession();

        if (!UserService.getInstance().isUserExistByEmail(email)) {
            log.trace("entered email is not registered");
            session.setAttribute("wrongEmail", true);
            router.setPage(Path.PAGE_LOGIN);
            router.setType(Router.TransactionType.REDIRECT);
            return router;

        }
        User user = UserService.getInstance().getUserByEmail(email);
        if (!user.getPasshash().equals(pass)) {
            log.trace("wrong password entered");
            session.setAttribute("wrongPass", true);
            router.setPage(Path.PAGE_LOGIN);
            router.setType(Router.TransactionType.REDIRECT);
            return router;
        }

        session.setAttribute("user",user);
        router.setPage(Path.COMMAND_SHOW_MAIN_PAGE);
        router.setType(Router.TransactionType.REDIRECT);
        return router;
    }
}
