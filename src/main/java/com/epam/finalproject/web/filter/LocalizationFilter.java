package com.epam.finalproject.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class LocalizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        //System.out.println(req.getRequestURI());
        String lang = request.getParameter("lang");
        System.out.println("localization fiter");
        System.out.println(lang);

        if(lang!=null) {
            req.getSession().setAttribute("lang", lang);
        }
        chain.doFilter(request, response);


    }
}
