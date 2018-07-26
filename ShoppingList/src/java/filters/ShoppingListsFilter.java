/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import beans.SLCommentBean;
import beans.SLItemBean;
import beans.ShoppingListBean;
import constants.LoginStatus;
import constants.Utils;
import database.DBConnectionManager;
import database.ShoppingListQueries;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author invidia
 */
public class ShoppingListsFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        System.out.println("Requested Resource::" + uri);
        String slidParam = req.getParameter("slid");
        HttpSession session = req.getSession();
        Object uidObj = session.getAttribute(Utils.USER_COOKIE);
        int uid = (uidObj == null) ? LoginStatus.GUEST_USER : Integer.parseInt(uidObj.toString());

        int slid = (slidParam == null) ? -1 : Integer.parseInt(slidParam);

        if (uid == LoginStatus.GUEST_USER) {
            res.sendRedirect("home.jsp");
        } else {

            List<ShoppingListBean> shoppingLists = (ArrayList<ShoppingListBean>) session.getAttribute("shoppingLists");
            List<SLItemBean> slItems = null;

            DBConnectionManager dbManager = (DBConnectionManager) req.getServletContext().getAttribute("DBManager");
            Connection conn = dbManager.getConnection();

            //not cached yet
            if (shoppingLists == null) {
                shoppingLists = ShoppingListQueries.getUserShoppingLists(conn, uid);
            }

            String qslName = null;
            if (shoppingLists.size() > 0) {

                for (ShoppingListBean sl : shoppingLists) {
                    //more or less the same as a query
                    if (sl.getSlid() == slid) {
                        qslName = sl.getSlName();
                    }
                    session.setAttribute("qslid", slid);
                }

                if (qslName == null) {
                    qslName = shoppingLists.get(0).getSlName();
                    slid = shoppingLists.get(0).getSlid();
                }

                slItems = ShoppingListQueries.getShoppingListItems(conn, slid);
                
                session.setAttribute("qslid", slid);

                /*
                        for (SLItemBean item : slItems) {
                            System.out.println(item.getPid() + " " + item.getProdName() + item.getProdDescr()
                                    + " " + item.getPcid() + " " + item.getProdCatName() + " " + item.getProdCatDescr()
                                    + " " + item.getProdMeasureUnit() + " " + item.getQuantity());
                        }
                 */
            } else {
                qslName = "No lists to display";
            }

            
            session.setAttribute("qslName", qslName);
            session.setAttribute("shoppingLists", shoppingLists);
            session.setAttribute("slItems", slItems);
        }

        chain.doFilter(request, response);

    }

    public void destroy() {
        //close any resources here
    }

}
