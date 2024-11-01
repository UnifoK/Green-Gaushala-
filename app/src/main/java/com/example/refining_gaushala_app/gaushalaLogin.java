package com.example.refining_gaushala_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.refining_gaushala_app.models.Gaushala;
import com.example.refining_gaushala_app.network.AuthApi;
import com.example.refining_gaushala_app.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class gaushalaLogin extends AppCompatActivity {

    EditText loginUserId, loginPassword;
    Button btnLogin;
    Long gaushalaId;
    TextView txtSignUp;
    private static final String PREF_NAME = "MyPrefs";
    private static final String GAUSHALA_ID_KEY = "gaushalaId";
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
        loginUserId = findViewById(R.id.login_userId);
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
            String userId = loginUserId.getText().toString();
            String password = loginPassword.getText().toString();

            // Validate input
            if (userId.isEmpty() || password.isEmpty()) {
                Toast.makeText(gaushalaLogin.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create Gaushala object for login
            Gaushala gaushala = new Gaushala(userId, password);

            // Make API call to authenticate Gaushala
            AuthApi authApi = RetrofitClient.getAuthApi();
            Call<Gaushala> call = authApi.loginGaushala(gaushala);
            call.enqueue(new Callback<Gaushala>() {
                @Override
                public void onResponse(Call<Gaushala> call, Response<Gaushala> response) {
                    if (response.isSuccessful()) {
//                         Successful login
                        Log.d("gaushalaLogin", "Attempting to retrieve Gaushala ID from response body...");
                        if (response.body() != null) {
                            gaushalaId = response.body().getId();
                            Log.d("gaushalaLogin", "Retrieved Gaushala ID: " + gaushalaId);

                            SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putLong(GAUSHALA_ID_KEY, gaushalaId);
                            editor.apply();

                            Log.d("gaushalaLogin", "Gaushala ID saved to SharedPreferences.");
                        } else {
                            Log.d("gaushalaLogin", "Response body is null.");
                            Toast.makeText(gaushalaLogin.this, "Failed to retrieve Gaushala ID. Please try again.", Toast.LENGTH_SHORT).show();
                        }

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
