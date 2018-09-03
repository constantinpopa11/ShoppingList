package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import beans.SLItemBean;
import beans.ShoppingListBean;
import constants.FormFields;
import constants.Privileges;
import constants.SignupStatus;
import constants.Utils;
import database.DBConnectionManager;
import database.ShoppingListQueries;
import database.UserQueries;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mail.SSLMailSender;

/**
 *
 * @author invidia
 */
public class SignUp extends HttpServlet {

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
        response.sendRedirect("signup.jsp");
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

        String action = request.getParameter(FormFields.SIGNUP_SUBMIT_BUTTON);

        if (action == null) {
            response.sendRedirect("signup.jsp");

        } else if (action.equals("cancel")) { //cancel was pressed

            String prevPage = request.getParameter("prevPage");
            if (prevPage == null || prevPage.equals("")) { //signup.jsp is first visited page
                response.sendRedirect("home.jsp");
            } else {
                response.sendRedirect(prevPage); //there is a prev page before signup was accessed
            }

        } else if (action.equals("signup")) { //signup was pressed
            String firstName = request.getParameter(FormFields.SIGNUP_FIRST_NAME_FIELD);
            String lastName = request.getParameter(FormFields.SIGNUP_LAST_NAME_FIELD);
            String email = request.getParameter(FormFields.SIGNUP_EMAIL_FIELD);
            String emailConfirm = request.getParameter(FormFields.SIGNUP_EMAIL_CONFIRM_FIELD);
            String password = request.getParameter(FormFields.SIGNUP_PASSWORD_FIELD);
            String passwordConfirm = request.getParameter(FormFields.SIGNUP_PASSWORD_CONFIRM_FIELD);
            String avatarPath = Utils.USER_AVATARS + Utils.DEFAULT_USER_AVATAR;

            if (!email.equals(emailConfirm)) {
                request.setAttribute("errorMessage", "The confirmation email doesn't match.");
                request.getRequestDispatcher("/signup.jsp").forward(request, response);

            } else if (!password.equals(passwordConfirm)) {
                request.setAttribute("errorMessage", "The confirmation password doesn't match.");
                request.getRequestDispatcher("/signup.jsp").forward(request, response);
            } else if (password.length() < Utils.MIN_PASSWORD_LENGTH
                    || !password.matches(".*\\d.*") //has a number
                    || password.equals(password.toUpperCase()) //has at least one upper case letter
                    ) {

                request.setAttribute("errorMessage", "The password must contain "
                        + "a number[0-9], "
                        + "an upper case letter[A-Z] "
                        + "and have at least 8 characters");
                request.getRequestDispatcher("/signup.jsp").forward(request, response);
            } else {
                DBConnectionManager dbManager = (DBConnectionManager) getServletContext().getAttribute("DBManager");
                Connection conn = dbManager.getConnection();
                int status = UserQueries.checkIfEmailAlreadyExists(conn, email);

                if (status == SignupStatus.ALREADY_REGISTERED) {
                    request.setAttribute("errorMessage", "The email provided is already associated to another account");
                    request.getRequestDispatcher("/signup.jsp").forward(request, response);

                } else if (status == SignupStatus.SIGNUP_SUCCESS) {

                    String verificationCode = UUID.randomUUID().toString();
                    String hashedPwd = Utils.sha256(password);
                    int newUid = UserQueries.insertUser(conn, email, firstName, lastName, avatarPath,
                            hashedPwd, Privileges.NOT_VERIFIED_USER_PRIVILEGES, verificationCode);

                    if (newUid != -1) {
                        SSLMailSender.sendVerificationMail(email, verificationCode);

                        HttpSession session = request.getSession();
                        List<ShoppingListBean> shoppingLists = (ArrayList<ShoppingListBean>) session.getAttribute("shoppingLists");
                        List<SLItemBean> slItems = (ArrayList<SLItemBean>) session.getAttribute("slItems");

                        if (shoppingLists != null && shoppingLists.size() > 0) {
                            for (ShoppingListBean sl : shoppingLists) {
                                String shareLink = UUID.randomUUID().toString();
                                int newSlid = ShoppingListQueries.insertShoppingList(conn, sl.getLcid(), sl.getSlName(), sl.getSlDescr(),
                                        sl.isEditable(), sl.isRemovable(), sl.getSlIconPath(), newUid, shareLink);
                                
                                if (slItems != null && slItems.size() > 0) {
                                    for (SLItemBean item : slItems) {
                                        ShoppingListQueries.addToSLCart(conn, newSlid, item.getPid(), item.getQuantity());
                                    }
                                }
                            }
                        }

                        response.sendRedirect("home.jsp");
                    }
                }
            }
        }
    }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
