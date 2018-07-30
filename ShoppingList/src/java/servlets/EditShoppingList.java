/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ProductBean;
import beans.ProductCategoryBean;
import beans.SLItemBean;
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
public class EditShoppingList extends HttpServlet {

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

        String actionParam = (String) request.getParameter("action");

        if (actionParam != null && !actionParam.isEmpty()) {

            if (actionParam.equals("add")) {
                String pidParam = (String) request.getParameter("pid");
                int pid = -1;
                if (pidParam != null && !pidParam.isEmpty()) {
                    pid = Integer.parseInt(pidParam);

                }

                String qtyParam = (String) request.getParameter("qty");
                double qty = 0.0;
                if (qtyParam != null && !qtyParam.isEmpty()) {
                    qty = Double.parseDouble(qtyParam);
                }

                
                List<ProductBean> products = (ArrayList<ProductBean>) session.getAttribute("products");
                for (ProductBean prod : products) {
                    if (prod.getPid() == pid) {
                        List<SLItemBean> slItems = (ArrayList<SLItemBean>) session.getAttribute("slItems");
                        if (slItems == null) {
                            slItems = new ArrayList<>();
                        }
                        double oldQty = 0.0;
                        for (int i = 0; i < slItems.size(); i++) {
                            if (slItems.get(i).getPid() == pid) {
                                oldQty = slItems.get(i).getQuantity();

                                if (uid == -1) {
                                    System.out.println("UPDATE QTY");
                                    slItems.get(i).setQuantity(qty + oldQty);
                                    session.setAttribute("slItems", slItems);
                                }
                            }
                        }

                        if (oldQty == 0.0) {
                            if (uid == -1) {
                                SLItemBean newItem = new SLItemBean();
                                newItem.setLogoPath(prod.getLogoPath());
                                newItem.setPcid(searchParams.getProdCat());
                                newItem.setPid(prod.getPid());
                                newItem.setProdCatDescr(prod.getProdCatDescr());
                                newItem.setProdCatIconPath(prod.getProdCatIconPath());
                                newItem.setProdCatName(prod.getProdCatName());
                                newItem.setProdDescr(prod.getProdDescr());
                                newItem.setProdMeasureUnit(prod.getMeasureUnit());
                                newItem.setProdName(prod.getProdName());
                                newItem.setQuantity(qty);
                                newItem.setSlid(searchParams.getSlid());
                                slItems.add(newItem);
                                session.setAttribute("slItems", slItems);
                            } else {
                                ShoppingListQueries.addToSLCart(conn, searchParams.getSlid(), pid, qty);
                            }

                        } else {
                            if (uid != -1) {
                                ShoppingListQueries.updateSLCart(conn, searchParams.getSlid(), pid, qty + oldQty);
                            }
                        }
                    }
                }
            }
        }

        List<ProductBean> searchResults = ShoppingListQueries.getProducts(conn, uid, searchParams.getLcid(), searchParams.getProdCat(),
                searchParams.getSlid(), searchParams.getKey(), searchParams.getSortBy(), searchParams.getPage());

        session.setAttribute("products", searchResults);

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
