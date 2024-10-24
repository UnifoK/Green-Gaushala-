package com.example.refining_gaushala_app.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.refining_gaushala_app.R;
import com.example.refining_gaushala_app.models.Gaushala;
import com.example.refining_gaushala_app.models.Report;
import com.example.refining_gaushala_app.network.ReportApi;
import com.example.refining_gaushala_app.network.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReportAdapter reportAdapter;
    private List<Report> reportList;
    private ProgressBar progressBar;
    private Map<Long, String> gaushalaMap; // Declare the gaushalaMap

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.reports_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressBar = root.findViewById(R.id.progress_bar); // Initialize progress bar

        reportList = new ArrayList<>();
        gaushalaMap = new HashMap<>(); // Initialize the gaushala map
        reportAdapter = new ReportAdapter(reportList, gaushalaMap, new ReportAdapter.OnReportActionListener() {
            @Override
            public void onAcceptClick(Report report) {
                acceptReport(report);
            }
        });
        recyclerView.setAdapter(reportAdapter);

        fetchGaushalas(); // Fetch gaushala names first
        fetchReports(); // Then fetch reports from API

        return root;
    }

    private void fetchGaushalas() {
        // Make an API call to fetch gaushalas (assuming you have this API set up)
        // This is a placeholder; replace it with your actual API call
        RetrofitClient.getRetrofitInstance().create(ReportApi.class).getAllGaushalas().enqueue(new Callback<List<Gaushala>>() {
            @Override
            public void onResponse(Call<List<Gaushala>> call, Response<List<Gaushala>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Populate the gaushalaMap with gaushalaId and gaushalaName
                    for (Gaushala gaushala : response.body()) {
                        gaushalaMap.put(gaushala.getId(), gaushala.getName());
                    }
                } else {
                    Toast.makeText(getContext(), "No gaushalas found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Gaushala>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to load gaushalas", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchReports() {
        progressBar.setVisibility(View.VISIBLE); // Show loading indicator
        RetrofitClient.getRetrofitInstance().create(ReportApi.class).getAllReports().enqueue(new Callback<List<Report>>() {
            @Override
            public void onResponse(Call<List<Report>> call, Response<List<Report>> response) {
                progressBar.setVisibility(View.GONE); // Hide loading indicator
                if (response.isSuccessful() && response.body() != null) {
                    reportList.clear();
                    reportList.addAll(response.body());
                    reportAdapter.notifyDataSetChanged(); // Refresh the RecyclerView
                } else {
                    Toast.makeText(getContext(), "No reports found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Report>> call, Throwable t) {
                progressBar.setVisibility(View.GONE); // Hide loading indicator
                Toast.makeText(getContext(), "Failed to load reports", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void acceptReport(Report report) {
        // Create a new Report object with the updated status
        Report updatedReport = new Report();
        updatedReport.setId(report.getId());
        updatedReport.setArea(report.getArea());
        updatedReport.setTimeSlot(report.getTimeSlot());
        updatedReport.setLocation(report.getLocation());
        updatedReport.setReportedBy(report.getReportedBy());
        updatedReport.setImage(report.getImage());
        updatedReport.setStatus("accepted"); // Set the status to "accepted"

        // Call API to update report status
        ReportApi reportApi = RetrofitClient.getRetrofitInstance().create(ReportApi.class);
        reportApi.updateReportStatus(report.getId(), updatedReport).enqueue(new Callback<Report>() {
            @Override
            public void onResponse(Call<Report> call, Response<Report> response) {
                if (response.isSuccessful()) {
                    // Log the successful response for debugging
                    Log.d("ReportUpdate", "Response: " + response.body());

                    // Get the updated report object from the response
                    Report updatedReport = response.body();

                    // Check if the updated report is not null
                    if (updatedReport != null) {
                        // Show a toast message
                        Toast.makeText(getContext(), "Report accepted: " + updatedReport.getLocation(), Toast.LENGTH_SHORT).show();

                        // Optionally refresh the report list or update the UI with the new report data
                        fetchReports(); // Ensure this method correctly updates the UI
                    } else {
                        Toast.makeText(getContext(), "Report update response is empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Log the error response code
                    Log.e("ReportUpdate", "Error code: " + response.code() + " Message: " + response.message());
                    Toast.makeText(getContext(), "Failed to accept report: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Report> call, Throwable t) {
                Toast.makeText(getContext(), "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
