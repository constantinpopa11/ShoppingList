/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import constants.DBColumns;
import constants.LoginStatus;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import constants.Utils;

public class DBConnectionManager {

    private String dbURL;
    private String user;
    private String pwd;
    private Connection conn = null;

    public DBConnectionManager(String url, String u, String p) {
        this.dbURL = url;
        this.user = u;
        this.pwd = p;

        try {
            //Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(dbURL, user, pwd);
        } catch (ClassNotFoundException ex) {
            System.out.println("An error was thrown while registering JDBC driver");
            Logger.getLogger(DBConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("An error was thrown while opening DB connection");
            ex.printStackTrace();
            Logger.getLogger(DBConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void closeConnection() {
        //close DB connection
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("An error while closing DB connection");
                Logger.getLogger(DBConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
