/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ProductBean;
import beans.ProductCategoryBean;
import beans.SearchParamsBean;
import beans.ShoppingListBean;
import constants.LoginStatus;
import constants.Utils;
import database.DBConnectionManager;
import database.ShoppingListQueries;
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

        SearchParamsBean searchParams = (SearchParamsBean) session.getAttribute("searchParams");

        //new search
        String lcidParam = (String) request.getParameter("lcid");
        int lcid;
        if (lcidParam != null && !lcidParam.isEmpty()) {
            lcid = Integer.parseInt(lcidParam);
            searchParams = new SearchParamsBean();
            searchParams.setLcid(lcid); 
            
        }
        
        String slidParam = (String) request.getParameter("slid");
        int slid;
        if (slidParam != null && !slidParam.isEmpty()) {
            slid = Integer.parseInt(slidParam);
            searchParams.setSlid(slid);    
        }
        
        if(searchParams.getLcid() == -1 || searchParams.getSlid() == -1){
            response.sendRedirect("home.jsp");
        }
        
        List<ShoppingListBean> shoppingLists = (ArrayList<ShoppingListBean>) session.getAttribute("shoppingLists");
        for(ShoppingListBean sl : shoppingLists){
            if(sl.getSlid() == searchParams.getSlid() ){
                session.setAttribute("activeSL", sl);
            }
        }

       
        List<ProductCategoryBean> prodCategories = ShoppingListQueries.getProdCategories(conn, searchParams.getLcid());
        request.setAttribute("prodCategories", prodCategories);

        String prodCatParam = request.getParameter("prodCat");
        if (prodCatParam != null && !prodCatParam.isEmpty()) {
            searchParams.setProdCat(Integer.parseInt(prodCatParam));
            searchParams.setPage(1);
        }
   
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            searchParams.setPage(Integer.parseInt(pageParam));
        }
        
        String sortByParam = request.getParameter("sortBy");
        if (sortByParam != null && !sortByParam.isEmpty()) {
            searchParams.setSortBy(Integer.parseInt(sortByParam));
        }

        String keyParam = request.getParameter("key");
        if (keyParam != null) {
            searchParams.setKey(keyParam);
            searchParams.setPage(1);
        }

        session.setAttribute("searchParams", searchParams);

        List<ProductBean> searchResults = ShoppingListQueries.getProducts(conn, uid, searchParams.getLcid(), searchParams.getProdCat(), 
                searchParams.getSlid(), searchParams.getKey(), searchParams.getSortBy(), searchParams.getPage());
        
        session.setAttribute("products", searchResults);
        
        System.out.println(searchParams.getSlid() + " " + searchParams.getLcid() + " " + searchParams.getProdCat() + " " + searchParams.getPage() 
                + " " + searchParams.getSortBy() + " >" + searchParams.getKey() + "<");
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
