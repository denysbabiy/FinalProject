package com.epam.finalproject.web.command;

import com.epam.finalproject.Path;
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
        HttpSession session = request.getSession();
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setPasshash(request.getParameter("pass"));
        user.setSurname(request.getParameter("surname"));
        user.setLogin(request.getParameter("login"));
        if(userDAO.isUserExist(user.getEmail())){
            session.setAttribute("alreadyRegistered",1);
            router.setPage(Path.PAGE_REGISTRATION);
            router.setType(Router.TransactionType.REDIRECT);
            return router;
        }
        userDAO.insertUser(user);

        session.setAttribute("userId", user.getId());
        session.setAttribute("userLogin",user.getLogin());
        session.setAttribute("isAdmin",user.getIsAdmin());
        router.setPage(Path.COMMAND_SHOW_MAIN_PAGE);
        router.setType(Router.TransactionType.REDIRECT);
        return router;
    }
}
