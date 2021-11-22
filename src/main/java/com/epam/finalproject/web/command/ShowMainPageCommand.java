package com.epam.finalproject.web.command;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.entity.Subject;
import com.epam.finalproject.db.entity.User;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.SubjectService;
import com.epam.finalproject.web.Router;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

public class ShowMainPageCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute("lang");
        System.out.println(language);
        Router router = new Router();

        List<Subject> subjectList = SubjectService.getInstance().getAllSubjects(language);
        request.setAttribute("subjectList", subjectList);
        router.setPage(Path.PAGE_MAIN);
        router.setType(Router.TransactionType.FORWARD);
        return router;


    }
}
