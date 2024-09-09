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

public class gaushalaReg extends AppCompatActivity {

    TextView ifLogin;
    Button Sign_up;
    EditText gau_name, gau_regis_no, location, dung_amt, email, phone, user_id, pswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gaushala_reg);

        // Initialize UI elements
        ifLogin = findViewById(R.id.ifLogin);
        Sign_up = findViewById(R.id.sign_up_button);
        gau_name = findViewById(R.id.gaushala_name_edit_text);
        gau_regis_no = findViewById(R.id.certificate_number_edit_text);
        location = findViewById(R.id.location_edit_text);
        dung_amt = findViewById(R.id.dung_production_edit_text);
        phone = findViewById(R.id.phone_number_edit_text);
        user_id = findViewById(R.id.user_id_edit_text);
        pswd = findViewById(R.id.password_edit_text);
        email = findViewById(R.id.email_edit_text); // Add this line

        // Handle "Login" text click
        ifLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gaushalaReg.this, gaushalaLogin.class);
                startActivity(intent);
            }
        });

        // Handle "Sign Up" button click
        Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String nameInput = gau_name.getText().toString();
                String registrationNumberInput = gau_regis_no.getText().toString();
                String locationInput = location.getText().toString();
                String dungAmountInput = dung_amt.getText().toString();
                String emailInput = email.getText().toString();
                String phoneInput = phone.getText().toString();
                String userIdInput = user_id.getText().toString();
                String passwordInput = pswd.getText().toString();

                // Validate input
                if (nameInput.isEmpty() || registrationNumberInput.isEmpty() || locationInput.isEmpty() ||
                        dungAmountInput.isEmpty() || emailInput.isEmpty() || phoneInput.isEmpty() ||
                        userIdInput.isEmpty() || passwordInput.isEmpty()) {
                    Toast.makeText(gaushalaReg.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create Gaushala object with user input
                Gaushala gaushala = new Gaushala(nameInput, registrationNumberInput, locationInput, dungAmountInput, emailInput, phoneInput, userIdInput, passwordInput);

                // Create AuthApi instance
                AuthApi authApi = RetrofitClient.getAuthApi();

                // Make API call to register gaushala
                Call<Gaushala> call = authApi.registerGaushala(gaushala);
                call.enqueue(new Callback<Gaushala>() {
                    @Override
                    public void onResponse(Call<Gaushala> call, Response<Gaushala> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(gaushalaReg.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            // Navigate to login screen
                            Intent intent = new Intent(gaushalaReg.this, gaushalaLogin.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(gaushalaReg.this, "Registration Failed: " + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Gaushala> call, Throwable t) {
                        Toast.makeText(gaushalaReg.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Handle edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
