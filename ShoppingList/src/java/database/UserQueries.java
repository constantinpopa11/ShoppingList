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
public class UserQueries {

    public static int verifyUserQuery(Connection conn, String email, String password) {
        Statement stmt = null;

        int result = LoginStatus.WRONG_EMAIL;

        try {

            stmt = conn.createStatement();
            String queryStr;
            queryStr = "SELECT " + DBColumns.USERS_EMAIL_COL 
                    + ", " + DBColumns.USERS_PASSWORD_COL 
                    + " FROM " + DBTables.USERS_TABLE
                    + " WHERE " 
                    + DBColumns.USERS_EMAIL_COL + "='" + email + "';";
            
            ResultSet rs = stmt.executeQuery(queryStr);

            //Extract data from result set
            while (rs.next()) {
                //Retrieve by column name, q indicates query result and not actual website input
                String qEmail = rs.getString(DBColumns.USERS_EMAIL_COL);
                String qPassword = rs.getString(DBColumns.USERS_PASSWORD_COL);

                if (email.equals(qEmail) && !password.equals(qPassword)) {
                    result = LoginStatus.WRONG_PASSWORD;
                } else if (email.equals(qEmail) && password.equals(qPassword)) {
                    result = LoginStatus.CORRECT_LOGIN_DETAILS;
                }
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
