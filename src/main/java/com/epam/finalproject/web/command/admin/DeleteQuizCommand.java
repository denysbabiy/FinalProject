package com.epam.finalproject.web.command.admin;

import com.epam.finalproject.Path;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.QuizService;
import com.epam.finalproject.web.Router;
import com.epam.finalproject.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteQuizCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        QuizService.getInstance().deleteQuizById(quizId);
        router.setPage(Path.COMMAND_SHOW_QUIZZES + "&subjectId=" + subjectId);
        router.setType(Router.TransactionType.REDIRECT);
        return router;
    }
}
