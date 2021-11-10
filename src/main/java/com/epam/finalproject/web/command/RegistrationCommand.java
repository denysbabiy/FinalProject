package com.epam.finalproject.web.command;

import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.dao.UserDAO;
import com.epam.finalproject.db.entity.User;
import com.epam.finalproject.web.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationCommand extends Command{
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setPasshash(request.getParameter("pass"));
        user.setSurname(request.getParameter("surname"));
        user.setLogin(request.getParameter("login"));
        if(userDAO.isUserExist(user.getEmail())){
            request.setAttribute("alreadyRegistered",1);
            router.setPage("/registration.jsp");
            router.setType(Router.TransactionType.FORWARD);
            return router;
        }
        userDAO.insertUser(user);
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
        session.setAttribute("userLogin",user.getLogin());
        session.setAttribute("isAdmin",user.getIsAdmin());
        router.setPage("/controller?command=showMainPage");
        router.setType(Router.TransactionType.REDIRECT);
        return router;
    }
}
