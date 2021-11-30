package com.epam.finalproject.web.command;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.entity.User;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.UserService;
import com.epam.finalproject.web.Router;
import com.epam.finalproject.web.validation.EditPassValidation;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditPassCommand extends Command{
    private static final Logger log = Logger.getLogger(EditPassCommand.class);
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(!request.getParameter("old-pass").equals(user.getPasshash())){
            log.debug("incorrect old password entered");
            request.setAttribute("incorrectPassword",true);
            router.setPage(Path.COMMAND_SHOW_EDIT_PASS_FORM);
            router.setType(Router.TransactionType.FORWARD);
            return router;
        }
        if(!EditPassValidation.isPassValid(request)){
            log.debug("invalid new password entered");
            request.setAttribute("invalidPassword",true);
            router.setPage(Path.COMMAND_SHOW_EDIT_PASS_FORM);
            router.setType(Router.TransactionType.FORWARD);
            return router;
        }
        String newPass = request.getParameter("new-pass");
        String newPassRep = request.getParameter("new-pass-rep");
        if(!newPass.equals(newPassRep)){
            log.debug("incorrect repeated password entered");
            request.setAttribute("incorrectPassRep",true);
            router.setPage(Path.COMMAND_SHOW_EDIT_PASS_FORM);
            router.setType(Router.TransactionType.FORWARD);
            return router;
        }
        user.setPasshash(newPass);
        UserService.getInstance().updateUser(user);
        router.setPage(Path.COMMAND_SHOW_MY_PROFILE);
        router.setType(Router.TransactionType.REDIRECT);
        return router;
    }
}
