/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ShoppingListBean;
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
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author invidia
 */
@MultipartConfig
public class UploadUserPic extends HttpServlet {

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

        response.sendRedirect("home.jsp");

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

        if (privileges < Privileges.ADMIN_PRIVILEGES) {

            response.sendRedirect("home.jsp");

        } else {

            List<Part> fileParts = request.getParts().stream().filter(part -> FormFields.NEW_SL_PICTURE_IMG_FIELD.equals(part.getName())).collect(Collectors.toList());

            for (Part filePart : fileParts) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                String picPath = Utils.SL_PICTURES;

                String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());

                String newPicName = UUID.randomUUID().toString() + extension;
                picPath += newPicName;

                File uploadLocation = new File(request.getRealPath(picPath));
                try (InputStream input = filePart.getInputStream();) {
                    Files.copy(input, uploadLocation.toPath());
                }

                ShoppingListBean activeSL = (ShoppingListBean) session.getAttribute("activeSL");
                ShoppingListQueries.insertSLPicture(conn, picPath, activeSL.getSlid(), uid);
            }

            String referrer = request.getHeader("referer");
            response.sendRedirect(referrer);
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
