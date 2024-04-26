package com.example.refining_gaushala_app;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.dhaval2404.imagepicker.ImagePicker;

public class MainActivity extends AppCompatActivity {

   ImageView imageView ;
    Button uploadImage ;
    Button signGaushala;
    Button signBio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.imageView1);
        uploadImage =findViewById(R.id.uploadImage);

    uploadImage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ImagePicker.with(MainActivity.this)
                    .crop()	    			//Crop image(Optional), Check Customization for more option
                    .compress(1024)			//Final image size will be less than 1 MB(Optional)
                    .maxResultSize(400, 400)	//Final image resolution will be less than 1080 x 1080(Optional)
                    .start();
        }
    });

    signBio= findViewById(R.id.signBio);
    signGaushala =findViewById(R.id.signGaushala);


    signBio.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, gaushalaNav.class);
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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri =data.getData();
        imageView.setImageURI(uri);
    }


}