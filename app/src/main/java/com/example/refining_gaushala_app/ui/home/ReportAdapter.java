package com.example.refining_gaushala_app.ui.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.refining_gaushala_app.R;
import com.example.refining_gaushala_app.models.Report;

import java.util.List;
import java.util.Map;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {

    private List<Report> reportList;
    private Map<Long, String> gaushalaMap; // Map to hold gaushalaId and gaushalaName
    private OnReportActionListener listener; // Define the listener

    // Interface for handling report actions
    public interface OnReportActionListener {
        void onAcceptClick(Report report);
    }

    public ReportAdapter(List<Report> reportList, Map<Long, String> gaushalaMap, OnReportActionListener listener) {
        this.reportList = reportList;
        this.gaushalaMap = gaushalaMap; // Initialize the map
        this.listener = listener; // Initialize the listener
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

        // Set acceptedBy field if the report is accepted
        if (report.getStatus().equals("accepted")) {
            String gaushalaName = gaushalaMap.get(report.getGaushalaId()); // Get gaushala name
            holder.textName.append("\nAccepted By: " + (gaushalaName != null ? gaushalaName : "Unknown Gaushala"));
        }

        // Convert the byte[] image from the report into a Bitmap and display it
        byte[] imageBytes = Base64.decode(report.getImage(), Base64.DEFAULT);
        if (imageBytes != null && imageBytes.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.imagePhoto.setImageBitmap(bitmap);
        } else {
            // Fallback to default image if no image data is found
            holder.imagePhoto.setImageResource(R.drawable.samplecow);
        }

        // Set the initial status indicator color
        if (report.getStatus().equals("pending")) {
            holder.statusIndicator.setBackgroundResource(R.drawable.circle_red);
            holder.acceptButton.setVisibility(View.VISIBLE);
        } else if (report.getStatus().equals("accepted")) {
            holder.statusIndicator.setBackgroundResource(R.drawable.circle_green);
            holder.acceptButton.setVisibility(View.GONE);
        }

        // Set up button click listener
        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onAcceptClick(report); // Notify the listener
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public static class ReportViewHolder extends RecyclerView.ViewHolder {
        TextView textLocation, textTime, textName;
        ImageView imagePhoto;
        Button acceptButton; // Add this line
        View statusIndicator; // Add this line for the status indicator

        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            textLocation = itemView.findViewById(R.id.text_location);
            textTime = itemView.findViewById(R.id.text_time);
            textName = itemView.findViewById(R.id.text_name);
            imagePhoto = itemView.findViewById(R.id.image_photo);
            acceptButton = itemView.findViewById(R.id.button_accept); // Initialize the button
            statusIndicator = itemView.findViewById(R.id.status_indicator); // Initialize the status indicator
        }
    }
}
