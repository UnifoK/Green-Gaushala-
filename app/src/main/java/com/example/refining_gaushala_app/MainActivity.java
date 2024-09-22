package com.example.refining_gaushala_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.refining_gaushala_app.models.Report;
import com.example.refining_gaushala_app.network.ReportApi;
import com.example.refining_gaushala_app.network.RetrofitClient;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    String str;
    ImageView imageView;
    private Uri imageUri; // To store the selected image URI

    EditText area, timeSlot, location, reportedBy;
    Button uploadImage, report_button;
    Button signGaushala;
    Button signBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ReportApi reportApi = RetrofitClient.getRetrofitInstance().create(ReportApi.class);
        Call<List<Report>> call = reportApi.getAllReports();

        imageView = findViewById(R.id.imageView1);
        uploadImage = findViewById(R.id.uploadImage);

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(MainActivity.this)
                        .crop()                    // Crop image (Optional)
                        .compress(1024)            // Compress image (Optional)
                        .maxResultSize(400, 400)    // Max resolution (Optional)
                        .start();
            }
        });

        signBio = findViewById(R.id.signBio);
        signGaushala = findViewById(R.id.signGaushala);

        signBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, bioplantLogin.class);
                startActivity(intent);
            }
        });

        signGaushala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, gaushalaLogin.class);
                startActivity(intent);
            }
        });

        area = findViewById(R.id.area_code_edit_text);
        location = findViewById(R.id.address_edit_text);
        reportedBy = findViewById(R.id.uploader_name_edit_text);
        timeSlot = findViewById(R.id.timeSlot);
        report_button = findViewById(R.id.report_button);

        report_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Area = area.getText().toString();
                String Location = location.getText().toString();
                String Reported = reportedBy.getText().toString();
                String TimeSlot = timeSlot.getText().toString();

                if (Area.equals("") || Location.equals("") || Reported.equals("") || TimeSlot.equals("") || imageUri == null) {
                    Toast.makeText(MainActivity.this, "Incomplete Information", Toast.LENGTH_SHORT).show();
                } else {
                    // Create the report fields as RequestBody
                    RequestBody areaBody = RequestBody.create(MediaType.parse("text/plain"), Area);
                    RequestBody locationBody = RequestBody.create(MediaType.parse("text/plain"), Location);
                    RequestBody reportedByBody = RequestBody.create(MediaType.parse("text/plain"), Reported);
                    RequestBody timeSlotBody = RequestBody.create(MediaType.parse("text/plain"), TimeSlot);

                    // Prepare the image file for upload
                    File imageFile = new File(imageUri.getPath()); // Use the correct path to the image file
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageFile);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("image", imageFile.getName(), requestFile);

                    // Make the API call to create a new report with the multipart data
                    ReportApi reportApi = RetrofitClient.getRetrofitInstance().create(ReportApi.class);
                    Call<Report> call = reportApi.createReport(areaBody, timeSlotBody, locationBody, reportedByBody, body);
                    call.enqueue(new Callback<Report>() {
                        @Override
                        public void onResponse(Call<Report> call, Response<Report> response) {
                            if (response.isSuccessful()) {
                                // Handle successful response
                                Toast.makeText(MainActivity.this, "Report created successfully!", Toast.LENGTH_SHORT).show();
                            } else {
                                // Handle unsuccessful response
                                Toast.makeText(MainActivity.this, "Failed to create report: " + response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Report> call, Throwable t) {
                            // Handle network failure
                            Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    // Clear the input fields after submission
                    area.setText("");
                    location.setText("");
                    reportedBy.setText("");
                    timeSlot.setText("");
                    imageView.setImageURI(null); // Clear the image view
                    imageUri = null; // Clear the image URI
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
          //  imageView.setImageURI(imageUri); // Display the selected image
            Toast.makeText(MainActivity.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
