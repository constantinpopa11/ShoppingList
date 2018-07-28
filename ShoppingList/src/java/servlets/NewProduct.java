/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import constants.FormFields;
import constants.LoginStatus;
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
public class NewProduct extends HttpServlet {

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

        String prodName = request.getParameter(FormFields.NEW_PRODUCT_NAME_FIELD);
        String shopCategory = request.getParameter(FormFields.NEW_PRODUCT_SHOP_CAT_FIELD);
        String itemCategory = request.getParameter(FormFields.NEW_PRODUCT_ITEM_CAT_FIELD);
        String measureUnit = request.getParameter(FormFields.NEW_PRODUCT_MEASURE_UNIT_FIELD);
        String prodDescr = request.getParameter(FormFields.NEW_PRODUCT_PROD_DESCR_FIELD);
        //TODO:FILE

        System.out.println(">" + prodName + "<");

        System.out.println(">" + shopCategory + "<");

        System.out.println(">" + itemCategory + "<");

        System.out.println(">" + measureUnit + "<");

        System.out.println(">" + prodDescr + "<");
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

        DBConnectionManager dbManager = (DBConnectionManager) getServletContext().getAttribute("DBManager");
        Connection conn = dbManager.getConnection();

        String prodName = request.getParameter(FormFields.NEW_PRODUCT_NAME_FIELD);
        String shopCategory = request.getParameter(FormFields.NEW_PRODUCT_SHOP_CAT_FIELD);
        String itemCategory = request.getParameter(FormFields.NEW_PRODUCT_ITEM_CAT_FIELD);
        String measureUnit = request.getParameter(FormFields.NEW_PRODUCT_MEASURE_UNIT_FIELD);
        String prodDescr = request.getParameter(FormFields.NEW_PRODUCT_PROD_DESCR_FIELD);
        //TODO:FILE

        System.out.println(">" + prodName + "<");

        System.out.println(">" + shopCategory + "<");

        System.out.println(">" + itemCategory + "<");

        System.out.println(">" + measureUnit + "<");

        System.out.println(">" + prodDescr + "<");

        int uid = UserQueries.verifyUserCredentials(conn, email, passwordHash);

        if (uid == LoginStatus.WRONG_EMAIL) {
            request.setAttribute("wrongEmail", "There's no account associated to this email address");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else if (uid == LoginStatus.WRONG_PASSWORD) {
            request.setAttribute("wrongPassword", "Wrong password");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else if (uid > 0) {
            HttpSession session = request.getSession();
            session.setAttribute(Utils.USER_COOKIE, uid);

            if (rememberMe) {
                session.setMaxInactiveInterval(Utils.REMEMBER_ME_MAX_INACTIVE_INTERVAL);
            } else {
                session.setMaxInactiveInterval(Utils.NO_REMEMBER_ME_MAX_INACTIVE_INTERVAL);
            }

            response.sendRedirect("home.jsp");
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
