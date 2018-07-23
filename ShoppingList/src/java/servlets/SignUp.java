package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import constants.FormFields;
import constants.Utils;
import java.io.IOException;
import java.io.PrintWriter;
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

        /*
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SignUp</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUp at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
         */
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

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlets and forms: Exercise 1</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");

        String name = request.getParameter(FormFields.SIGNUP_NAME_FIELD);
        String surname = request.getParameter(FormFields.SIGNUP_SURNAME_FIELD);
        String email = request.getParameter(FormFields.SIGNUP_EMAIL_FIELD);
        String emailConfirm = request.getParameter(FormFields.SIGNUP_EMAIL_CONFIRM_FIELD);
        String passwordHash = request.getParameter(FormFields.SIGNUP_PASSWORD_FIELD);
        String passwordConfirmHash = request.getParameter(FormFields.SIGNUP_PASSWORD_CONFIRM_FIELD);

        out.println("<b>First name entered:</b> " + name);
        out.println("<br/>");
        out.println("<b>Last name entered :</b> " + surname);
        out.println("<br/>");
        out.println("<b>Email :</b> " + email);
        out.println("<br/>");
        out.println("<b>Email confirm:</b> " + emailConfirm);
        out.println("<br/>");
        out.println("<b>Password :</b> " + passwordHash);
        out.println("<br/>");
        out.println("<b>Password Confirm:</b> " + passwordHash);
        out.println("<br/>");

        if (!email.equals(emailConfirm)) {
            request.setAttribute("errorMessage", "The confirmation Email doesn't match.");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        
        } else if (!email.equals("mario@rossi.com")) {
            //TODO: Check if email exists
            request.setAttribute("errorMessage", "The email provided is already associated to another account");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        
        } else if (!passwordHash.equals(passwordConfirmHash)) {
            request.setAttribute("errorMessage", "The confirmation password doesn't match.");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        }

        out.println("</body>");
        out.println("</html>");
        out.close();

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
