package com.example.refining_gaushala_app;

import android.os.Bundle;
import android.widget.*;
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

public class bioplantReg extends AppCompatActivity {

    // Define UI elements
    EditText name, registrationNumber, location, phoneNumber, userId, password;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bioplant_reg);

        // Initialize UI elements
        name = findViewById(R.id.bioplant_name_edit_text);
        registrationNumber = findViewById(R.id.Registration_number_edit_text);
        location = findViewById(R.id.add_edit_text);
        phoneNumber = findViewById(R.id.phone_number_edit_text);
        userId = findViewById(R.id.user_id_edit_text);
        password = findViewById(R.id.password_edit_text);
        registerButton = findViewById(R.id.sign_up_button);

        // Handle register button click
        registerButton.setOnClickListener(v -> {
            // Get user input
            String nameInput = name.getText().toString();
            String registrationNumberInput = registrationNumber.getText().toString();
            String locationInput = location.getText().toString();
            String phoneNumberInput = phoneNumber.getText().toString();
            String userIdInput = userId.getText().toString();
            String passwordInput = password.getText().toString();

            // Validate input
            if (nameInput.isEmpty() || registrationNumberInput.isEmpty() || locationInput.isEmpty() ||
                    phoneNumberInput.isEmpty() || userIdInput.isEmpty() || passwordInput.isEmpty()) {
                Toast.makeText(bioplantReg.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create Bioplant object with user input
            Bioplant bioplant = new Bioplant(nameInput, registrationNumberInput, locationInput, phoneNumberInput, userIdInput, passwordInput);

            // Create AuthApi instance
            AuthApi authApi = RetrofitClient.getAuthApi();

            // Make API call to register bioplant
            Call<Bioplant> call = authApi.registerBioplant(bioplant);
            call.enqueue(new Callback<Bioplant>() {
                @Override
                public void onResponse(Call<Bioplant> call, Response<Bioplant> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(bioplantReg.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        // Navigate to login or main activity
                        // Intent intent = new Intent(bioplantReg.this, LoginActivity.class);
                        // startActivity(intent);
                    } else {
                        Toast.makeText(bioplantReg.this, "Registration Failed: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Bioplant> call, Throwable t) {
                    Toast.makeText(bioplantReg.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Handle edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
