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

import com.example.refining_gaushala_app.models.Gaushala;
import com.example.refining_gaushala_app.network.AuthApi;
import com.example.refining_gaushala_app.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class gaushalaLogin extends AppCompatActivity {

    EditText loginEmail, loginPassword;
    Button btnLogin;
    TextView txtSignUp;

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

        // Initialize UI elements
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.Gbtnlogin);
        txtSignUp = findViewById(R.id.gaushala_SignUp); // Update the ID to match your layout

        // Handle Sign Up click
        txtSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(gaushalaLogin.this, gaushalaReg.class);
            startActivity(intent);
        });

        // Handle Login button click
        btnLogin.setOnClickListener(v -> {
            String email = loginEmail.getText().toString();
            String password = loginPassword.getText().toString();

            // Validate input
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(gaushalaLogin.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create Gaushala object for login
            Gaushala gaushala = new Gaushala(email, password);

            // Make API call to authenticate Gaushala
            AuthApi authApi = RetrofitClient.getAuthApi();
            Call<Gaushala> call = authApi.loginGaushala(gaushala);
            call.enqueue(new Callback<Gaushala>() {
                @Override
                public void onResponse(Call<Gaushala> call, Response<Gaushala> response) {
                    if (response.isSuccessful()) {
                        // Successful login
                        Intent intent = new Intent(gaushalaLogin.this, gaushalaNav.class);
                        startActivity(intent);
                        Toast.makeText(gaushalaLogin.this, "Login successful", Toast.LENGTH_SHORT).show();
                    } else {
                        // Failed login
                        Toast.makeText(gaushalaLogin.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Gaushala> call, Throwable t) {
                    Toast.makeText(gaushalaLogin.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}

//
