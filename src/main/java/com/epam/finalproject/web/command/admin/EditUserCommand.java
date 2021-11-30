package com.epam.finalproject.web.command.admin;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.entity.User;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.UserService;
import com.epam.finalproject.web.Router;
import com.epam.finalproject.web.command.Command;
import com.epam.finalproject.web.command.EditPersonalInformationCommand;
import com.epam.finalproject.web.validation.EditPersonalInfoValidation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditUserCommand extends Command {
    private static final Logger log = Logger.getLogger(EditUserCommand.class);
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = UserService.getInstance().getUserById(userId);
        if(!user.getLogin().equals(request.getParameter("login"))){
            if (UserService.getInstance().isUserExistByLogin(request.getParameter("login"))) {
                log.debug("user with this login already exist");
                request.setAttribute("loginAlreadyExist", true);
                router.setPage(Path.COMMAND_SHOW_EDIT_USER_FORM);
                router.setType(Router.TransactionType.FORWARD);
                return router;
            }
        }
        if(!user.getEmail().equals(request.getParameter("email"))){
            if (UserService.getInstance().isUserExistByEmail(request.getParameter("email"))) {
                log.debug("user with this email already exist");
                request.setAttribute("emailAlreadyExist", true);
                router.setPage(Path.COMMAND_SHOW_EDIT_USER_FORM);
                router.setType(Router.TransactionType.FORWARD);
                return router;
            }
        }

        if(!EditPersonalInfoValidation.isValidUserParameters(request)){
            log.debug("invalid user parameters");
            request.setAttribute("invalidData", true);
            router.setPage(Path.PAGE_USER_EDIT_FORM);
            router.setType(Router.TransactionType.FORWARD);
            return router;
        }
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        user.setEmail(request.getParameter("email"));
        user.setLogin(request.getParameter("login"));
        UserService.getInstance().updateUser(user);
        router.setType(Router.TransactionType.REDIRECT);
        router.setPage(Path.COMMAND_SHOW_USER_MANAGER);
        return router;

    }
}
