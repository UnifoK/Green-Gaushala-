package com.example.refining_gaushala_app;

import android.os.Bundle;
import java.sql.*;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;

public class ConnectionClass extends AppCompatActivity {

    TextView t1=findViewById(R.id.showTextView1);


    public static final String DATABASE_NAME = "temp";
    public static final String url = "jdbc:mysql://192.168.0.1:3306/" +
            DATABASE_NAME;
    public static final String username = "root", password = "root";

    public static final String TABLE_NAME = "hi";
//    public final String name_COLUMN = "name";
//    public final String place_COLUMN = "place";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_connection_class);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void utilFun() throws SQLException {
        new Thread(() -> {
            //do your work

            StringBuilder records = new StringBuilder();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();

                ResultSet rs = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
                while (rs.next()) {
                    records.append("Name: ").append(rs.getString(1)).append(", Place: ").append(rs.getString(3)).append("\n");
                }

                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // after the job is finished:

                    t1.setText(records.toString());
                }
            });
        }).start();


    }

    public static void addTemp(String name_str, String place_str) {
        new Thread(() -> {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                // add to RDS DB:

                statement.execute("INSERT INTO " + TABLE_NAME + "(name, place) VALUES('" + name_str + "', '" + place_str + "')");

                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}