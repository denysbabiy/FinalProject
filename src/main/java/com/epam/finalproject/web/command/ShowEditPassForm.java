package com.epam.finalproject.web.command;

import com.epam.finalproject.Path;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.web.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowEditPassForm extends Command{
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        router.setType(Router.TransactionType.FORWARD);
        router.setPage(Path.PAGE_EDIT_PASS_FORM);
        return router;
    }
}
