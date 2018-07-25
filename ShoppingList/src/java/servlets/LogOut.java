/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import constants.Utils;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogOut extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    System.out.println("JSESSIONID=" + cookie.getValue() + " logged out");
                    break;
                }
            }
        }
        
        HttpSession session = request.getSession(false);
        System.out.println("User = " + session.getAttribute("user") + " logged out");
        session.removeAttribute(Utils.USER_COOKIE);
        session.removeAttribute("shoppingLists");
        session.removeAttribute("itemsList");
        response.sendRedirect("login.jsp");
    }
    
}
