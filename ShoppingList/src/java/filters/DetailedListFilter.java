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
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author invidia
 */
public class DetailedListFilter implements Filter {

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

        int slid = (slidParam == null) ? -1 : Integer.parseInt(slidParam);

        if (slid == -1 || uid == LoginStatus.GUEST_USER) {
            res.sendRedirect("home.jsp");
        } else {

            List<ShoppingListBean> shoppingLists = (ArrayList<ShoppingListBean>) session.getAttribute("shoppingLists");
            List<SLItemBean> slItems = null;
            List<SLCommentBean> commentsList = null;

            DBConnectionManager dbManager = (DBConnectionManager) req.getServletContext().getAttribute("DBManager");
            Connection conn = dbManager.getConnection();

            //not cached yet
            if (shoppingLists == null) {
                shoppingLists = ShoppingListQueries.getUserShoppingLists(conn, uid);
            }

            if (shoppingLists.size() > 0) {
                ShoppingListBean activeSL = null;

                for (ShoppingListBean sl : shoppingLists) {

                    if (sl.getSlid() == slid) {
                        activeSL = sl;
                    }
                }

                if (activeSL != null) {
                    session.setAttribute("activeSL", activeSL);
                    slItems = ShoppingListQueries.getShoppingListItems(conn, slid);

                    commentsList = ShoppingListQueries.getSLComments(conn, slid);
                    session.setAttribute("commentsList", commentsList);


                } else {
                    res.sendRedirect("home.jsp");
                }
            }
            session.setAttribute("shoppingLists", shoppingLists);
            session.setAttribute("slItems", slItems);
            session.setAttribute("qslid", slid);
        }

        chain.doFilter(request, response);

    }

    public void destroy() {
        //close any resources here
    }

}
