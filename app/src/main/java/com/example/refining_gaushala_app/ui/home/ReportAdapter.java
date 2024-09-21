package com.example.refining_gaushala_app.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide; // For image loading
import com.example.refining_gaushala_app.R; // For accessing resources (R)
import com.example.refining_gaushala_app.models.Report; // The Report model
import java.util.List; // To work with lists

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
        holder.textLocation.setText("Location: " + report.getLocation());
        holder.textTime.setText("Time Slot: " + report.getTimeSlot());
        holder.textName.setText("Reported By: " + report.getReportedBy());

        // Load the image using Glide
        Glide.with(holder.itemView.getContext())
                .load(report.getImageUrl())
                .placeholder(R.drawable.samplecow) // Placeholder image
                .into(holder.imagePhoto);
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
