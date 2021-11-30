package com.epam.finalproject.web.command;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.entity.QuizResult;
import com.epam.finalproject.db.entity.User;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.QuizResultService;
import com.epam.finalproject.web.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowStatisticCommand extends Command{
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<QuizResult> quizResults = QuizResultService.getInstance().getQuizResults(user.getId(),quizId);
        request.setAttribute("quizResults",quizResults);
        router.setPage(Path.PAGE_QUIZ_RESULT_STATISTIC);
        router.setType(Router.TransactionType.FORWARD);
        return router;
    }
}
