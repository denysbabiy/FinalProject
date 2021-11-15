package com.epam.finalproject.web.command.admin;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.entity.Quiz;
import com.epam.finalproject.web.Router;
import com.epam.finalproject.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateQuizCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Quiz quiz = null;
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        quiz = DAOFactory.getInstance().getQuizDAO().getQuizById(quizId);
        String name = request.getParameter("name");
        int complexity = Integer.parseInt(request.getParameter("complexity"));
        int time = Integer.parseInt(request.getParameter("time"));
        quiz.setName(name);
        quiz.setComplexity(complexity);
        quiz.setTime(time);
        DAOFactory.getInstance().getQuizDAO().updateQuiz(quiz);
        Router router = new Router();
        router.setType(Router.TransactionType.REDIRECT);
        router.setPage(Path.COMMAND_SHOW_QUESTION_MANAGER+"&quizId="+quizId);
        return router;


    }
}
