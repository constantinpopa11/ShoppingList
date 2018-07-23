package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import constants.FormFields;
import constants.SignupStatus;
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
            String passwordHash = request.getParameter(FormFields.SIGNUP_PASSWORD_FIELD);
            String passwordConfirmHash = request.getParameter(FormFields.SIGNUP_PASSWORD_CONFIRM_FIELD);

            if (!email.equals(emailConfirm)) {
                request.setAttribute("errorMessage", "The confirmation email doesn't match.");
                request.getRequestDispatcher("/signup.jsp").forward(request, response);

            } else if (!passwordHash.equals(passwordConfirmHash)) {
                request.setAttribute("errorMessage", "The confirmation password doesn't match.");
                request.getRequestDispatcher("/signup.jsp").forward(request, response);
            }

            DBConnectionManager dbManager = (DBConnectionManager) getServletContext().getAttribute("DBManager");
            Connection conn = dbManager.getConnection();
            int status = UserQueries.checkIfEmailAlreadyExists(conn, firstName, lastName, email, passwordHash);

            if (status == SignupStatus.ALREADY_REGISTERED) {
                request.setAttribute("errorMessage", "The email provided is already associated to another account");
                request.getRequestDispatcher("/signup.jsp").forward(request, response);

            } else if (status == SignupStatus.SIGNUP_SUCCESS) {

                UserQueries.insertUser(conn, email, firstName, lastName, null, passwordHash, Utils.STANDARD_USER_PRIVILEGES);

                response.setContentType("text/html");
                PrintWriter out = response.getWriter();

                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlets and forms: Exercise 1</title>");
                out.println("</head>");
                out.println("<body bgcolor=\"white\">");
                out.println("<b>First name entered:</b> " + firstName);
                out.println("<br/>");
                out.println("<b>Last name entered :</b> " + lastName);
                out.println("<br/>");
                out.println("<b>Email :</b> " + email);
                out.println("<br/>");
                out.println("<b>Email confirm:</b> " + emailConfirm);
                out.println("<br/>");
                out.println("<b>Password :</b> " + passwordHash);
                out.println("<br/>");
                out.println("<b>Password Confirm:</b> " + passwordHash);
                out.println("<br/>");

                out.println("</body>");
                out.println("</html>");
                out.close();

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
