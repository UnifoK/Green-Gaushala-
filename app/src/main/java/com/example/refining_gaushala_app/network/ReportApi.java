package com.example.refining_gaushala_app.network;

import com.example.refining_gaushala_app.models.Gaushala;
import com.example.refining_gaushala_app.models.Report;
import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ReportApi {

    // New method to fetch all gaushalas
    @GET("gaushalas") // Adjust this endpoint to match your backend API
    Call<List<Gaushala>> getAllGaushalas(); // Make sure to return a List of Gaushala objects

    // Method to create a new report with multipart image upload
    @Multipart
    @POST("api/reports")
    Call<Report> createReport(
            @Part("area") RequestBody area,
            @Part("timeSlot") RequestBody timeSlot,
            @Part("location") RequestBody location,
            @Part("reportedBy") RequestBody reportedBy,
            @Part MultipartBody.Part image // This is the image being uploaded
    );

    // Method to fetch all reports
    @GET("api/reports")
    Call<List<Report>> getAllReports();

    // Method to fetch a specific report by its ID
    @GET("api/reports/{id}")
    Call<Report> getReportById(@Path("id") Long id);

    // Method to delete a specific report by its ID
    @DELETE("api/reports/{id}")
    Call<Void> deleteReport(@Path("id") Long id);

    // Method to update the status of a report (partial update using PATCH)
    @PATCH("api/reports/{id}")
    Call<Report> updateReportStatus(
            @Path("id") Long id,
            @Body Report report // Sending the updated report object to change its status
    );

    // Method to update a report completely by its ID
    @PUT("api/reports/{id}")
    Call<Report> updateReport(
            @Path("id") Long id,
            @Body Report report // Sending the entire report object for update
    );

    // New method to accept a report by a specific user
    @PUT("api/reports/{id}/accept")
    Call<Report> acceptReport(
            @Path("id") Long id,
            @Query("acceptedBy") String acceptedBy // Sending the name of the user accepting the report
    );


}
