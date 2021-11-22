package com.epam.finalproject.web.listener;


import org.apache.log4j.Logger;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebListener
public class SessionListener implements HttpSessionListener {
    private static final Logger log = Logger.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.trace("session was created");
        se.getSession().setAttribute("lang","en");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.trace("Session was destroyed");
    }

}
