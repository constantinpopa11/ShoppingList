/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ShoppingListBean;
import constants.LoginStatus;
import constants.Privileges;
import constants.Utils;
import database.DBConnectionManager;
import database.ShoppingListQueries;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author invidia
 */
@WebServlet(name = "AddComment", urlPatterns = {"/AddComment"})
public class AddComment extends HttpServlet {

    HttpSession session;
    Connection conn;
    int uid;
    int privileges;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        session = request.getSession();
        DBConnectionManager dbManager = (DBConnectionManager) request.getServletContext().getAttribute("DBManager");
        conn = dbManager.getConnection();
        Object uidObj = session.getAttribute(Utils.UID_SESSION_ATTR);
        uid = (uidObj == null) ? LoginStatus.GUEST_USER : Integer.parseInt(uidObj.toString());
        privileges = (int) session.getAttribute(Utils.PRIVILEGES_SESSION_ATTR);
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
        processRequest(request, response);
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
        processRequest(request, response);

        if(privileges >= Privileges.ADMIN_PRIVILEGES){
            String msg = request.getParameter("msgText");
            ShoppingListBean activeSL = (ShoppingListBean) session.getAttribute("activeSL");
            
            ShoppingListQueries.insertSLComment(conn, uid, msg, activeSL.getSlid(), Utils.SL_COMMENT_TYPE);
        }
        
        String referrer = request.getHeader("referer");
        response.sendRedirect(referrer);
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
