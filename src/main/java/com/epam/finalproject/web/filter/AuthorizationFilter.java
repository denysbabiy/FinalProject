package com.epam.finalproject.web.filter;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.entity.User;
import com.epam.finalproject.service.UserService;
import com.epam.finalproject.web.Router;
import com.epam.finalproject.web.command.LoginCommand;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/controller"})
public class AuthorizationFilter implements Filter {
    private static final Logger log = Logger.getLogger(AuthorizationFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.debug("Authorization filter starts");
        HttpServletRequest req = (HttpServletRequest) request;
       // HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String path = req.getQueryString();
        if(path.startsWith("command=login") || path.startsWith("command=registration")){
            chain.doFilter(request,response);
        }else {
            if(isLoggedIn(session)){
                log.debug("user is logged in");
                if(((User)session.getAttribute("user")).getIsBlocked()==1){
                    log.debug("user is blocked");
                    request.getRequestDispatcher(Path.PAGE_BLOCKED).forward(request,response);
                }else {
                    log.debug("user is not blocked");
                    chain.doFilter(request,response);
                }
            }else {
                log.debug("user is not logged id");
                request.getRequestDispatcher(Path.PAGE_NOT_LOGIN).forward(request,response);
            }
        }

    }
    private boolean isLoggedIn(HttpSession session){
        return (session!=null && session.getAttribute("user")!=null);
    }
}
