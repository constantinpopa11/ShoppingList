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
import constants.Privileges;
import constants.Utils;
import database.DBConnectionManager;
import database.ShoppingListQueries;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author invidia
 */
@WebServlet(urlPatterns = {"/NewShopCat"})
@MultipartConfig
public class NewShopCat extends HttpServlet {

    HttpSession session;
    Connection conn;
    int uid;
    int privileges;

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
        if (privileges == Privileges.ADMIN_PRIVILEGES) {
            response.sendRedirect("newshopcat.jsp");
        } else {
            response.sendRedirect("home.jsp");
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

        if (privileges != Privileges.ADMIN_PRIVILEGES) {

            response.sendRedirect("home");

        } else {

            String shopCatName = request.getParameter(FormFields.NEW_SHOP_CAT_NAME_FIELD);
            String shopCatDescr = request.getParameter(FormFields.NEW_SHOP_CAT_DESCR_FIELD);

            Part filePart = request.getPart(FormFields.NEW_SHOP_CAT_ICON_FIELD);
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            String iconPath = Utils.SHOP_CATEGORY_ICONS;

            if (fileName == null || fileName.isEmpty()) {
                iconPath += Utils.DEFAULT_SHOP_CAT_ICON;
            } else {
                String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());

                String newIconName = UUID.randomUUID().toString() + extension;
                iconPath += newIconName;

                File uploadLocation = new File(request.getRealPath(iconPath));
                try (InputStream input = filePart.getInputStream();) {
                    Files.copy(input, uploadLocation.toPath());
                }
            }

            //TODO: logo
            ShoppingListQueries.insertShopCat(conn, shopCatName, shopCatDescr, iconPath);
            //TODO: popup
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
