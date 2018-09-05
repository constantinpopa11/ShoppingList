/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.ProductBean;
import beans.ProductCategoryBean;
import beans.SLCategoryBean;
import beans.SLCommentBean;
import beans.SLItemBean;
import beans.SLPictureBean;
import beans.ShoppingListBean;
import constants.DBColumns;
import constants.DBTables;
import constants.LoginStatus;
import constants.Privileges;
import constants.Utils;
import filters.DetailedListFilter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
            queryStr = "SELECT *"
                    + " FROM "
                    + DBTables.SHOPPING_LISTS_TABLE
                    + " , " + DBTables.SL_MEMBERS_TABLE
                    + " , " + DBTables.SL_CATEGORIES_TABLE
                    + " WHERE "
                    + DBColumns.SL_MEMBERS_UID_COL + "=" + uid
                    + " AND " + DBColumns.SHOPPING_LIST_ID_COL + "=" + DBColumns.SL_MEMBERS_SLID_COL
                    + " AND " + DBColumns.SHOPPING_LIST_LCID_COL + "=" + DBColumns.SL_CAT_ID_COL + ";";

            ResultSet rs = stmt.executeQuery(queryStr);

            //Extract data from result set
            while (rs.next()) {
                //Retrieve by column name, q indicates query result and not actual website input
                int slid = rs.getInt(DBColumns.SHOPPING_LIST_ID_COL);
                String slName = rs.getString(DBColumns.SHOPPING_LIST_NAME_COL);
                String slDescr = rs.getString(DBColumns.SHOPPING_LIST_DESCR_COL);
                String slIconPath = rs.getString(DBColumns.SHOPPING_LIST_ICON_PATH_COL);
                int lcid = rs.getInt(DBColumns.SHOPPING_LIST_LCID_COL);
                String slCatName = rs.getString(DBColumns.SL_CAT_NAME);
                String slCatDescr = rs.getString(DBColumns.SL_CAT_DESCR);
                String slCatIconPath = rs.getString(DBColumns.SL_CAT_ICON_PATH);
                boolean editable = rs.getBoolean(DBColumns.SHOPPING_LIST_IS_EDITABLE_COL);
                boolean removable = rs.getBoolean(DBColumns.SHOPPING_LIST_IS_REMOVABLE_COL);
                int owner = rs.getInt(DBColumns.SHOPPING_LIST_OWNER_COL);
                String shareLink = rs.getString(DBColumns.SHOPPING_LIST_SHARE_LINK_COL);

                ShoppingListBean sl = new ShoppingListBean();
                sl.setSlid(slid);
                sl.setSlName(slName);
                sl.setSlDescr(slDescr);
                sl.setSlIconPath(slIconPath);
                sl.setLcid(lcid);
                sl.setSlCatName(slCatName);
                sl.setSlCatDescr(slCatDescr);
                sl.setSlCatIconPath(slCatIconPath);
                sl.setEditable(editable);
                sl.setRemovable(removable);
                sl.setOwner(owner);
                sl.setShareLink(shareLink);
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
                    + ", " + DBColumns.PRODUCT_CAT_ICON_PATH_COL
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
                String prodCatIconPath = rs.getString(DBColumns.PRODUCT_CAT_ICON_PATH_COL);
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
                item.setProdCatIconPath(prodCatIconPath);
                item.setProdMeasureUnit(prodMeasureUnit);
                item.setQuantity(quantity);
                item.setSlid(slid);
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
                String formattedDate = df.format(date);

                SLCommentBean comment = new SLCommentBean();
                comment.setFirstName(firstName);
                comment.setLastName(lastName);
                comment.setAvatarPath(avatarPath);
                comment.setDate(formattedDate);
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

    public static List<SLCategoryBean> getSLCategories(Connection conn) {
        Statement stmt = null;

        List<SLCategoryBean> slCategories = new ArrayList<>();
        try {

            stmt = conn.createStatement();
            String queryStr = "SELECT * FROM "
                    + DBTables.SL_CATEGORIES_TABLE;

            ResultSet rs = stmt.executeQuery(queryStr);

            //Extract data from result set
            while (rs.next()) {
                //Retrieve by column name, rs indicates query result and not actual website input
                int slcid = rs.getInt(DBColumns.SL_CAT_ID_COL);
                String slCatName = rs.getString(DBColumns.SL_CAT_NAME);
                String slCatDescr = rs.getString(DBColumns.SL_CAT_DESCR);
                String slCatIconPath = rs.getString(DBColumns.SL_CAT_ICON_PATH);

                SLCategoryBean slCat = new SLCategoryBean();
                slCat.setSlcid(slcid);
                slCat.setSlCatName(slCatName);
                slCat.setSlCatDescr(slCatDescr);
                slCat.setSlCatIconPath(slCatIconPath);
                slCategories.add(slCat);

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

        return slCategories;
    }

    public static SLCategoryBean getSLCategoryById(Connection conn, int lcid) {
        Statement stmt = null;

        SLCategoryBean slCategory = new SLCategoryBean();
        try {

            stmt = conn.createStatement();
            String queryStr = "SELECT * FROM " + DBTables.SL_CATEGORIES_TABLE
                    + " WHERE " + DBColumns.SL_CAT_ID_COL + "=" + lcid;;

            ResultSet rs = stmt.executeQuery(queryStr);

            //Extract data from result set
            while (rs.next()) {
                //Retrieve by column name, rs indicates query result and not actual website input
                int slcid = rs.getInt(DBColumns.SL_CAT_ID_COL);
                String slCatName = rs.getString(DBColumns.SL_CAT_NAME);
                String slCatDescr = rs.getString(DBColumns.SL_CAT_DESCR);
                String slCatIconPath = rs.getString(DBColumns.SL_CAT_ICON_PATH);

                slCategory.setSlcid(slcid);
                slCategory.setSlCatName(slCatName);
                slCategory.setSlCatDescr(slCatDescr);
                slCategory.setSlCatIconPath(slCatIconPath);

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

        return slCategory;
    }

    public static List<ProductCategoryBean> getProdCategories(Connection conn, int lcid) {
        Statement stmt = null;

        List<ProductCategoryBean> prodCategories = new ArrayList<>();
        try {

            stmt = conn.createStatement();
            String queryStr = "SELECT * FROM "
                    + DBTables.PRODUCT_CAT_TABLE
                    + " WHERE "
                    + DBColumns.PRODUCT_CAT_LCID_COL + "=" + lcid + ";";

            ResultSet rs = stmt.executeQuery(queryStr);

            //Extract data from result set
            while (rs.next()) {
                //Retrieve by column name, rs indicates query result and not actual website input
                int pcid = rs.getInt(DBColumns.PRODUCT_CAT_ID_COL);
                String prodCatName = rs.getString(DBColumns.PRODUCT_CAT_NAME_COL);
                String prodCatDescr = rs.getString(DBColumns.PRODUCT_CAT_DESCR_COL);
                String prodCatIconPath = rs.getString(DBColumns.PRODUCT_CAT_ICON_PATH_COL);

                ProductCategoryBean prodCat = new ProductCategoryBean();
                prodCat.setPcid(pcid);
                prodCat.setProdCatName(prodCatName);
                prodCat.setProdCatDescr(prodCatDescr);
                prodCat.setProdCatIconPath(prodCatIconPath);
                prodCategories.add(prodCat);

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

        return prodCategories;
    }

    public static void insertProduct(Connection conn, String name, String descr, String measureUnit,
            String logoPath, int pcid, int createdBy) {

        PreparedStatement preparedStmt = null;

        try {

            String queryStr = " INSERT INTO " + DBTables.PRODUCTS_TABLE
                    + " (" + DBColumns.PRODUCTS_NAME_COL
                    + ", " + DBColumns.PRODUCTS_DESCR_COL
                    + ", " + DBColumns.PRODUCTS_MEASURE_UNIT_COL
                    + ", " + DBColumns.PRODUCTS_LOGO_PATH_COL
                    + ", " + DBColumns.PRODUCTS_PCID_COL
                    + ", " + DBColumns.PRODUCTS_CREATED_BY_COL
                    + ") VALUES (?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            preparedStmt = conn.prepareStatement(queryStr);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, descr);
            preparedStmt.setString(3, measureUnit);
            preparedStmt.setString(4, logoPath);
            preparedStmt.setInt(5, pcid);

            int privileges = UserQueries.getUserPrivilegesByUid(conn, createdBy);
            if (privileges == Privileges.ADMIN_PRIVILEGES) {
                preparedStmt.setInt(6, -1);
            } else {
                preparedStmt.setInt(6, createdBy);
            }

            // execute the preparedstatement
            preparedStmt.execute();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (preparedStmt != null) {
                    preparedStmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
        }//end try

    }

    public static void insertShopCat(Connection conn, String shopCatName, String shopCatDescr, String iconPath) {

        PreparedStatement preparedStmt = null;

        try {

            String queryStr = " INSERT INTO " + DBTables.SL_CATEGORIES_TABLE
                    + " (" + DBColumns.SL_CAT_NAME
                    + ", " + DBColumns.SL_CAT_DESCR
                    + ", " + DBColumns.SL_CAT_ICON_PATH
                    + ") VALUES (?, ?, ?)";

            // create the mysql insert preparedstatement
            preparedStmt = conn.prepareStatement(queryStr);
            preparedStmt.setString(1, shopCatName);
            preparedStmt.setString(2, shopCatDescr);
            preparedStmt.setString(3, iconPath);

            // execute the preparedstatement
            preparedStmt.execute();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (preparedStmt != null) {
                    preparedStmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
        }//end try

    }

    public static void insertProdCat(Connection conn, int lcid, String prodCatName, String prodCatDescr, String iconPath) {

        PreparedStatement preparedStmt = null;

        try {

            String queryStr = " INSERT INTO " + DBTables.PRODUCT_CAT_TABLE
                    + " (" + DBColumns.PRODUCT_CAT_LCID_COL
                    + ", " + DBColumns.PRODUCT_CAT_NAME_COL
                    + ", " + DBColumns.PRODUCT_CAT_DESCR_COL
                    + ", " + DBColumns.PRODUCT_CAT_ICON_PATH_COL
                    + ") VALUES (?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            preparedStmt = conn.prepareStatement(queryStr);
            preparedStmt.setInt(1, lcid);
            preparedStmt.setString(2, prodCatName);
            preparedStmt.setString(3, prodCatDescr);
            preparedStmt.setString(4, iconPath);

            // execute the preparedstatement
            preparedStmt.execute();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (preparedStmt != null) {
                    preparedStmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
        }//end try

    }

    public static int insertShoppingList(Connection conn, int shopCategory, String slName, String slDescr,
            boolean isEditable, boolean isRemovable, String iconPath, int owner, String shareLink) {

        PreparedStatement preparedStmt = null;
        int slid = -1;

        try {

            String queryStr = " INSERT INTO " + DBTables.SHOPPING_LISTS_TABLE
                    + " (" + DBColumns.SHOPPING_LIST_LCID_COL
                    + ", " + DBColumns.SHOPPING_LIST_NAME_COL
                    + ", " + DBColumns.SHOPPING_LIST_DESCR_COL
                    + ", " + DBColumns.SHOPPING_LIST_IS_EDITABLE_COL
                    + ", " + DBColumns.SHOPPING_LIST_IS_REMOVABLE_COL
                    + ", " + DBColumns.SHOPPING_LIST_ICON_PATH_COL
                    + ", " + DBColumns.SHOPPING_LIST_OWNER_COL
                    + ", " + DBColumns.SHOPPING_LIST_SHARE_LINK_COL
                    + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            preparedStmt = conn.prepareStatement(queryStr, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setInt(1, shopCategory);
            preparedStmt.setString(2, slName);
            preparedStmt.setString(3, slDescr);
            preparedStmt.setBoolean(4, isEditable);
            preparedStmt.setBoolean(5, isRemovable);
            preparedStmt.setString(6, iconPath);
            preparedStmt.setInt(7, owner);
            preparedStmt.setString(8, shareLink);

            // execute the preparedstatement
            preparedStmt.execute();
            ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                slid = generatedKeys.getInt(1);
                insertSLMember(conn, slid, owner);
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (preparedStmt != null) {
                    preparedStmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
        }//end try
        return slid;
    }

    public static void insertSLMember(Connection conn, int slid, int uid) {

        PreparedStatement preparedStmt = null;

        try {

            String queryStr = "INSERT INTO " + DBTables.SL_MEMBERS_TABLE
                    + " (" + DBColumns.SL_MEMBERS_SLID_COL
                    + ", " + DBColumns.SL_MEMBERS_UID_COL
                    + ") VALUES (?, ?)";

            preparedStmt = conn.prepareStatement(queryStr);
            preparedStmt.setInt(1, slid);
            preparedStmt.setInt(2, uid);
            preparedStmt.execute();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (preparedStmt != null) {
                    preparedStmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
        }//end try

    }

    public static List<ProductBean> getProducts(Connection conn, int uid, int lcid, int pcid, int slid,
            String key, int sortBy, int page) {
        Statement stmt = null;

        List<ProductBean> products = new ArrayList<>();
        try {

            stmt = conn.createStatement();
            String queryStr = "SELECT * FROM "
                    + DBTables.PRODUCTS_TABLE
                    + ", " + DBTables.PRODUCT_CAT_TABLE
                    + " WHERE "
                    + DBColumns.PRODUCTS_PCID_COL + "=" + DBColumns.PRODUCT_CAT_ID_COL;

            if (pcid == Utils.SEARCH_ANY_PROD_CAT) {
                queryStr += " AND " + DBColumns.PRODUCTS_PCID_COL
                        + " IN(SELECT " + DBColumns.PRODUCT_CAT_ID_COL
                        + " FROM " + DBTables.PRODUCT_CAT_TABLE
                        + " WHERE " + DBColumns.PRODUCT_CAT_LCID_COL + "=" + lcid + ")";
            } else {
                queryStr += " AND " + DBColumns.PRODUCTS_PCID_COL + "=" + pcid;
            }
            queryStr += " AND LOWER(" + DBColumns.PRODUCTS_NAME_COL + ") LIKE LOWER('%" + key + "%')";

            queryStr += " AND (" + DBColumns.PRODUCTS_CREATED_BY_COL + "=" + Utils.CREATED_BY_ADMIN;

            if (uid > 0) {
                queryStr += " OR " + DBColumns.PRODUCTS_CREATED_BY_COL
                        + " IN (SELECT " + DBColumns.SL_MEMBERS_UID_COL
                        + " FROM " + DBTables.SL_MEMBERS_TABLE
                        + " WHERE " + DBColumns.SL_MEMBERS_SLID_COL + "=" + slid + ")";
            }

            queryStr += ")  ORDER BY ";

            if (sortBy == Utils.SORT_PROD_BY_CATEGORY) {
                queryStr += DBColumns.PRODUCT_CAT_NAME_COL;
            } else if (sortBy == Utils.SORT_PROD_BY_NAME) {
                queryStr += DBColumns.PRODUCTS_NAME_COL;
            }

            queryStr += " LIMIT " + ((page - 1) * Utils.SEARCH_RESULTS_NUMBER) + ", " + (Utils.SEARCH_RESULTS_NUMBER + 1) + ";";

            ResultSet rs = stmt.executeQuery(queryStr);

            //Extract data from result set
            while (rs.next()) {
                int pid = rs.getInt(DBColumns.PRODUCTS_ID_COL);
                String prodName = rs.getString(DBColumns.PRODUCTS_NAME_COL);
                String prodDescr = rs.getString(DBColumns.PRODUCTS_DESCR_COL);
                String measureUnit = rs.getString(DBColumns.PRODUCTS_MEASURE_UNIT_COL);
                String logoPath = rs.getString(DBColumns.PRODUCTS_LOGO_PATH_COL);
                String prodCatName = rs.getString(DBColumns.PRODUCT_CAT_NAME_COL);
                String prodCatDescr = rs.getString(DBColumns.PRODUCT_CAT_DESCR_COL);
                String prodCatIconPath = rs.getString(DBColumns.PRODUCT_CAT_ICON_PATH_COL);

                ProductBean prod = new ProductBean();
                prod.setPid(pid);
                prod.setProdName(prodName);
                prod.setProdDescr(prodDescr);
                prod.setMeasureUnit(measureUnit);
                prod.setLogoPath(logoPath);
                prod.setProdCatName(prodCatName);
                prod.setProdCatDescr(prodCatDescr);
                prod.setProdCatIconPath(prodCatIconPath);
                products.add(prod);

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

        return products;
    }

    public static void addToSLCart(Connection conn, int slid, int pid, double qty) {

        PreparedStatement preparedStmt = null;

        try {

            String queryStr = " INSERT INTO " + DBTables.SL_CARTS_TABLE
                    + " (" + DBColumns.SL_CARTS_SLID_COL
                    + ", " + DBColumns.SL_CARTS_PID_COL
                    + ", " + DBColumns.SL_CARTS_QUANTITY_COL
                    + ") VALUES (?, ?, ?)";

            // create the mysql insert preparedstatement
            preparedStmt = conn.prepareStatement(queryStr);
            preparedStmt.setInt(1, slid);
            preparedStmt.setInt(2, pid);
            preparedStmt.setDouble(3, qty);

            // execute the preparedstatement
            preparedStmt.execute();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (preparedStmt != null) {
                    preparedStmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
        }//end try

    }

    public static void updateSLCart(Connection conn, int slid, int pid, double qty) {

        Statement stmt = null;

        try {
            stmt = conn.createStatement();

            String queryStr = " UPDATE " + DBTables.SL_CARTS_TABLE
                    + " SET " + DBColumns.SL_CARTS_QUANTITY_COL + "=" + qty
                    + " WHERE " + DBColumns.SL_CARTS_SLID_COL + "=" + slid
                    + " AND " + DBColumns.SL_CARTS_PID_COL + "=" + pid;

            stmt.executeUpdate(queryStr);

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

    }

    public static void removeFromSLCart(Connection conn, int slid, int pid) {

        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            String queryStr = "DELETE FROM " + DBTables.SL_CARTS_TABLE
                    + " WHERE " + DBColumns.SL_CARTS_SLID_COL + "=" + slid
                    + " AND " + DBColumns.SL_CARTS_PID_COL + "=" + pid + ";";

            stmt.executeUpdate(queryStr);

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

    }

    public static void deleteSLPicturesBySlid(Connection conn, int slid) {

        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            String queryStr = "DELETE FROM " + DBTables.SL_PICTURES_TABLE
                    + " WHERE " + DBColumns.SL_PICTURES_SLID_COL + "=" + slid + ";";

            stmt.executeUpdate(queryStr);

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

    }

    public static void deleteSLMembersBySlid(Connection conn, int slid) {

        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            String queryStr = "DELETE FROM " + DBTables.SL_MEMBERS_TABLE
                    + " WHERE " + DBColumns.SL_MEMBERS_SLID_COL + "=" + slid + ";";

            stmt.executeUpdate(queryStr);

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
    }

    public static void deleteSLCommentsBySlid(Connection conn, int slid) {

        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            String queryStr = "DELETE FROM " + DBTables.SL_COMMENTS_TABLE
                    + " WHERE " + DBColumns.SL_COMMENTS_SLID_COL + "=" + slid + ";";

            stmt.executeUpdate(queryStr);

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
    }

    public static void deleteSLCartBySlid(Connection conn, int slid) {

        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            String queryStr = "DELETE FROM " + DBTables.SL_CARTS_TABLE
                    + " WHERE " + DBColumns.SL_CARTS_SLID_COL + "=" + slid + ";";

            stmt.executeUpdate(queryStr);

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
    }

    public static void deleteShoppingListBySlid(Connection conn, int slid) {

        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            String queryStr = "DELETE FROM " + DBTables.SHOPPING_LISTS_TABLE
                    + " WHERE " + DBColumns.SHOPPING_LIST_ID_COL + "=" + slid + ";";

            stmt.executeUpdate(queryStr);

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
    }

    public static int getShoppingListByShareLink(Connection conn, String shareLink) {
        Statement stmt = null;

        int slid = -1;

        try {

            stmt = conn.createStatement();
            String queryStr = "SELECT " + DBColumns.SHOPPING_LIST_ID_COL
                    + " FROM " + DBTables.SHOPPING_LISTS_TABLE
                    + " WHERE " + DBColumns.SHOPPING_LIST_SHARE_LINK_COL + "='" + shareLink + "';";

            ResultSet rs = stmt.executeQuery(queryStr);

            //Extract data from result set
            while (rs.next()) {
                //Retrieve by column name, rs indicates query result and not actual website input
                slid = rs.getInt(DBColumns.SHOPPING_LIST_ID_COL);
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

        return slid;
    }
    
    public static String getProductNameById(Connection conn, int pid) {
        Statement stmt = null;

        String prodName = "";

        try {

            stmt = conn.createStatement();
            String queryStr = "SELECT " + DBColumns.PRODUCTS_NAME_COL
                    + " FROM " + DBTables.PRODUCTS_TABLE
                    + " WHERE " + DBColumns.PRODUCTS_ID_COL + "=" + pid + ";";

            ResultSet rs = stmt.executeQuery(queryStr);

            //Extract data from result set
            while (rs.next()) {
                //Retrieve by column name, rs indicates query result and not actual website input
                prodName = rs.getString(DBColumns.PRODUCTS_NAME_COL);
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

        return prodName;
    }
    
    public static void insertSLPicture(Connection conn, String path, int slid, int uid) {

        PreparedStatement preparedStmt = null;

        try {

            String queryStr = " INSERT INTO " + DBTables.SL_PICTURES_TABLE
                    + " (" + DBColumns.SL_PICTURES_PATH_COL
                    + ", " + DBColumns.SL_PICTURES_SLID_COL
                    + ", " + DBColumns.SL_PICTURES_UID_COL
                    + ") VALUES (?, ?, ?)";

            // create the mysql insert preparedstatement
            preparedStmt = conn.prepareStatement(queryStr);
            preparedStmt.setString(1, path);
            preparedStmt.setInt(2, slid);
            preparedStmt.setInt(3, uid);

            // execute the preparedstatement
            preparedStmt.execute();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (preparedStmt != null) {
                    preparedStmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
        }//end try

    }

    
    public static void insertSLComment(Connection conn, int uid, String message, int slid, int type) {

        PreparedStatement preparedStmt = null;

        try {

            String queryStr = " INSERT INTO " + DBTables.SL_COMMENTS_TABLE
                    + " (" + DBColumns.SL_COMMENTS_UID_COL
                    + ", " + DBColumns.SL_COMMENTS_MESSAGE_COL
                    + ", " + DBColumns.SL_COMMENTS_SLID_COL
                    + ", " + DBColumns.SL_COMMENTS_TYPE_COL
                    + ") VALUES (?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            preparedStmt = conn.prepareStatement(queryStr);
            preparedStmt.setInt(1, uid);
            preparedStmt.setString(2, message);
            preparedStmt.setInt(3, slid);
            preparedStmt.setInt(4, type);


            // execute the preparedstatement
            preparedStmt.execute();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (preparedStmt != null) {
                    preparedStmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
        }//end try

    }
    
    public static List<SLPictureBean> getSLPictures(Connection conn, int slid) {
        Statement stmt = null;

        List<SLPictureBean> slPictures = new ArrayList<>();
        try {

            stmt = conn.createStatement();
            String queryStr = "SELECT * "
                    + " FROM " + DBTables.SL_PICTURES_TABLE 
                    + ", " + DBTables.USERS_TABLE
                    + " WHERE " + DBColumns.SL_PICTURES_SLID_COL + "=" + slid
                    + " AND " + DBColumns.USERS_ID_COL + "=" + DBColumns.SL_PICTURES_UID_COL
                    + " ORDER BY " + DBColumns.SL_PICTURES_DATE_COL;

            ResultSet rs = stmt.executeQuery(queryStr);

            //Extract data from result set
            while (rs.next()) {
                //Retrieve by column name, rs indicates query result and not actual website input
                String picPath = rs.getString(DBColumns.SL_PICTURES_PATH_COL);
                int uid = rs.getInt(DBColumns.SL_PICTURES_UID_COL);
                String userFirstName = rs.getString(DBColumns.USERS_FIRST_NAME_COL);
                String userLastName = rs.getString(DBColumns.USERS_LAST_NAME_COL);
                String dateStr = rs.getString(DBColumns.SL_PICTURES_DATE_COL);

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ITALY);
                Date date = format.parse(dateStr);

                DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                String formattedDate = df.format(date);
                
                SLPictureBean pic = new SLPictureBean();
                pic.setPicPath(picPath);
                pic.setSlid(slid);
                pic.setUid(uid);
                pic.setUserFirstName(userFirstName);
                pic.setUserLastName(userLastName);
                pic.setDate(formattedDate);
                slPictures.add(pic);

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

        return slPictures;
    }
}
