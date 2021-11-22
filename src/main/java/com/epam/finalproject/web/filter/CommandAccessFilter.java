package com.epam.finalproject.web.filter;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = "/controller")
public class CommandAccessFilter implements Filter {
    private static final Logger log = Logger.getLogger(CommandAccessFilter.class);
    private static List<String> adminCommands = new ArrayList<>();

    public void init(FilterConfig config) throws ServletException {
        adminCommands.add("showSubjectEditForm");
        adminCommands.add("addNewSubject");
        adminCommands.add("updateSubject");
        adminCommands.add("showQuizEditForm");
        adminCommands.add("addNewQuiz");
        adminCommands.add("showQuestionManager");
        adminCommands.add("showQuestionEditForm");
        adminCommands.add("addNewQuestion");
        adminCommands.add("updateQuiz");
        adminCommands.add("updateQuestion");
        adminCommands.add("deleteAnswer");
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.debug("Filter starts");
        if(accessAllowed(request)){
            log.debug("Allowed, filter finished");
            chain.doFilter(request, response);
        }else {
            log.debug("Not allowed");
            request.getRequestDispatcher(Path.PAGE_LOGIN).forward(request,response);
        }

    }

    private boolean accessAllowed(ServletRequest request){
        HttpServletRequest req = (HttpServletRequest) request;
        String commandName = req.getParameter("command");
        if(!adminCommands.contains(commandName)){
            return true;
        }
        HttpSession session = req.getSession(false);
        if(session == null){
            return false;
        }
        if(((User)session.getAttribute("user")).getIsAdmin()==0){
            return false;
        }
        return true;
    }
}
