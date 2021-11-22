package com.epam.finalproject.web.command;

import com.epam.finalproject.EntityFields;
import com.epam.finalproject.Path;
import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.dao.UserDAO;
import com.epam.finalproject.db.entity.User;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.UserService;
import com.epam.finalproject.web.Controller;
import com.epam.finalproject.web.Router;
import com.epam.finalproject.web.validation.RegistrationValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationCommand extends Command{
    private static final Logger log = Logger.getLogger(RegistrationCommand.class);
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        User user = new User();
        if(!RegistrationValidator.isValidUserParameters(request)){
            log.trace("Invalid input data was entered");
            router.setType(Router.TransactionType.REDIRECT);
            router.setPage(Path.PAGE_REGISTRATION);
            return router;
        }
        HttpSession session = request.getSession();
        user.setName(request.getParameter(EntityFields.USER_NAME));
        user.setEmail(request.getParameter(EntityFields.USER_EMAIL));
        user.setPasshash(request.getParameter(EntityFields.USER_PASSHASH));
        user.setSurname(request.getParameter(EntityFields.USER_SURNAME));
        user.setLogin(request.getParameter(EntityFields.USER_LOGIN));
        if(UserService.getInstance().isUserExistByEmailOrLogin(user.getEmail(), user.getLogin())){
            log.trace("User with entered email or password already registered");
            session.setAttribute("alreadyRegistered",true);
            router.setPage(Path.PAGE_REGISTRATION);
            router.setType(Router.TransactionType.REDIRECT);
            return router;
        }
        log.trace("Register user with login "+user.getLogin());
        UserService.getInstance().insertUser(user);
        log.trace("Success");
        session.setAttribute("user",user);
        router.setPage(Path.COMMAND_SHOW_MAIN_PAGE);
        router.setType(Router.TransactionType.REDIRECT);
        return router;
    }
}
