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

import com.example.refining_gaushala_app.models.Bioplant;
import com.example.refining_gaushala_app.network.AuthApi;
import com.example.refining_gaushala_app.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class bioplantLogin extends AppCompatActivity {

    EditText userIdEditText, passwordEditText;
    Button loginButton;
    TextView bioplantSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bioplant_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI elements
        userIdEditText = findViewById(R.id.login_email);
        passwordEditText = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.biobtnlogin); // Make sure you have this button in your XML
        bioplantSignUp = findViewById(R.id.bioplant_SignUp);

        // Handle Sign Up button click
        bioplantSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(bioplantLogin.this, bioplantReg.class);
            startActivity(intent);
        });

        // Handle Login button click
        loginButton.setOnClickListener(v -> {
            String userId = userIdEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // Validate input
            if (userId.isEmpty() || password.isEmpty()) {
                Toast.makeText(bioplantLogin.this, "Please enter both user ID and password", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create Bioplant object for login (modify if your API expects a different payload)
            Bioplant bioplant = new Bioplant(null, null, null, null, userId, password);

            // Make API call to authenticate Bioplant
            AuthApi authApi = RetrofitClient.getAuthApi();
            Call<Bioplant> call = authApi.loginBioplant(bioplant);
            call.enqueue(new Callback<Bioplant>() {
                @Override
                public void onResponse(Call<Bioplant> call, Response<Bioplant> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(bioplantLogin.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        // Navigate to another activity or update UI as needed
                    } else {
                        Toast.makeText(bioplantLogin.this, "Login failed: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Bioplant> call, Throwable t) {
                    Toast.makeText(bioplantLogin.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
