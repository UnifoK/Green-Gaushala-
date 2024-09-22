package com.example.refining_gaushala_app.network;

import com.example.refining_gaushala_app.models.Report;
import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ReportApi {

    // Updated method to support multipart requests
    @Multipart
    @POST("api/reports")
    Call<Report> createReport(
            @Part("area") RequestBody area,
            @Part("timeSlot") RequestBody timeSlot,
            @Part("location") RequestBody location,
            @Part("reportedBy") RequestBody reportedBy,
            @Part MultipartBody.Part image // This is the image
    );

    @GET("api/reports")
    Call<List<Report>> getAllReports();

    @GET("api/reports/{id}")
    Call<Report> getReportById(@Path("id") Long id);

    @DELETE("api/reports/{id}")
    Call<Void> deleteReport(@Path("id") Long id);
}
