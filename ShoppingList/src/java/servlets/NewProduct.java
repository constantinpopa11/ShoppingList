/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ProductCategory;
import beans.SLCategory;
import constants.FormFields;
import constants.LoginStatus;
import constants.ResetPwdStatus;
import constants.Utils;
import database.DBConnectionManager;
import database.ShoppingListQueries;
import database.UserQueries;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
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

    HttpSession session;
    Connection conn;
    int uid;

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

        session = request.getSession();
        DBConnectionManager dbManager = (DBConnectionManager) request.getServletContext().getAttribute("DBManager");
        conn = dbManager.getConnection();
        Object uidObj = session.getAttribute(Utils.UID_SESSION_ATTR);
        uid = (uidObj == null) ? LoginStatus.GUEST_USER : Integer.parseInt(uidObj.toString());
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

        if (uid != LoginStatus.GUEST_USER) {

            String lcid = request.getParameter("lcid");
            String slCatName = request.getParameter("slCatName");

            if (lcid == null) {

                List<SLCategory> slCategories = ShoppingListQueries.getSLCategories(conn);
                request.setAttribute("slCategories", slCategories);
                request.getRequestDispatcher("/newproduct.jsp").forward(request, response);

            } else {
                request.setAttribute("lcid", lcid);
                request.setAttribute("slCatName", slCatName);
                List<ProductCategory> prodCategories = ShoppingListQueries.getProdCategories(conn, Integer.parseInt(lcid));
                request.setAttribute("prodCategories", prodCategories);
                request.getRequestDispatcher("/newproduct.jsp").forward(request, response);
            }
        }

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

        String prodName = request.getParameter(FormFields.NEW_PRODUCT_NAME_FIELD);
        String shopCategory = request.getParameter(FormFields.NEW_PRODUCT_SHOP_CAT_FIELD);
        String prodCategory = request.getParameter(FormFields.NEW_PRODUCT_ITEM_CAT_FIELD);
        String measureUnit = request.getParameter(FormFields.NEW_PRODUCT_MEASURE_UNIT_FIELD);
        String prodDescr = request.getParameter(FormFields.NEW_PRODUCT_PROD_DESCR_FIELD);
        //TODO:FILE

        System.out.println(">" + prodName + "<");

        System.out.println(">" + shopCategory + "<");

        System.out.println(">" + prodCategory + "<");

        System.out.println(">" + measureUnit + "<");

        System.out.println(">" + prodDescr + "<");

        //TODO: logo
        ShoppingListQueries.insertProduct(conn, prodName, prodDescr, measureUnit, null, Integer.parseInt(prodCategory), uid);
        //TODO: popup
        response.sendRedirect("home.jsp");
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
