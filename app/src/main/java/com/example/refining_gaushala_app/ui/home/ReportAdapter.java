package com.example.refining_gaushala_app.ui.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.refining_gaushala_app.R;
import com.example.refining_gaushala_app.models.Report;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {


    private List<Report> reportList;

    public ReportAdapter(List<Report> reportList) {
        this.reportList = reportList;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_request, parent, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        Report report = reportList.get(position);

        // Set text data
        holder.textLocation.setText("Location: " + report.getLocation());
        holder.textTime.setText("Time Slot: " + report.getTimeSlot());
        holder.textName.setText("Reported By: " + report.getReportedBy());

        // Convert the byte[] image from the report into a Bitmap and display it
        byte[] imageBytes = Base64.decode(report.getImage(), Base64.DEFAULT); // Assuming the image is base64 encoded
        if (imageBytes != null && imageBytes.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.imagePhoto.setImageBitmap(bitmap);
        } else {
            // Fallback to default image if no image data is found
            holder.imagePhoto.setImageResource(R.drawable.samplecow);
        }
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public static class ReportViewHolder extends RecyclerView.ViewHolder {
        TextView textLocation, textTime, textName;
        ImageView imagePhoto;

        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            textLocation = itemView.findViewById(R.id.text_location);
            textTime = itemView.findViewById(R.id.text_time);
            textName = itemView.findViewById(R.id.text_name);
            imagePhoto = itemView.findViewById(R.id.image_photo);
        }
    }
}
