package com.epam.finalproject.web.command;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.entity.User;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.UserService;
import com.epam.finalproject.web.Router;
import com.epam.finalproject.web.validation.EditPersonalInfoValidation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditPersonalInformationCommand extends Command {
    private static final Logger log = Logger.getLogger(EditPersonalInformationCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        HttpSession session = request.getSession();
        Router router = new Router();
        User user = (User) session.getAttribute("user");
        String newName = request.getParameter("name");
        String newSurname = request.getParameter("surname");
        String newLogin = request.getParameter("login");
        if (!newLogin.equals(user.getLogin()) && UserService.getInstance().isUserExistByLogin(newLogin)) {
            log.debug("user with login - " + newLogin + " already exist");
            request.setAttribute("alreadyExist", true);
            router.setPage(Path.PAGE_EDIT_PERSONAL_INFORMATION_FORM);
            router.setType(Router.TransactionType.FORWARD);
            return router;
        }

        if (!EditPersonalInfoValidation.isValidUserParameters(request)) {
            log.debug("invalid user parameters");
            request.setAttribute("invalidData", true);
            router.setPage(Path.PAGE_EDIT_PERSONAL_INFORMATION_FORM);
            router.setType(Router.TransactionType.FORWARD);
            return router;
        }
        User userEdited = new User();
        userEdited.setId(user.getId());
        userEdited.setName(newName);
        userEdited.setSurname(newSurname);
        userEdited.setPasshash(user.getPasshash());
        userEdited.setEmail(user.getEmail());
        userEdited.setLogin(newLogin);
        userEdited.setIsAdmin(user.getIsAdmin());
        userEdited.setIsBlocked(user.getIsBlocked());
        UserService.getInstance().updateUser(userEdited);

        session.setAttribute("user", userEdited);

        router.setPage(Path.COMMAND_SHOW_MY_PROFILE);
        router.setType(Router.TransactionType.REDIRECT);

        return router;


    }
}
