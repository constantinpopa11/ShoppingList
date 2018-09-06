/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ProductCategoryBean;
import beans.SLCategoryBean;
import beans.ShoppingListBean;
import constants.FormFields;
import constants.LoginStatus;
import constants.Privileges;
import constants.ResetPwdStatus;
import constants.Utils;
import database.DBConnectionManager;
import database.ShoppingListQueries;
import database.UserQueries;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
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

@WebServlet(urlPatterns = {"/NewShoppingList"})
@MultipartConfig
public class NewShoppingList extends HttpServlet {

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
        List<ShoppingListBean> shoppingLists = (ArrayList<ShoppingListBean>) session.getAttribute("shoppingLists");

        if (privileges < Privileges.ADMIN_PRIVILEGES && shoppingLists != null) {
            response.sendRedirect("home.jsp");
        } else {
            List<SLCategoryBean> slCategories = ShoppingListQueries.getSLCategories(conn);
            request.setAttribute("slCategories", slCategories);
            request.getRequestDispatcher("/newsl.jsp").forward(request, response);
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

        String slName = request.getParameter(FormFields.NEW_LIST_NAME_FIELD);
        String slDescr = request.getParameter(FormFields.NEW_LIST_DESCR_FIELD);
        
        if(slDescr == null || slDescr.isEmpty()){
            slDescr = Utils.NO_SL_DESCRIPTION;
        }
        
        String slcidField = request.getParameter(FormFields.NEW_LIST_SHOP_CAT);
        int slcid = Integer.parseInt(slcidField);
        String removableField = request.getParameter(FormFields.NEW_LIST_REMOVABLE_FIELD);
        boolean removable = removableField == null ? false : true;
        String editableField = request.getParameter(FormFields.NEW_LIST_EDITABLE_FIELD);
        boolean editable = editableField == null ? false : true;
        String shareLink = UUID.randomUUID().toString();

        Part filePart = request.getPart(FormFields.NEW_LIST_ICON_FIELD);
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        String iconPath = Utils.SHOPPING_LIST_ICONS;

        if (fileName == null || fileName.isEmpty()) {
            iconPath += Utils.DEFAULT_SL_ICON;
        } else {
            String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());

            String newIconName = UUID.randomUUID().toString() + extension;
            iconPath += newIconName;

            File uploadLocation = new File(request.getRealPath(iconPath));
            try (InputStream input = filePart.getInputStream();) {
                Files.copy(input, uploadLocation.toPath());
            }
        }

        if (privileges >= Privileges.ADMIN_PRIVILEGES) {
            //TODO: logo
            ShoppingListQueries.insertShoppingList(conn, slcid, slName, slDescr, editable, removable, iconPath, uid, shareLink);
            //TODO: popup
        } else {
            List<ShoppingListBean> shoppingLists = (ArrayList<ShoppingListBean>) session.getAttribute("shoppingLists");

            SLCategoryBean slCat = ShoppingListQueries.getSLCategoryById(conn, slcid);
            ShoppingListBean newSl = new ShoppingListBean();
            newSl.setSlName(slName);
            newSl.setSlDescr(slDescr);
            newSl.setEditable(editable);
            newSl.setRemovable(removable);
            newSl.setLcid(slcid);
            newSl.setSlIconPath(iconPath);
            newSl.setSlCatName(slCat.getSlCatName());
            newSl.setSlCatDescr(slCat.getSlCatDescr());
            newSl.setSlCatIconPath(slCat.getSlCatIconPath());

            if (shoppingLists == null) {
                shoppingLists = new ArrayList<>();
            }
            newSl.setSlid(shoppingLists.size() + 1);
            shoppingLists.add(newSl);
            session.setAttribute("shoppingLists", shoppingLists);
            session.setAttribute("activeSL", newSl);
            
        }

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
