package com.example.refining_gaushala_app.network;

import com.example.refining_gaushala_app.models.Gaushala;
import com.example.refining_gaushala_app.models.Report;
import com.example.refining_gaushala_app.ui.slideshow.GaushalaUpdateRequest;

import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ReportApi {

    // Fetch all gaushalas
    @GET("/api/gaushala/gaushalas")
    Call<List<Gaushala>> getAllGaushalas();

    // Create a new report with multipart image upload
    @Multipart
    @POST("/api/reports") // Ensure leading slash if base URL is set without one
    Call<Report> createReport(
            @Part("area") RequestBody area,
            @Part("timeSlot") RequestBody timeSlot,
            @Part("location") RequestBody location,
            @Part("reportedBy") RequestBody reportedBy,
            @Part MultipartBody.Part image // Image to be uploaded
    );

    // Fetch all reports
    @GET("/api/reports")
    Call<List<Report>> getAllReports();

    // Fetch a specific report by its ID
    @GET("/api/reports/{id}")
    Call<Report> getReportById(@Path("id") Long id);

    // Delete a specific report by its ID
    @DELETE("/api/reports/{id}")
    Call<Void> deleteReport(@Path("id") Long id);

    // Update the status of a report (partial update)
    @PATCH("/api/reports/{id}")
    Call<Report> updateReportStatus(
            @Path("id") Long id,
            @Body Report report // Updated report object
    );

    // Update a report completely by its ID
    @PUT("/api/reports/{id}")
    Call<Report> updateReport(
            @Path("id") Long id,
            @Body Report report // Entire report object for update
    );

    // Accept a report by a specific user
    // Accept a report by a specific user
    @PUT("/api/reports/{id}/accept")
    Call<Report> acceptReport(
            @Path("id") Long id,
            @Query("gaushalaId") Long gaushalaId // Include gaushalaId as a query parameter
    );


    // Update dung details for a specific Gaushala
    @PUT("/api/gaushala/update-dung/{id}")
    Call<Gaushala> updateDungDetails(
            @Path("id") Long gaushalaId, // ID of the Gaushala to update
            @Body GaushalaUpdateRequest request);

    @GET("/api/gaushala/dung/details/{id}")
    Call<GaushalaUpdateRequest> getDungDetails(@Path("id") Long gaushalaId);


}
