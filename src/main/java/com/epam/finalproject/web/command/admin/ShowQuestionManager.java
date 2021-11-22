package com.epam.finalproject.web.command.admin;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.entity.Question;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.QuestionService;
import com.epam.finalproject.service.QuizService;
import com.epam.finalproject.web.Router;
import com.epam.finalproject.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowQuestionManager extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        List<Question> questionList = QuestionService.getInstance().getAllQuestionsByQuizId(quizId);
        String quizName = QuizService.getInstance().getQuizById(quizId).getName();
        request.setAttribute("quizName",quizName);
        request.setAttribute("quizId",quizId);
        request.setAttribute("questionList",questionList);
        router.setType(Router.TransactionType.FORWARD);
        router.setPage(Path.PAGE_QUESTION_MANAGER);
        return router;
    }
}
