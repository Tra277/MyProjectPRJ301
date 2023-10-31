/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class DBConnect {

    Connection conn = null;

    public DBConnect(String url, String user, String pass) {
        try {
            // call Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //connect
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("connected");
        } catch (ClassNotFoundException | SQLException ex) {
        }
    }

    public ResultSet getData(String sql) {
        ResultSet rs = null;
        Statement state;
        try {
            state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rs;
    }

    public DBConnect() {
        this("jdbc:sqlserver://localhost:1433;databaseName=PRJ301Project",
                "sa", "Tra#11a12");
    }

    public static void main(String[] args) {
        DBConnect dbConnect = new DBConnect();
        Connection connect = dbConnect.conn;
    }
}
