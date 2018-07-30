/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ProductCategoryBean;
import constants.LoginStatus;
import constants.Utils;
import database.DBConnectionManager;
import database.ShoppingListQueries;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
public class SearchProducts extends HttpServlet {

    HttpSession session;
    Connection conn;
    int uid;
    int privileges;

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
        
        int qlcid = -1;
        int qprodCat = -1;
        int qsortBy = Utils.SORT_PROD_BY_CATEGORY;
        int qpage = 1;
        String qkey = "";
        

        String lcidParam = (String) session.getAttribute("qlcid");
        if(lcidParam != null ){
            qlcid = Integer.parseInt(lcidParam);   
        } else {
            response.sendRedirect("home.jsp");
        }
        
        session.setAttribute("qlcid", qlcid);
        List<ProductCategoryBean> prodCategories = ShoppingListQueries.getProdCategories(conn, qlcid);
        request.setAttribute("prodCategories", prodCategories);
        
        
        String prodCatParam = request.getParameter("qprodCat");
        if(prodCatParam != null && !prodCatParam.isEmpty()){
            qprodCat = Integer.parseInt(prodCatParam);
        }
        
        session.setAttribute("qprodCat", qprodCat);
        
        String sortByParam = (String) session.getAttribute("qsortBy");
        if(sortByParam != null && !sortByParam.isEmpty()){
            qsortBy = Integer.parseInt(sortByParam);
        } else{
            qsortBy = Utils.SORT_PROD_BY_NAME;
        }
        session.setAttribute("qsortBy", qsortBy);
        
        String pageParam = (String) session.getAttribute("qpage");
        if(pageParam != null && !pageParam.isEmpty()){
            qpage = Integer.parseInt(pageParam);      
        } else {
            qpage = 1;
        }
        session.setAttribute("qpage", qpage);
        
        qkey = session.getAttribute("qkey");
        if(qkey != null && !qkey.isEmpty()){
            session.setAttribute("qkey", qkey);
        } else {
            session.setAttribute("qkey", "");
        }
        
        
        
        System.out.println(qlcid + " " + prodCatParam + " " + sortByParam + " " + pageParam + " " + qkey);
        System.out.println(qlcid + " " + qprodCat + " " + qsortBy + " " + qpage + " " + qkey);
        System.out.println("------------------------------------");
        request.getRequestDispatcher("addproduct.jsp").forward(request, response);
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
