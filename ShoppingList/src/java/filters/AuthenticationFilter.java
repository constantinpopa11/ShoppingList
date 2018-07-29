/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import constants.LoginStatus;
import constants.Privileges;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import constants.Utils;
import database.DBConnectionManager;
import database.UserQueries;
import java.sql.Connection;


public class AuthenticationFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        DBConnectionManager dbManager = (DBConnectionManager) req.getServletContext().getAttribute("DBManager");
        Connection conn = dbManager.getConnection();
        HttpSession session = req.getSession(true);

        
        String uri = req.getRequestURI();
        System.out.println("Requested Resource::" + uri);

        Object uidObj = session.getAttribute(Utils.UID_SESSION_ATTR);
        int uid = (uidObj == null) ? LoginStatus.GUEST_USER : Integer.parseInt(uidObj.toString());

        int privileges = UserQueries.getUserPrivilegesByUid(conn, uid);
        session.setAttribute(Utils.PRIVILEGES_SESSION_ATTR, privileges);

        if (privileges >= Privileges.NOT_VERIFIED_USER_PRIVILEGES) {
            String firstName = UserQueries.getFirstNameByUid(conn, uid);
            session.setAttribute(Utils.FIRST_NAME_SESSION_ATTR, firstName);
        }

        if (privileges != Privileges.GUEST_USER_PRIVILEGES
                && (uri.endsWith("login.jsp") || uri.endsWith("signup.jsp"))) {

            res.sendRedirect("home.jsp"); //already registered or logged in

        }

        if (privileges <= Privileges.NOT_VERIFIED_USER_PRIVILEGES
                && (uri.endsWith("newproduct.jsp") || (uri.endsWith("NewProduct")))) {

            res.sendRedirect("home.jsp"); //already registered or logged in

        }

        if (privileges >= Privileges.ADMIN_PRIVILEGES && (uri.endsWith("newproduct.jsp"))) {

            res.sendRedirect("NewProduct"); //already registered or logged in

        }

        if (privileges != Privileges.ADMIN_PRIVILEGES
                && (uri.endsWith("newshopcat.jsp") || (uri.endsWith("newprodcat.jsp")))) {

            res.sendRedirect("home.jsp");
        }

        chain.doFilter(request, response);
    }

    public void destroy() {
        //close any resources here
    }

}
