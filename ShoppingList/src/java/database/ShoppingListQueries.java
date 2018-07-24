/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import constants.DBColumns;
import constants.DBTables;
import constants.LoginStatus;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author invidia
 */
public class ShoppingListQueries {

    public static int getUserShoppingLists(Connection conn, int uid) {
        Statement stmt = null;

        int result = LoginStatus.WRONG_EMAIL;

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
                int rsSlid = rs.getInt(DBColumns.SHOPPING_LIST_ID_COL);
                String rsName = rs.getString(DBColumns.SHOPPING_LIST_NAME_COL);

                System.out.println(rsSlid + " - " + rsName);
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

        return result;
    }
}
