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
        if ("accepted".equals(report.getStatus())) {
            Report.AcceptedBy acceptedBy = report.getAcceptedBy(); // Get the AcceptedBy object
            Long acceptedById = acceptedBy != null ? acceptedBy.getUserId() : null; // Retrieve userId safely
            String gaushalaName = acceptedById != null ? gaushalaMap.get(acceptedById) : null; // Retrieve name from map

            holder.textName.append("\nAccepted By: " + (gaushalaName != null ? gaushalaName : "Unknown Gaushala"));
        }

        // Convert the byte[] image from the report into a Bitmap and display it
        if (report.getImage() != null) {
            byte[] imageBytes = Base64.decode(report.getImage(), Base64.DEFAULT);
            if (imageBytes.length > 0) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                holder.imagePhoto.setImageBitmap(bitmap);
            } else {
                holder.imagePhoto.setImageResource(R.drawable.samplecow);
            }
        } else {
            holder.imagePhoto.setImageResource(R.drawable.samplecow);
        }

        // Set the status indicator color and visibility of the accept button
        holder.acceptButton.setVisibility("pending".equals(report.getStatus()) ? View.VISIBLE : View.GONE);
        holder.statusIndicator.setBackgroundResource("pending".equals(report.getStatus()) ? R.drawable.circle_red : R.drawable.circle_green);

        // Set up button click listener
        holder.acceptButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAcceptClick(report); // Notify the listener
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
