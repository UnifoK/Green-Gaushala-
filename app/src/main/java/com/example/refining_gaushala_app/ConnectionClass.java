package com.example.refining_gaushala_app;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

public class ConnectionClass {
    protected static String db = "green_gaushala";
    protected static String ip = "10.0.2.2";
    protected static String port = "3306";
    protected static String username = "root";
    protected static String password = "mysql5.7";

    public Connection CONN() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

//here sonoo is database name, root is username and password
            // Statement stmt = con.createStatement();
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+db, username, password);


        } catch (Exception e) {
            //.showMessageDialog(this, e.getMessage());
            // System.out.print(e);
            Log.e("Error", Objects.requireNonNull(e.getMessage()));
        }
        return conn;
    }

}
