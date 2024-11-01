package com.example.refining_gaushala_app.ui.slideshow;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.refining_gaushala_app.R;
import com.example.refining_gaushala_app.databinding.FragmentSlideshowBinding;
import com.example.refining_gaushala_app.models.Gaushala;
import com.example.refining_gaushala_app.network.RetrofitClient;
import com.example.refining_gaushala_app.network.ReportApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private static final String TAG = "SlideshowFragment";

    private TextView textCurrentStockDry, textCurrentStockFresh;
    private EditText editStockQuantity, editPricePerUnit;
    private Spinner spinnerResourceType;
    private Button btnSave;

    private Long CgaushalaId;
    private long gaushalaId=1;
    private double currentFreshDungAmount;
    private double currentDryDungAmount;

    private double currentFreshDungPrice;
    private double currentDryDungPrice;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize views
        textCurrentStockDry = root.findViewById(R.id.text_current_stock_dry);
        textCurrentStockFresh = root.findViewById(R.id.text_current_stock_fresh);
        editStockQuantity = root.findViewById(R.id.edit_stock_quantity);
        editPricePerUnit = root.findViewById(R.id.edit_price_per_unit);
        spinnerResourceType = root.findViewById(R.id.spinner_resource_type);
        btnSave = root.findViewById(R.id.btn_save);

        // Retrieve Gaushala ID from shared preferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        CgaushalaId = sharedPreferences.getLong("gaushalaId", -1L); // Default to -1L if not found

        fetchDungDetails();

        // Set up button click listener
        btnSave.setOnClickListener(v -> {
            fetchDungDetails();
            saveStockDetails();
            Log.d(TAG, "Save button clicked");

        });

        return root;
    }

    private void saveStockDetails() {
        String resourceType = spinnerResourceType.getSelectedItem().toString();
        String stockQuantityStr = editStockQuantity.getText().toString();
        String pricePerUnitStr = editPricePerUnit.getText().toString();

        // Validate input
        if (!stockQuantityStr.isEmpty() && !pricePerUnitStr.isEmpty()) {
            try {
                // Convert inputs to double
                double stockQuantity = Double.parseDouble(stockQuantityStr);
                double pricePerUnit = Double.parseDouble(pricePerUnitStr);

                // Fetch existing amounts before updating
                double existingFreshDungAmount = getCurrentFreshDungAmount();
                Log.d(TAG, "existingFreshDungAmount: "+existingFreshDungAmount);
                double existingDryDungAmount = getCurrentDryDungAmount();
                Log.d(TAG, "existingDryDungAmount: "+existingDryDungAmount);
                double existingFreshDungPrice = getCurrentFreshDungPrice();
                Log.d(TAG, "existingFreshDungPrice: "+existingFreshDungPrice);
                double existingDryDungPrice = getCurrentDryDungPrice();
                Log.d(TAG, "existingDryDungPrice: "+existingDryDungPrice);

                // Determine which dung type is being updated
                double freshDungAmount = resourceType.equals("Fresh") ? stockQuantity : existingFreshDungAmount;
                double dryDungAmount = resourceType.equals("Dry") ? stockQuantity : existingDryDungAmount;

                double freshDungPrice = resourceType.equals("Fresh") ? pricePerUnit : existingFreshDungPrice;
                double dryDungPrice = resourceType.equals("Dry") ? pricePerUnit : existingDryDungPrice;

                // Call the update method
                updateDungDetails(CgaushalaId, freshDungAmount, dryDungAmount, freshDungPrice, dryDungPrice);


            } catch (NumberFormatException e) {
                Toast.makeText(getActivity(), "Invalid number format", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Number format exception: " + e.getMessage());
            }
        } else {
            Toast.makeText(getActivity(), "Please enter valid stock details", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateDungDetails(Long gaushalaId, double freshDungAmount, double dryDungAmount, double freshDungPrice, double dryDungPrice) {
        // Initialize Retrofit API service
        ReportApi apiService = RetrofitClient.getRetrofitInstance().create(ReportApi.class);

        // Log the request details
        Log.d(TAG, "Updating Dung Details: Gaushala ID = " + gaushalaId + ", Fresh Dung = " + freshDungAmount + ", Dry Dung = " + dryDungAmount + ", Fresh Price = " + freshDungPrice + ", Dry Price = " + dryDungPrice);

        // Create request object
        GaushalaUpdateRequest request = new GaushalaUpdateRequest();
        request.setFreshDungAmount(freshDungAmount);
        request.setDryDungAmount(dryDungAmount);
        request.setFreshDungPrice(freshDungPrice);
        request.setDryDungPrice(dryDungPrice);

        // Call the API to update dung details
        Call<Gaushala> call = apiService.updateDungDetails(gaushalaId, request);

        call.enqueue(new Callback<Gaushala>() {
            @Override
            public void onResponse(Call<Gaushala> call, Response<Gaushala> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(getActivity(), "Stock details updated successfully!", Toast.LENGTH_SHORT).show();

                    // Update current stock display logic
                    currentFreshDungAmount = response.body().getFreshDungAmount(); // Assuming response contains updated values
                    currentDryDungAmount = response.body().getDryDungAmount();
                    currentFreshDungPrice = response.body().getFreshDungPrice();
                    currentDryDungPrice = response.body().getDryDungPrice();

                    textCurrentStockFresh.setText("Fresh Stock: " + currentFreshDungAmount + " kg");
                    textCurrentStockDry.setText("Dry Stock: " + currentDryDungAmount + " kg");
                } else {
                    Toast.makeText(getActivity(), "Failed to update stock details", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Response code: " + response.code() + ", Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Gaushala> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "API call failure: " + t.getMessage());
            }
        });
        fetchDungDetails();
    }

    private void fetchDungDetails() {
        ReportApi apiService = RetrofitClient.getRetrofitInstance().create(ReportApi.class);
        Call<GaushalaUpdateRequest> call = apiService.getDungDetails(gaushalaId);

        call.enqueue(new Callback<GaushalaUpdateRequest>() {
            @Override
            public void onResponse(Call<GaushalaUpdateRequest> call, Response<GaushalaUpdateRequest> response) {
                if (response.isSuccessful() && response.body() != null) {
                    GaushalaUpdateRequest dungDetails = response.body();
                    Log.d(TAG, "Fetched Dung Details: " + dungDetails.toString()); // Log the fetched details
                    currentFreshDungAmount = dungDetails.getFreshDungAmount();
                    currentDryDungAmount = dungDetails.getDryDungAmount();
                    currentFreshDungPrice = dungDetails.getFreshDungPrice();
                    currentDryDungPrice = dungDetails.getDryDungPrice();
                    textCurrentStockFresh.setText("Fresh Stock: " + currentFreshDungAmount + " kg");
                    textCurrentStockDry.setText("Dry Stock: " + currentDryDungAmount + " kg");
                } else {
                    Log.e(TAG, "Failed to fetch dung details: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<GaushalaUpdateRequest> call, Throwable t) {
                Log.e(TAG, "API call failure: " + t.getMessage());
            }
        });
    }

    private double getCurrentFreshDungAmount() {
        return currentFreshDungAmount; // Return the fetched fresh dung amount
    }

    private double getCurrentDryDungAmount() {
        return currentDryDungAmount; // Return the fetched dry dung amount
    }

    private double getCurrentFreshDungPrice() {
        return currentFreshDungPrice; // Store and return fetched fresh dung price
    }

    private double getCurrentDryDungPrice() {
        return currentDryDungPrice; // Store and return fetched dry dung price
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
