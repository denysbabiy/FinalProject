package com.epam.finalproject.web.command;

import com.epam.finalproject.Path;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.web.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCommand extends Command{
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        router.setPage(Path.PAGE_ERROR);
        return router;
    }
}
