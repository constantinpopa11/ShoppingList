/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

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
        

        HttpSession session = req.getSession();
        Object uidObj = session.getAttribute(Utils.USER_COOKIE);
        int uid = (uidObj == null) ? LoginStatus.GUEST_USER : (int) uidObj;

        List<ShoppingListBean> shoppingLists = null;
        List<SLItemBean> slItems = null;

        if (uid == LoginStatus.GUEST_USER) {
            System.out.println("NOT REGISTERED !!");
        } else {
            DBConnectionManager dbManager = (DBConnectionManager) req.getServletContext().getAttribute("DBManager");
            Connection conn = dbManager.getConnection();

            System.out.println("UID : " + uid);

            shoppingLists = ShoppingListQueries.getUserShoppingLists(conn, uid);


            if (shoppingLists.size() > 0) {
                slItems = ShoppingListQueries.getShoppingListItems(conn, shoppingLists.get(0).getSlid()); 

            }
        }

        session.setAttribute("shoppingLists", shoppingLists);
        session.setAttribute("slItems", slItems);
        
        chain.doFilter(request, response);

    }

    public void destroy() {
        //close any resources here
    }

}
