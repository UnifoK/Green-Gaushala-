package com.example.refining_gaushala_app;
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class gaushalaReg extends AppCompatActivity {

    TextView ifLogin;
Button Sign_up;
EditText gau_name, gau_regis_no, location, dung_amt, email, phone, user_id, pswd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gaushala_reg);

        //connectDatabase();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ifLogin=findViewById(R.id.ifLogin);
        Sign_up=findViewById(R.id.sign_up_button);
        gau_name=findViewById(R.id.gaushala_name_edit_text);
        gau_regis_no=findViewById(R.id.certificate_number_edit_text);
        location=findViewById(R.id.location_edit_text);
        dung_amt=findViewById(R.id.dung_production_edit_text);
        phone=findViewById(R.id.phone_number_edit_text);
        user_id=findViewById(R.id.user_id_edit_text);
        pswd=findViewById(R.id.password_edit_text);
        ifLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gaushalaReg.this,gaushalaLogin.class);
                startActivity(intent);
            }
        });

        Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }


        });

    }


}