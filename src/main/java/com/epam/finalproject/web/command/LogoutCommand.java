package com.epam.finalproject.web.command;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.entity.QuizResult;
import com.epam.finalproject.db.entity.User;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.QuizResultService;
import com.epam.finalproject.web.Router;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand extends Command {
    private static final Logger log = Logger.getLogger(LogoutCommand.class);
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        HttpSession session = request.getSession(false);
        User user = ((User)session.getAttribute("user"));
        if(session.getAttribute("timerId")!=null){
            QuizResult quizResult = new QuizResult();
            quizResult.setResult(0);
            quizResult.setUserId(user.getId());
            quizResult.setQuizId((Integer) session.getAttribute("timerId"));
            QuizResultService.getInstance().insertNewQuizResult(quizResult);
        }
        log.trace("User with id "+user.getId()+" logout");
        session.invalidate();
        router.setPage(Path.PAGE_LOGIN);
        router.setType(Router.TransactionType.REDIRECT);
        return router;
    }
}
