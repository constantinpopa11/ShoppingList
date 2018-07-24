/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import constants.FormFields;
import constants.ResetPwdStatus;
import constants.Utils;
import database.DBConnectionManager;
import database.UserQueries;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author invidia
 */
public class ResetPwd extends HttpServlet {

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

        response.sendRedirect("resetpwd.jsp");
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

        String action = request.getParameter(FormFields.RESET_PWD_SUBMIT_BUTTON);

        if (action == null) {
            response.sendRedirect("resetpwd.jsp");

        } else if (action.equals("cancel")) { //cancel was pressed
            
            String prevPage = request.getParameter("prevPage");
            if (prevPage == null || prevPage.equals("")) { //resetpwd.jsp is first visited page
                response.sendRedirect("home.jsp");
            } else {
                response.sendRedirect(prevPage); //there is a prev page before resetpwd was accessed
            }

        } else if (action.equals("reset")) { //reset was pressed

            String email = request.getParameter(FormFields.RESET_PWD_EMAIL_FIELD);

            DBConnectionManager dbManager = (DBConnectionManager) getServletContext().getAttribute("DBManager");
            Connection conn = dbManager.getConnection();

            int status = UserQueries.resetPassword(conn, email);

            if (status == ResetPwdStatus.WRONG_EMAIL) {
                request.setAttribute("wrongEmail", "There's no account associated to this email address");
                request.getRequestDispatcher("/resetpwd.jsp").forward(request, response);
            } else if (status == ResetPwdStatus.CORRECT_EMAIL) {
                
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
