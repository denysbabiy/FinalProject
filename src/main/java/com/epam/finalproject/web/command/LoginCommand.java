package com.epam.finalproject.web.command;

import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.dao.UserDAO;
import com.epam.finalproject.db.entity.User;
import com.epam.finalproject.web.Router;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand extends Command{
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        if(!userDAO.isUserExist(email)){
            request.setAttribute("wrongEmail",1);
            router.setPage("/index.jsp");
            router.setType(Router.TransactionType.FORWARD);
            return router;

        }
        User user = userDAO.getUserByEmail(email);
        if(!user.getPasshash().equals(pass)){
            request.setAttribute("wrongPass",1);
            router.setPage("/index.jsp");
            router.setType(Router.TransactionType.FORWARD);
            return router;
        }
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
        session.setAttribute("userLogin",user.getLogin());
        session.setAttribute("isAdmin",user.getIsAdmin());
        router.setPage("/controller?command=showMainPage");
        router.setType(Router.TransactionType.REDIRECT);
        return router;
    }
}
