package com.epam.finalproject.web.command.admin;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.entity.User;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.UserService;
import com.epam.finalproject.web.Router;
import com.epam.finalproject.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnblockUserCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        int id = Integer.parseInt(request.getParameter("userId"));
        UserService.getInstance().unblockUserById(id);
        router.setPage(Path.COMMAND_SHOW_USER_MANAGER);
        router.setType(Router.TransactionType.REDIRECT);
        return router;
    }
}
