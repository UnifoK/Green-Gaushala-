package com.example.refining_gaushala_app.network;


import com.example.refining_gaushala_app.models.Report;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ReportApi {

    @Multipart
    @POST("upload")
    Call<String> uploadImage(@Part MultipartBody.Part image);
    @GET("api/reports")
    Call<List<Report>> getAllReports();

    @POST("api/reports")
    Call<Report> createReport(@Body Report report);

    @GET("api/reports/{id}")
    Call<Report> getReportById(@Path("id") Long id);

    @DELETE("api/reports/{id}")
    Call<Void> deleteReport(@Path("id") Long id);
}
