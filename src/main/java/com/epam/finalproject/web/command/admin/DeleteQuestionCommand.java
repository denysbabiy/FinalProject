package com.epam.finalproject.web.command.admin;

import com.epam.finalproject.Path;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.QuestionService;
import com.epam.finalproject.web.Router;
import com.epam.finalproject.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteQuestionCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        QuestionService.getInstance().deleteQuestionById(questionId);
        router.setPage(Path.COMMAND_SHOW_QUESTION_MANAGER+"&quizId="+quizId);
        router.setType(Router.TransactionType.REDIRECT);
        return router;
    }
}
