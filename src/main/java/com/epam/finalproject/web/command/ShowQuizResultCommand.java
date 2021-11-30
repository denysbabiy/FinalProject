package com.epam.finalproject.web.command;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.entity.Quiz;
import com.epam.finalproject.db.entity.QuizResult;
import com.epam.finalproject.db.entity.User;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.QuizResultService;
import com.epam.finalproject.service.QuizService;
import com.epam.finalproject.service.SubjectService;
import com.epam.finalproject.web.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowQuizResultCommand extends Command{
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        Quiz quiz = QuizService.getInstance().getQuizById(quizId);
        request.setAttribute("quiz", quiz);
        request.setAttribute("subject", SubjectService.getInstance().getSubjectById(quiz.getSubjectId(), (String) session.getAttribute("lang")));
        List<QuizResult> quizResults = QuizResultService.getInstance().getQuizResults(user.getId(),quizId);
        request.setAttribute("quizResultList",quizResults);
        System.out.println(quizResults.get(0).getCreate_time());
        router.setPage(Path.PAGE_TEST_RESULT);
        router.setType(Router.TransactionType.FORWARD);
        return router;
    }
}
