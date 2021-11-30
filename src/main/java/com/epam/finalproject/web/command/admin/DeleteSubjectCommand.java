package com.epam.finalproject.web.command.admin;

import com.epam.finalproject.Path;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.SubjectService;
import com.epam.finalproject.web.Router;
import com.epam.finalproject.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteSubjectCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        SubjectService.getInstance().deleteSubjectById(subjectId);

        router.setPage(Path.COMMAND_SHOW_MAIN_PAGE);
        router.setType(Router.TransactionType.REDIRECT);
        return router;
    }
}
