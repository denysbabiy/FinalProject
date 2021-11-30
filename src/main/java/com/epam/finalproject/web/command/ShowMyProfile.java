package com.epam.finalproject.web.command;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.entity.User;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.QuizResultService;
import com.epam.finalproject.service.QuizService;
import com.epam.finalproject.service.SubjectService;
import com.epam.finalproject.web.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowMyProfile extends Command{
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("quizList", QuizService.getInstance().getAllQuizzes());
        request.setAttribute("quizResultBests", QuizResultService.getInstance().getBestQuizResultsByUserId(user.getId()));
        router.setPage(Path.PAGE_PROFILE);
        return router;
    }
}
