package com.example.refining_gaushala_app;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.dhaval2404.imagepicker.ImagePicker;

import java.sql.Connection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    ConnectionClass connectionClass;
    Connection con;
    String str;
    ImageView imageView;

    EditText area, timeSlot, location, reportedBy;
    Button uploadImage, report_button;
    Button signGaushala;
    Button signBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        connectionClass = new ConnectionClass();
        connectDatabase();
        imageView = findViewById(R.id.imageView1);
        uploadImage = findViewById(R.id.uploadImage);

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(MainActivity.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(400, 400)    //Final image resolution will be less than 1080 x 1080(Optional)
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
                if (Area.equals("") && Location.equals("") && Reported.equals("") && TimeSlot.equals(""))
                    Toast.makeText(com.example.refining_gaushala_app.MainActivity.this, "Incomplete Information", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(com.example.refining_gaushala_app.MainActivity.this, "Reported", Toast.LENGTH_SHORT).show();
                area.setText("");
                location.setText("");
                reportedBy.setText("");
                timeSlot.setText("");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        Toast.makeText(com.example.refining_gaushala_app.MainActivity.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
        //   imageView.setImageURI(uri);
    }

    private void connectDatabase() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                con = connectionClass.CONN();
                if (con == null) {
                    str = "Error in connection with Mysql server";
                } else {
                    str = "Connected with Mysql server";
                }
                //Toast.makeText(null, str, Toast.LENGTH_SHORT).show(); //problem arises from adding this lil shit
                Log.e("from code->", str);
                //maybe problem is arising by Connection Class calling
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

//                    runOnUiThread(()->{
//                        try {
//                            Thread.sleep(1000);
//                        }catch(InterruptedException e){
//                            e.printStackTrace();
//                        }
//
//                    });
        });
    }

}