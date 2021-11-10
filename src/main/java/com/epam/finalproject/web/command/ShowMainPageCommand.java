package com.epam.finalproject.web.command;

import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.entity.Subject;
import com.epam.finalproject.db.entity.User;
import com.epam.finalproject.web.Router;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowMainPageCommand extends Command{
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        Router router = new Router();
        if(userId!= null) {
            List<Subject> subjectList = DAOFactory.getInstance().getSubjectDAO().getAllSubjects();
            request.setAttribute("subjectList", subjectList);
            router.setPage("WEB-INF/jsp/main.jsp");
            router.setType(Router.TransactionType.FORWARD);
            System.out.println("i am here");
            return router;
        }
        return router;
    }
}
