package com.epam.finalproject.web.command.admin;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.entity.Quiz;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.QuizService;
import com.epam.finalproject.web.Router;
import com.epam.finalproject.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowQuizEditFormCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        router.setType(Router.TransactionType.FORWARD);
        int subject_id = Integer.parseInt(request.getParameter("subjectId"));
        request.setAttribute("subjectId",subject_id);
        if(request.getParameter("quizId")!=null){
            Quiz quiz = QuizService.getInstance().getQuizById(Integer.parseInt(request.getParameter("quizId")));
            System.out.println(quiz.getName());
            request.setAttribute("quiz",quiz);
            router.setPage(Path.PAGE_EDIT_QUIZ);
            return router;
        }

        router.setPage(Path.PAGE_ADD_NEW_QUIZ);
        return router;
    }
}
