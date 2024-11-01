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
    private Map<Long, String> gaushalaMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.reports_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressBar = root.findViewById(R.id.progress_bar);
        reportList = new ArrayList<>();
        gaushalaMap = new HashMap<>();

        reportAdapter = new ReportAdapter(reportList, gaushalaMap, this::acceptReport);
        recyclerView.setAdapter(reportAdapter);

        fetchGaushalas(); // Fetch gaushala names first
        fetchReports(); // Then fetch reports from API

        return root;
    }

    private void fetchGaushalas() {
        progressBar.setVisibility(View.VISIBLE); // Show progress bar
        ReportApi reportApi = RetrofitClient.getRetrofitInstance().create(ReportApi.class);
        reportApi.getAllGaushalas().enqueue(new Callback<List<Gaushala>>() {
            @Override
            public void onResponse(Call<List<Gaushala>> call, Response<List<Gaushala>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (Gaushala gaushala : response.body()) {
                        gaushalaMap.put(gaushala.getId(), gaushala.getName());
                    }
                } else {
                    showToast("No gaushalas found");
                }
            }

            @Override
            public void onFailure(Call<List<Gaushala>> call, Throwable t) {
                showToast("Failed to load gaushalas");
                Log.e("HomeFragment", "Gaushala Fetch Error: ", t);
            }
        });
    }

    private void fetchReports() {
        progressBar.setVisibility(View.VISIBLE);
        ReportApi reportApi = RetrofitClient.getRetrofitInstance().create(ReportApi.class);
        reportApi.getAllReports().enqueue(new Callback<List<Report>>() {
            @Override
            public void onResponse(Call<List<Report>> call, Response<List<Report>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    reportList.clear();
                    reportList.addAll(response.body());
                    reportAdapter.notifyDataSetChanged();
                } else {
                    showToast("No reports found");
                }
            }

            @Override
            public void onFailure(Call<List<Report>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                showToast("Failed to load reports");
                Log.e("HomeFragment", "Report Fetch Error: ", t);
            }
        });
    }

    private void acceptReport(Report report) {
        report.setStatus("accepted"); // Update status directly

        ReportApi reportApi = RetrofitClient.getRetrofitInstance().create(ReportApi.class);
        reportApi.updateReportStatus(report.getId(), report).enqueue(new Callback<Report>() {
            @Override
            public void onResponse(Call<Report> call, Response<Report> response) {
                if (response.isSuccessful()) {
                    Log.d("ReportUpdate", "Response: " + response.body());
                    showToast("Report accepted: " + report.getLocation());
                    fetchReports(); // Refresh the UI
                } else {
                    Log.e("ReportUpdate", "Error code: " + response.code() + " Message: " + response.message());
                    showToast("Failed to accept report: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Report> call, Throwable t) {
                showToast("API call failed to accept report");
                Log.e("HomeFragment", "Report Accept Error: ", t);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}