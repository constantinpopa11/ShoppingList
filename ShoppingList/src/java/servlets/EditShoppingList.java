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
import constants.Privileges;
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
                                String prodName = ShoppingListQueries.getProductNameById(conn, pid);
                                String msg = prodName + " was added to the list";
                                ShoppingListQueries.insertSLComment(conn, Utils.SYSTEM_UID, msg, searchParams.getSlid(), Utils.SL_ADD_TYPE);

                                ShoppingListQueries.addToSLCart(conn, searchParams.getSlid(), pid, qty);
                            }

                        } else {
                            if (uid != -1) {
                                String prodName = ShoppingListQueries.getProductNameById(conn, pid);
                                String msg = prodName + " quantity was updated from " + oldQty + " to " + (oldQty + qty);
                                ShoppingListQueries.insertSLComment(conn, Utils.SYSTEM_UID, msg, searchParams.getSlid(), Utils.SL_EDIT_TYPE);

                                ShoppingListQueries.updateSLCart(conn, searchParams.getSlid(), pid, qty + oldQty);
                            }
                        }
                    }
                }
                List<ProductBean> searchResults = ShoppingListQueries.getProducts(conn, uid, searchParams.getLcid(), searchParams.getProdCat(),
                        searchParams.getSlid(), searchParams.getKey(), searchParams.getSortBy(), searchParams.getPage());

                session.setAttribute("products", searchResults);

                request.getRequestDispatcher("addproduct.jsp").forward(request, response);

            } else if (actionParam.equals("removeItem")) {

                int pid = Integer.parseInt(request.getParameter("removePid"));
                ShoppingListBean activeSL = (ShoppingListBean) session.getAttribute("activeSL");

                if (uid == activeSL.getOwner() || activeSL.isEditable()) {
                    ShoppingListQueries.removeFromSLCart(conn, activeSL.getSlid(), pid);

                    String prodName = ShoppingListQueries.getProductNameById(conn, pid);
                    String msg = prodName + " was removed from the list";
                    ShoppingListQueries.insertSLComment(conn, Utils.SYSTEM_UID, msg, activeSL.getSlid(), Utils.SL_REMOVE_TYPE);

                } else if (privileges < Privileges.ADMIN_PRIVILEGES) {
                    List<SLItemBean> slItems = (ArrayList<SLItemBean>) session.getAttribute("slItems");
                    SLItemBean itemToDelete = null;

                    if (slItems != null && slItems.size() > 0) {
                        for (SLItemBean item : slItems) {
                            if (item.getPid() == pid) {
                                itemToDelete = item;
                            }
                        }
                        slItems.remove(itemToDelete);
                        session.setAttribute("slItems", slItems);
                    }
                }

                String referrer = request.getHeader("referer");
                response.sendRedirect(referrer);
            } else if (actionParam.equals("update")) {

                int pid = Integer.parseInt(request.getParameter("updatePid"));
                double qty = Double.parseDouble(request.getParameter("qty"));
                ShoppingListBean activeSL = (ShoppingListBean) session.getAttribute("activeSL");
                List<SLItemBean> slItems = (ArrayList<SLItemBean>) session.getAttribute("slItems");

                if (uid == activeSL.getOwner() || activeSL.isEditable()) {
                    ShoppingListQueries.updateSLCart(conn, activeSL.getSlid(), pid, qty);
                    
                    double oldQty = 0.0;
                    if (slItems != null && slItems.size() > 0) {
                        for (SLItemBean item : slItems) {
                            if (item.getPid() == pid) {
                                oldQty = item.getQuantity();
                            }
                        }
                    }

                    String prodName = ShoppingListQueries.getProductNameById(conn, pid);
                    String msg = prodName + " quantity was updated from " + oldQty + " to " + qty;
                    ShoppingListQueries.insertSLComment(conn, Utils.SYSTEM_UID, msg, activeSL.getSlid(), Utils.SL_EDIT_TYPE);
                    
                } else if (privileges < Privileges.ADMIN_PRIVILEGES) {
                    

                    if (slItems != null && slItems.size() > 0) {
                        for (SLItemBean item : slItems) {
                            if (item.getPid() == pid) {
                                item.setQuantity(qty);
                            }
                        }
                        session.setAttribute("slItems", slItems);
                    }
                }

                String referrer = request.getHeader("referer");
                response.sendRedirect(referrer);
            } else if (actionParam.equals("removeSL")) {
                ShoppingListBean activeSL = (ShoppingListBean) session.getAttribute("activeSL");

                if (privileges >= Privileges.NOT_VERIFIED_USER_PRIVILEGES) {
                    if (uid == activeSL.getOwner() || activeSL.isRemovable()) {
                        int slid = activeSL.getSlid();

                        ShoppingListQueries.deleteSLCartBySlid(conn, slid);
                        ShoppingListQueries.deleteSLCommentsBySlid(conn, slid);
                        ShoppingListQueries.deleteSLMembersBySlid(conn, slid);
                        ShoppingListQueries.deleteSLPicturesBySlid(conn, slid);
                        ShoppingListQueries.deleteShoppingListBySlid(conn, slid);

                    } else {
                        //cant be removed
                    }
                }

                List<ShoppingListBean> shoppingLists = (ArrayList<ShoppingListBean>) session.getAttribute("shoppingLists");
                shoppingLists.remove(activeSL);
                if (shoppingLists.size() > 0) {
                    activeSL = shoppingLists.get(0);
                } else {
                    activeSL = null;
                }
                session.setAttribute("activeSL", activeSL);

                response.sendRedirect("home.jsp");

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
