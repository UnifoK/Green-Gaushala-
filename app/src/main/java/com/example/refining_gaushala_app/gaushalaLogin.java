package com.example.refining_gaushala_app;

import android.content.Intent;
import android.os.Bundle;
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

public class gaushalaLogin extends AppCompatActivity {
    TextView txt_SignUp;
    EditText login_email;
    EditText login_password;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gaushala_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txt_SignUp = findViewById(R.id.bioplant_SignUp);

        txt_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gaushalaLogin.this, gaushalaReg.class);
                startActivity(intent);
            }
        });

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        btnlogin = findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = login_email.getText().toString();
                String password = login_password.getText().toString();

                // Implement authentication logic here
                if (username.equals("go_rak") && password.equals("go_rak123")) {
                    // Successful login
                    Intent intent = new Intent(gaushalaLogin.this, gaushalaNav.class);
                    startActivity(intent);
//
                    Toast.makeText(com.example.refining_gaushala_app.gaushalaLogin.this, "Login successful", Toast.LENGTH_SHORT).show();

                } else {
                    // Failed login
                    Toast.makeText(com.example.refining_gaushala_app.gaushalaLogin.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });


//        btnlogin=findViewById(R.id.btnlogin);
//        btnlogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(gaushalaLogin.this, gaushalaNav.class);
//                startActivity(intent);
//            }
//        });


    }
}