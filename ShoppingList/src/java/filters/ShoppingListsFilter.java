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
import constants.Privileges;
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
        Object uidObj = session.getAttribute(Utils.UID_SESSION_ATTR);
        int uid = (uidObj == null) ? LoginStatus.GUEST_USER : Integer.parseInt(uidObj.toString());
        int privileges = (int) session.getAttribute(Utils.PRIVILEGES_SESSION_ATTR);
                
        int slid = (slidParam == null) ? -1 : Integer.parseInt(slidParam);

        ShoppingListBean activeSL = (ShoppingListBean) session.getAttribute("activeSL");
        if(activeSL != null && slid == -1)
            slid = activeSL.getSlid();
        
        List<SLItemBean> slItems = null;
        List<ShoppingListBean> shoppingLists = (ArrayList<ShoppingListBean>) session.getAttribute("shoppingLists");

        if (privileges >= Privileges.ADMIN_PRIVILEGES) {

            DBConnectionManager dbManager = (DBConnectionManager) req.getServletContext().getAttribute("DBManager");
            Connection conn = dbManager.getConnection();

            shoppingLists = ShoppingListQueries.getUserShoppingLists(conn, uid);

            if (shoppingLists.size() > 0) {

                for (ShoppingListBean sl : shoppingLists) {
                    //more or less the same as a query
                    if (sl.getSlid() == slid) {
                        activeSL = sl;
                    }
                    session.setAttribute("qslid", slid);
                }

                if (activeSL == null) {
                    activeSL = shoppingLists.get(0);
                    slid = shoppingLists.get(0).getSlid();
                }

                slItems = ShoppingListQueries.getShoppingListItems(conn, slid);

                session.setAttribute("qslid", slid);

            } else {
                activeSL = null;
                shoppingLists = null;
            }
        } else {

            if (shoppingLists != null) {
                if(slid == -1){
                    activeSL = shoppingLists.get(0);
                } else {
                    for(ShoppingListBean sl : shoppingLists){
                        if(sl.getSlid() == slid) activeSL = sl;
                    }
                }
                slItems = (List<SLItemBean>) session.getAttribute("slItems");
            } else {
                activeSL = null;
                shoppingLists = null;
            }
        }
        
        
        session.setAttribute("activeSL", activeSL);
        session.setAttribute("shoppingLists", shoppingLists);
        session.setAttribute("slItems", slItems);

        chain.doFilter(request, response);

    }

    public void destroy() {
        //close any resources here
    }

}
