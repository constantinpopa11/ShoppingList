/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ShoppingListBean;
import filters.ShoppingListsFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import osm.EligibleShop;
import static osm.ShopFinder.getEligibleShops;

/**
 *
 * @author invidia
 */
public class FindNearShops extends HttpServlet {

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
        processRequest(request, response);

        String data = "";

        double lat = Double.parseDouble(request.getParameter("userLat"));
        double lon = Double.parseDouble(request.getParameter("userLong"));
        
        System.out.println("Device location: " + lat + ", " + lon);

        HttpSession session = request.getSession();
        List<ShoppingListBean> shoppingLists = (List<ShoppingListBean>) session.getAttribute("shoppingLists");
        List<String> oldShopsList = (List<String>) session.getAttribute("nearShopsList");
        boolean newShopsOnly = Boolean.parseBoolean(request.getParameter("newShopsOnly"));
        System.out.println("newShopsOnly " + newShopsOnly);
        List<String> newShopsList = new ArrayList<>();

        Set<String> shopCategories = new HashSet<>();

        try {
            if (shoppingLists != null && shoppingLists.size() > 0) {
                for (ShoppingListBean sl : shoppingLists) {
                    if (!shopCategories.contains(sl.getSlCatName())) {
                        shopCategories.add(sl.getSlCatName());

                        List<EligibleShop> shopList = getEligibleShops(sl.getSlCatName().toLowerCase(), lat, lon);
                        System.out.println(sl.getSlCatName() + " shops: " + shopList.size());
                        for (EligibleShop v : shopList) {

                            if (newShopsOnly && !oldShopsList.contains(v.getName())) {
                                data += "<br><hr>"
                                        + "<a href='https://google.com/maps/dir/" + lat + "," + lon + "/" + v.getLat() + "," + v.getLon() + "'>"
                                        + v.getName()
                                        + "</a><br>"
                                        + v.getAddress();
                            } else if(!newShopsOnly){
                                data += "<br><hr>"
                                        + "<a href='https://google.com/maps/dir/" + lat + "," + lon + "/" + v.getLat() + "," + v.getLon() + "'>"
                                        + v.getName()
                                        + "</a><br>"
                                        + v.getAddress();
                            }

                            newShopsList.add(v.getName());
                        }
                    }
                }
            }

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ShoppingListsFilter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ShoppingListsFilter.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(data.isEmpty() && newShopsOnly){
            data = "noNewShops";
        } else if (data.isEmpty()) {
            data = "There are no shops near you.";
        } else {
            data = "There are the following shops in your area:" + data;
        }

        session.setAttribute("nearShopsList", newShopsList);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(data);
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
