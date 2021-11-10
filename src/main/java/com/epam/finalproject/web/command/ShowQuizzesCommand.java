package com.epam.finalproject.web.command;

import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.entity.Quiz;
import com.epam.finalproject.web.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowQuizzesCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        Router router = new Router();
        if (userId != null) {
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            List<Quiz> quizList = DAOFactory.getInstance().getQuizDAO().getAllQuizzesBySubject(subjectId);
            request.setAttribute("quizList", quizList);
            router.setPage("WEB-INF/jsp/quizzes.jsp");
            router.setType(Router.TransactionType.FORWARD);
            return router;
        }
        return router;
    }
}
