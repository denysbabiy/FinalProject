package com.epam.finalproject.web.command.admin;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.entity.Subject;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.SubjectService;
import com.epam.finalproject.web.Router;
import com.epam.finalproject.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

public class AddNewSubjectCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        Subject subjectEn = new Subject();
        Subject subjectUa = new Subject();
        String nameEn = request.getParameter("name_en");
        String nameUa = request.getParameter("name_ua");
        String descrEn = request.getParameter("description_en");
        String descrUa = request.getParameter("description_ua");
        subjectEn.setName(nameEn);
        subjectEn.setDescription(descrEn);
        subjectUa.setName(nameUa);
        subjectUa.setDescription(descrUa);

        SubjectService.getInstance().addNewSubject(subjectEn,subjectUa);

        router.setPage(Path.COMMAND_SHOW_QUIZZES +"&subjectId=" + subjectEn.getId());
        router.setType(Router.TransactionType.REDIRECT);
        return router;
    }
}
