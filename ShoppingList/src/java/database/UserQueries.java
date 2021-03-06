/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import constants.DBColumns;
import constants.DBTables;
import constants.LoginStatus;
import constants.Privileges;
import constants.ResetPwdStatus;
import constants.SignupStatus;
import constants.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author invidia
 */
public class UserQueries {

    public static int verifyUserCredentials(Connection conn, String email, String password) {
        Statement stmt = null;

        int result = LoginStatus.WRONG_EMAIL;

        try {

            stmt = conn.createStatement();
            String queryStr;
            queryStr = "SELECT " + DBColumns.USERS_ID_COL
                    + ", " + DBColumns.USERS_EMAIL_COL
                    + ", " + DBColumns.USERS_PASSWORD_COL
                    + " FROM " + DBTables.USERS_TABLE
                    + " WHERE "
                    + DBColumns.USERS_EMAIL_COL + "='" + email + "';";

            ResultSet rs = stmt.executeQuery(queryStr);

            //Extract data from result set
            while (rs.next()) {
                //Retrieve by column name, rs indicates query result and not actual website input
                int rsUid = rs.getInt(DBColumns.USERS_ID_COL);
                String rsEmail = rs.getString(DBColumns.USERS_EMAIL_COL);
                String rsPassword = rs.getString(DBColumns.USERS_PASSWORD_COL);

                if (email.equals(rsEmail) && !password.equals(rsPassword)) {
                    result = LoginStatus.WRONG_PASSWORD;
                } else if (email.equals(rsEmail) && password.equals(rsPassword)) {
                    result = rsUid;
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

    public static int getUserPrivilegesByUid(Connection conn, int uid) {
        Statement stmt = null;

        int result = Privileges.GUEST_USER_PRIVILEGES;

        try {

            stmt = conn.createStatement();
            String queryStr;
            queryStr = "SELECT " + DBColumns.USERS_PRIVILEGES_COL
                    + " FROM " + DBTables.USERS_TABLE
                    + " WHERE "
                    + DBColumns.USERS_ID_COL + "='" + uid + "';";

            ResultSet rs = stmt.executeQuery(queryStr);

            //Extract data from result set
            while (rs.next()) {
                //Retrieve by column name, rs indicates query result and not actual website input
                result = rs.getInt(DBColumns.USERS_PRIVILEGES_COL);

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

    public static int checkIfEmailAlreadyExists(Connection conn, String email) {
        Statement stmt = null;

        int result = SignupStatus.SIGNUP_SUCCESS;

        try {

            stmt = conn.createStatement();
            String queryStr;
            queryStr = "SELECT " + DBColumns.USERS_EMAIL_COL
                    + " FROM " + DBTables.USERS_TABLE
                    + " WHERE "
                    + DBColumns.USERS_EMAIL_COL + "='" + email + "';";

            ResultSet rs = stmt.executeQuery(queryStr);

            //If this executes it means there is already an accousnt associated to the email adress
            while (rs.next()) {
                result = SignupStatus.ALREADY_REGISTERED;
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

    public static int insertUser(Connection conn, String email, String firstName, String lastName,
            String avatarPath, String password, int privileges, String verificationCode) {

        PreparedStatement preparedStmt = null;
        int newUid = -1;

        try {

            String queryStr = " INSERT INTO " + DBTables.USERS_TABLE
                    + " (" + DBColumns.USERS_EMAIL_COL
                    + ", " + DBColumns.USERS_FIRST_NAME_COL
                    + ", " + DBColumns.USERS_LAST_NAME_COL
                    + ", " + DBColumns.USERS_AVATAR_PATH_COL
                    + ", " + DBColumns.USERS_PASSWORD_COL
                    + ", " + DBColumns.USERS_PRIVILEGES_COL
                    + ", " + DBColumns.USERS_VERIFICATION_CODE_COL
                    + ") VALUES (?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            preparedStmt = conn.prepareStatement(queryStr, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, email);
            preparedStmt.setString(2, firstName);
            preparedStmt.setString(3, lastName);
            preparedStmt.setString(4, avatarPath);
            preparedStmt.setString(5, password);
            preparedStmt.setInt(6, privileges);
            preparedStmt.setString(7, verificationCode);

            // execute the preparedstatement
            preparedStmt.execute();
            
            ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
            if (generatedKeys.next()) {

                newUid = generatedKeys.getInt(1);
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
        return newUid;
    }

    public static int resetPassword(Connection conn, String email) {
        Statement stmt = null;

        int result = ResetPwdStatus.WRONG_EMAIL;

        try {

            stmt = conn.createStatement();
            String queryStr;
            queryStr = "SELECT " + DBColumns.USERS_EMAIL_COL
                    + " FROM " + DBTables.USERS_TABLE
                    + " WHERE "
                    + DBColumns.USERS_EMAIL_COL + "='" + email + "';";

            ResultSet rs = stmt.executeQuery(queryStr);

            //Extract data from result set
            while (rs.next()) {
                //Retrieve by column name, q indicates query result and not actual website input
                String qEmail = rs.getString(DBColumns.USERS_EMAIL_COL);

                if (email.equals(qEmail)) {
                    result = ResetPwdStatus.CORRECT_EMAIL;
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

    public static String getFirstNameByUid(Connection conn, int uid) {
        Statement stmt = null;

        String result = null;

        try {

            stmt = conn.createStatement();
            String queryStr;
            queryStr = "SELECT " + DBColumns.USERS_FIRST_NAME_COL
                    + " FROM " + DBTables.USERS_TABLE
                    + " WHERE "
                    + DBColumns.USERS_ID_COL + "='" + uid + "';";

            ResultSet rs = stmt.executeQuery(queryStr);

            //Extract data from result set
            while (rs.next()) {
                //Retrieve by column name, rs indicates query result and not actual website input
                result = rs.getString(DBColumns.USERS_FIRST_NAME_COL);

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

    public static void verifyUserEmail(Connection conn, String verificationCode) {

        PreparedStatement preparedStmt = null;

        try {

            String queryStr = "UPDATE " + DBTables.USERS_TABLE
                    + " SET " + DBColumns.USERS_VERIFICATION_CODE_COL + "=?"
                    + " , " + DBColumns.USERS_PRIVILEGES_COL + "=" + Privileges.VERIFIED_USER_PRIVILEGES
                    + " WHERE " + DBColumns.USERS_VERIFICATION_CODE_COL + "=?";

            // create the mysql insert preparedstatement
            preparedStmt = conn.prepareStatement(queryStr);
            preparedStmt.setString(1, "verified");
            preparedStmt.setString(2, verificationCode);

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
}
