/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.SLCommentBean;
import beans.SLItemBean;
import beans.ShoppingListBean;
import constants.DBColumns;
import constants.DBTables;
import constants.LoginStatus;
import filters.DetailedListFilter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author invidia
 */
public class ShoppingListQueries {

    public static List<ShoppingListBean> getUserShoppingLists(Connection conn, int uid) {
        Statement stmt = null;

        List<ShoppingListBean> shoppingLists = new ArrayList<>();
        try {

            stmt = conn.createStatement();
            String queryStr;
            queryStr = "SELECT "
                    + DBColumns.SHOPPING_LIST_ID_COL
                    + ", " + DBColumns.SHOPPING_LIST_NAME_COL
                    + " FROM "
                    + DBTables.SHOPPING_LISTS_TABLE
                    + " , " + DBTables.SL_MEMBERS_TABLE
                    + " WHERE "
                    + DBColumns.SL_MEMBERS_UID_COL + "=" + uid
                    + " AND " + DBColumns.SHOPPING_LIST_ID_COL + "=" + DBColumns.SL_MEMBERS_SLID_COL + ";";

            ResultSet rs = stmt.executeQuery(queryStr);

            //Extract data from result set
            while (rs.next()) {
                //Retrieve by column name, q indicates query result and not actual website input
                int slid = rs.getInt(DBColumns.SHOPPING_LIST_ID_COL);
                String slName = rs.getString(DBColumns.SHOPPING_LIST_NAME_COL);

                ShoppingListBean sl = new ShoppingListBean();
                sl.setSlid(slid);
                sl.setSlName(slName);
                shoppingLists.add(sl);

            }
            //Clean-up environment
            rs.close();
            stmt.close();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
        }//end try

        return shoppingLists;
    }

    public static List<SLItemBean> getShoppingListItems(Connection conn, int slid) {
        Statement stmt = null;

        List<SLItemBean> items = new ArrayList<>();
        try {

            stmt = conn.createStatement();
            String queryStr;
            queryStr = "SELECT DISTINCT "
                    + DBColumns.PRODUCTS_ID_COL
                    + ", " + DBColumns.PRODUCTS_LOGO_PATH_COL
                    + ", " + DBColumns.PRODUCTS_NAME_COL
                    + ", " + DBColumns.PRODUCTS_DESCR_COL
                    + ", " + DBColumns.PRODUCT_CAT_ID_COL
                    + ", " + DBColumns.PRODUCT_CAT_NAME_COL
                    + ", " + DBColumns.PRODUCT_CAT_DESCR_COL
                    + ", " + DBColumns.PRODUCTS_MEASURE_UNIT_COL
                    + ", " + DBColumns.SL_CARTS_QUANTITY_COL
                    + " FROM "
                    + DBTables.PRODUCTS_TABLE
                    + ", " + DBTables.PRODUCT_CAT_TABLE
                    + ", " + DBTables.SL_CARTS_TABLE
                    + ", " + DBTables.SHOPPING_LISTS_TABLE
                    + " WHERE "
                    + DBColumns.SL_CARTS_SLID_COL + " = " + slid
                    + " AND " + DBColumns.PRODUCTS_PCID_COL + "=" + DBColumns.PRODUCT_CAT_ID_COL
                    + " AND " + DBColumns.PRODUCTS_ID_COL + "=" + DBColumns.SL_CARTS_PID_COL;

            ResultSet rs = stmt.executeQuery(queryStr);

            //Extract data from result set
            while (rs.next()) {
                //Retrieve by column name, rs indicates query result and not actual website input
                int pid = rs.getInt(DBColumns.PRODUCTS_ID_COL);
                String prodLogoPath = rs.getString(DBColumns.PRODUCTS_LOGO_PATH_COL);
                String prodName = rs.getString(DBColumns.PRODUCTS_NAME_COL);
                String prodDescr = rs.getString(DBColumns.PRODUCTS_DESCR_COL);
                int pcid = rs.getInt(DBColumns.PRODUCT_CAT_ID_COL);
                String prodCatName = rs.getString(DBColumns.PRODUCT_CAT_NAME_COL);
                String prodCatDescr = rs.getString(DBColumns.PRODUCT_CAT_DESCR_COL);
                String prodMeasureUnit = rs.getString(DBColumns.PRODUCTS_MEASURE_UNIT_COL);
                double quantity = rs.getDouble(DBColumns.SL_CARTS_QUANTITY_COL);

                SLItemBean item = new SLItemBean();
                item.setPid(pid);
                item.setLogoPath(prodLogoPath);
                item.setProdName(prodName);
                item.setProdDescr(prodDescr);
                item.setPcid(pcid);
                item.setProdCatName(prodCatName);
                item.setProdCatDescr(prodCatDescr);
                item.setProdMeasureUnit(prodMeasureUnit);
                item.setQuantity(quantity);
                items.add(item);

            }
            //Clean-up environment
            rs.close();
            stmt.close();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
        }//end try

        return items;
    }

    public static List<SLCommentBean> getSLComments(Connection conn, int slid) {
        Statement stmt = null;

        List<SLCommentBean> comments = new ArrayList<>();
        try {

            stmt = conn.createStatement();
            String queryStr;
            queryStr = "SELECT "
                    + DBColumns.USERS_FIRST_NAME_COL
                    + ", " + DBColumns.USERS_LAST_NAME_COL
                    + ", " + DBColumns.USERS_AVATAR_PATH_COL
                    + ", " + DBColumns.SL_COMMENTS_DATE_COL
                    + ", " + DBColumns.SL_COMMENTS_MESSAGE_COL
                    + ", " + DBColumns.SL_COMMENTS_TYPE_COL
                    + " FROM "
                    + DBTables.USERS_TABLE
                    + ", " + DBTables.SL_COMMENTS_TABLE
                    + " WHERE "
                    + DBColumns.USERS_ID_COL + "=" + DBColumns.SL_COMMENTS_UID_COL
                    + " AND " + DBColumns.SL_COMMENTS_SLID_COL + "=" + slid
                    + " ORDER BY "
                    + DBColumns.SL_COMMENTS_DATE_COL + " ;";

            ResultSet rs = stmt.executeQuery(queryStr);

            //Extract data from result set
            while (rs.next()) {
                //Retrieve by column name, rs indicates query result and not actual website input
                String firstName = rs.getString(DBColumns.USERS_FIRST_NAME_COL);
                String lastName = rs.getString(DBColumns.USERS_LAST_NAME_COL);
                String avatarPath = rs.getString(DBColumns.USERS_AVATAR_PATH_COL);
                String dateStr = rs.getString(DBColumns.SL_COMMENTS_DATE_COL);
                String message = rs.getString(DBColumns.SL_COMMENTS_MESSAGE_COL);
                int type = rs.getInt(DBColumns.SL_COMMENTS_TYPE_COL);

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ITALY);
                Date date = format.parse(dateStr);

                DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                String result = df.format(date);

                SLCommentBean comment = new SLCommentBean();
                comment.setFirstName(firstName);
                comment.setLastName(lastName);
                comment.setAvatarPath(avatarPath);
                comment.setDate(result);
                comment.setMessage(message);
                comment.setType(type);
                comments.add(comment);

            }
            //Clean-up environment
            rs.close();
            stmt.close();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
        }//end try

        return comments;
    }
}
