package com.epam.finalproject.web.command;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.entity.Question;
import com.epam.finalproject.db.entity.Quiz;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.QuestionService;
import com.epam.finalproject.service.QuizService;
import com.epam.finalproject.web.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

public class StartQuizCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        HttpSession session = request.getSession();
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        Quiz quiz = QuizService.getInstance().getQuizById(quizId);
        List<Question> questionList = QuestionService.getInstance().getAllQuestionsByQuizId(quizId);
        request.setAttribute("questionList", questionList);
        request.setAttribute("quiz",quiz);
        if (session.getAttribute("timeStart") == null || session.getAttribute("timeEnd") == null
                || session.getAttribute("timerId")==null){
            session.setAttribute("timerId",quizId);
            session.setAttribute("timeStart",new Timestamp(System.currentTimeMillis()));
            session.setAttribute("timeEnd", new Timestamp(System.currentTimeMillis()+ quiz.getTime()*60000));
        }
        if (!session.getAttribute("timerId").equals(quizId)) {
            router.setPage(Path.PAGE_LOGIN);
            return router;
        }

        router.setType(Router.TransactionType.FORWARD);
        router.setPage(Path.PAGE_TESTING);
        return router;
    }
}
