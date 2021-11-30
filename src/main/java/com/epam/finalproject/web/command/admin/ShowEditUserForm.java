package com.epam.finalproject.web.command.admin;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.entity.User;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.UserService;
import com.epam.finalproject.web.Router;
import com.epam.finalproject.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowEditUserForm extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        int userId = Integer.parseInt(request.getParameter("userId"));
        User userToEdit = UserService.getInstance().getUserById(userId);
        request.setAttribute("user",userToEdit);
        router.setPage(Path.PAGE_USER_EDIT_FORM);
        router.setType(Router.TransactionType.FORWARD);
        return router;
    }
}
