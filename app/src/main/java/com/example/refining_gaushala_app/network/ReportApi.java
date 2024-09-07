package com.example.refining_gaushala_app.network;


import com.example.refining_gaushala_app.models.Report;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReportApi {

    @GET("api/reports")
    Call<List<Report>> getAllReports();

    @POST("api/reports")
    Call<Report> createReport(@Body Report report);

    @GET("api/reports/{id}")
    Call<Report> getReportById(@Path("id") Long id);

    @DELETE("api/reports/{id}")
    Call<Void> deleteReport(@Path("id") Long id);
}
