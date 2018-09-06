/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ProductCategoryBean;
import beans.SLCategoryBean;
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

@WebServlet(urlPatterns = {"/NewProdCat"})
@MultipartConfig
public class NewProdCat extends HttpServlet {

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
            List<SLCategoryBean> slCategories = ShoppingListQueries.getSLCategories(conn);
            request.setAttribute("slCategories", slCategories);

            request.getRequestDispatcher("/newprodcat.jsp").forward(request, response);
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

            int lcid = Integer.parseInt(request.getParameter(FormFields.NEW_PROD_CAT_LCID_FIELD));
            String prodCatName = request.getParameter(FormFields.NEW_PROD_CAT_NAME_FIELD);
            String prodCatDescr = request.getParameter(FormFields.NEW_PROD_CAT_DESCR_FIELD);

            if(prodCatDescr == null || prodCatDescr.isEmpty())
                prodCatDescr = Utils.NO_PROD_CAT_DESCRIPTION;
            
            Part filePart = request.getPart(FormFields.NEW_PROD_CAT_ICON_FIELD);
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            String iconPath = Utils.PROD_CATEGORY_ICONS;

            if (fileName == null || fileName.isEmpty()) {
                iconPath += Utils.DEFAULT_PROD_CAT_ICON;
            } else {
                String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());

                String newIconName = UUID.randomUUID().toString() + extension;
                iconPath += newIconName;

                File uploadLocation = new File(request.getRealPath(iconPath));
                try (InputStream input = filePart.getInputStream();) {
                    Files.copy(input, uploadLocation.toPath());
                }
            }
            
            //TODO logo
            ShoppingListQueries.insertProdCat(conn, lcid, prodCatName, prodCatDescr, iconPath);
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
