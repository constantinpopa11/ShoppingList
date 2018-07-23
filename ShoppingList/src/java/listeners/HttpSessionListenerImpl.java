/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import constants.Utils;

/**
 * Web application lifecycle listener.
 *
 * @author invidia
 */
public class HttpSessionListenerImpl implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //not registered users' sessions are deleted after 3 days by default
        se.getSession().setMaxInactiveInterval(Utils.ANONYMOUS_USER_MAX_INACTIVE_INTERVAL);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        System.out.println("Session Destroyed:: ID=" + sessionEvent.getSession().getId());
    }
}
