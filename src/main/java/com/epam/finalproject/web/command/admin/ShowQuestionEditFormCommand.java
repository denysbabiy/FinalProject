package com.epam.finalproject.web.command.admin;

import com.epam.finalproject.Path;
import com.epam.finalproject.web.Router;
import com.epam.finalproject.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowQuestionEditFormCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        if(request.getParameter("questionId")==null){
            int quizId = Integer.parseInt(request.getParameter("quizId"));
            request.setAttribute("quizId",quizId);
            router.setPage(Path.PAGE_QUESTION_EDIT_FORM);
            router.setType(Router.TransactionType.FORWARD);
        }
        return router;
    }
}
