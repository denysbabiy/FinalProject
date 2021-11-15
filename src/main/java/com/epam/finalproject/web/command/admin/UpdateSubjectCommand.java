package com.epam.finalproject.web.command.admin;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.entity.Subject;
import com.epam.finalproject.web.Router;
import com.epam.finalproject.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class UpdateSubjectCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {

        Subject subjectEn = new Subject();
        Subject subjectUa = new Subject();

        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        System.out.println(subjectId);

        subjectEn = DAOFactory.getInstance().getSubjectDAO().getSubjectById(subjectId,"en");
        subjectUa = DAOFactory.getInstance().getSubjectDAO().getSubjectById(subjectId,"ua");

        String nameEn = request.getParameter("name_en");
        String nameUa = request.getParameter("name_ua");
        String descrEn = request.getParameter("description_en");
        String descrUa = request.getParameter("description_ua");

        subjectEn.setName(nameEn);
        subjectEn.setDescription(descrEn);
        subjectUa.setName(nameUa);
        subjectUa.setDescription(descrUa);
        Connection connection = null;
        try{
            connection = DAOFactory.getInstance().createConnection();
            connection.setAutoCommit(false);
            DAOFactory.getInstance().getSubjectDAO().updateSubject(subjectEn,"en",connection);
            DAOFactory.getInstance().getSubjectDAO().updateSubject(subjectUa,"ua",connection);
            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Router router = new Router();
        router.setPage(Path.COMMAND_SHOW_MAIN_PAGE);
        router.setType(Router.TransactionType.REDIRECT);
        return router;

    }
}
