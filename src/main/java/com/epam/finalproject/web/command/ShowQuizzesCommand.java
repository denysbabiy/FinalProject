package com.epam.finalproject.web.command;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.entity.Quiz;
import com.epam.finalproject.db.entity.User;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.QuizService;
import com.epam.finalproject.service.SubjectService;
import com.epam.finalproject.web.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowQuizzesCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        String sortBy = "id";
        int page = 1;
        int countOfQuizOnPage = 6;
        if(((User)request.getSession().getAttribute("user")).getIsAdmin()==1){
            countOfQuizOnPage = 5;
        }


        if (request.getParameter("sortBy") != null) {
            sortBy = request.getParameter("sortBy");
        }
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Quiz> quizList = QuizService.getInstance().getAllQuizzesSorted(subjectId, sortBy, page,countOfQuizOnPage);


        //List<Quiz> quizList = QuizService.getInstance().getAllQuizzesBySubject(subjectId);
        int quizCount = quizList.size();
        int numberOfPages = (int) Math.ceil(quizCount * 1.0 / countOfQuizOnPage);
        request.setAttribute("currentPage", page);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("sortBy",sortBy);
        request.setAttribute("quizList", quizList);
        request.setAttribute("subjectId", subjectId);
        request.setAttribute("subjectNameEn", SubjectService.getInstance().getSubjectById(subjectId, "en").getName());
        request.setAttribute("subjectNameUa", SubjectService.getInstance().getSubjectById(subjectId, "ua").getName());
        router.setPage(Path.PAGE_QUIZZES);
        router.setType(Router.TransactionType.FORWARD);
        return router;
    }
}
