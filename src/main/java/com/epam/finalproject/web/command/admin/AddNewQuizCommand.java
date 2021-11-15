package com.epam.finalproject.web.command.admin;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.entity.Question;
import com.epam.finalproject.db.entity.Quiz;
import com.epam.finalproject.web.Router;
import com.epam.finalproject.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AddNewQuizCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Quiz quiz = new Quiz();
        quiz.setName(request.getParameter("name"));
        quiz.setComplexity(Integer.parseInt(request.getParameter("complexity")));
        quiz.setTime(Integer.parseInt(request.getParameter("time")));
        quiz.setSubjectId(Integer.parseInt(request.getParameter("subjectId")));
        DAOFactory.getInstance().getQuizDAO().insertQuiz(quiz);
        Router router = new Router();
        router.setPage(Path.COMMAND_SHOW_QUESTION_MANAGER+"&quizId="+quiz.getId());
        router.setType(Router.TransactionType.REDIRECT);
        return router;

    }
}
