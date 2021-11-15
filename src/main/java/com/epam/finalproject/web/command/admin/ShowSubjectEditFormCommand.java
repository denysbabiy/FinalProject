package com.epam.finalproject.web.command.admin;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.entity.Subject;
import com.epam.finalproject.web.Router;
import com.epam.finalproject.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowSubjectEditFormCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        int isAdmin = (int) request.getSession().getAttribute("isAdmin");
        String subjectId = request.getParameter("subjectId");
        if(isAdmin==1 && subjectId!= null){
            Subject subjectEn = DAOFactory.getInstance().getSubjectDAO().getSubjectById(Integer.parseInt(subjectId),"en");
            Subject subjectUa = DAOFactory.getInstance().getSubjectDAO().getSubjectById(Integer.parseInt(subjectId),"ua");
            request.setAttribute("subjectEn",subjectEn);
            request.setAttribute("subjectUa",subjectUa);
            router.setPage(Path.PAGE_EDIT_SUBJECT);
            router.setType(Router.TransactionType.FORWARD);
            return router;
        }
        if(isAdmin==1){
            System.out.println("admin");
            router.setPage(Path.PAGE_ADD_NEW_SUBJECT);
            router.setType(Router.TransactionType.FORWARD);
            return router;
        }
        System.out.println("not admin");
        router.setPage("WEB-INF/jsp/main.jsp");//error page
        return router;
    }
}
