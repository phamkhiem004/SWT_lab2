/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SHD
 */
public class DBContext {
    protected Connection connection;
    public DBContext() {
        try {
            String user = "sa";
            String pass = "sa";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=rental_care";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        System.out.println(new DBContext());
    }
}
