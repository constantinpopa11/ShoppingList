package servlets;

import beans.ProductCategory;
import beans.SLCategory;
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

@WebServlet(urlPatterns = {"/NewProduct"})
@MultipartConfig
public class NewProduct extends HttpServlet {

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

        if (privileges >= Privileges.ADMIN_PRIVILEGES) {

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

        Part filePart = request.getPart(FormFields.NEW_PRODUCT_ICON_FIELD);
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        String logoPath = Utils.PRODUCT_LOGOS;
        
        if (fileName == null || fileName.isEmpty()) {
            logoPath += Utils.DEFAULT_PRODUCT_LOGO;
        } else {
            String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());

            String newIconName = UUID.randomUUID().toString() + extension;
            logoPath += newIconName;

            File uploadLocation = new File(request.getRealPath(logoPath));
            try (InputStream input = filePart.getInputStream();) {
                Files.copy(input, uploadLocation.toPath());
            }
        }

        //TODO: logo
        ShoppingListQueries.insertProduct(conn, prodName, prodDescr, measureUnit, logoPath, Integer.parseInt(prodCategory), uid);
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
