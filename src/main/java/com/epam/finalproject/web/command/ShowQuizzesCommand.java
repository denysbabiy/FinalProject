package com.epam.finalproject.web.command;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.entity.Quiz;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.QuizService;
import com.epam.finalproject.service.SubjectService;
import com.epam.finalproject.web.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowQuizzesCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));
            List<Quiz> quizList = QuizService.getInstance().getAllQuizzesBySubject(subjectId);
            request.setAttribute("quizList", quizList);
            request.setAttribute("subjectId",subjectId);
            request.setAttribute("subjectNameEn", SubjectService.getInstance().getSubjectById(subjectId,"en").getName());
            request.setAttribute("subjectNameUa",SubjectService.getInstance().getSubjectById(subjectId,"ua").getName());
            router.setPage(Path.PAGE_QUIZZES);
            router.setType(Router.TransactionType.FORWARD);
            return router;
    }
}
