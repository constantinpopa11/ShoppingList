/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import constants.FormFields;
import constants.LoginStatus;
import database.DBConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import constants.Utils;
import database.DBConnectionManager;
import database.UserQueries;
import java.sql.Connection;

/**
 *
 * @author invidia
 */
public class LogIn extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("login.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter(FormFields.LOGIN_SUBMIT_BUTTON);

        if (action == null) {
            response.sendRedirect("login.jsp");

        } else if (action.equals("cancel")) { //cancel was pressed
            
            String prevPage = request.getParameter("prevPage");
            if (prevPage == null || prevPage.equals("")) { //login.jsp is first visited page
                response.sendRedirect("home.jsp");
            } else {
                response.sendRedirect(prevPage); //there is a prev page before login was accessed
            }

        } else if (action.equals("login")) { //login was pressed

            String email = request.getParameter(FormFields.LOGIN_EMAIL_FIELD);
            String passwordHash = request.getParameter(FormFields.LOGIN_PASSWORD_FIELD);
            boolean rememberMe = request.getParameter(FormFields.LOGIN_REMEMBER_ME_FLAG) == null ? false : true;

            DBConnectionManager dbManager = (DBConnectionManager) getServletContext().getAttribute("DBManager");
            Connection conn = dbManager.getConnection();

            int status = UserQueries.verifyUserCredentials(conn, email, passwordHash);

            if (status == LoginStatus.WRONG_EMAIL) {
                request.setAttribute("wrongEmail", "There's no account associated to this email address");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else if (status == LoginStatus.WRONG_PASSWORD) {
                request.setAttribute("wrongPassword", "Wrong password");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else if (status == LoginStatus.CORRECT_LOGIN_DETAILS) {
                HttpSession session = request.getSession();
                session.setAttribute(Utils.USER_COOKIE, email);

                if (rememberMe) {
                    session.setMaxInactiveInterval(Utils.REMEMBER_ME_MAX_INACTIVE_INTERVAL);
                } else {
                    session.setMaxInactiveInterval(Utils.NO_REMEMBER_ME_MAX_INACTIVE_INTERVAL);
                }

                response.sendRedirect("home.jsp");
            }
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
