package com.epam.finalproject.web.command.admin;

import com.epam.finalproject.Path;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.AnswerService;
import com.epam.finalproject.web.Router;
import com.epam.finalproject.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class DeleteAnswerCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int answerId = Integer.parseInt(request.getParameter("answerId"));
        AnswerService.getInstance().deleteById(answerId);
        Router router = new Router();
        router.setPage(Path.COMMAND_SHOW_QUESTION_EDIT_FORM+"&questionId="+request.getParameter("questionId"));//////problem
        router.setType(Router.TransactionType.REDIRECT);
        return router;
    }
}
