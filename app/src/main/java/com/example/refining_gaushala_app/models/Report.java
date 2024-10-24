package com.example.refining_gaushala_app.models;

public class Report {
    private Long id;                // Unique identifier for the report
    private String area;            // Area of the report
    private String timeSlot;        // Time slot for the report
    private String location;        // Location of the incident or report
    private String reportedBy;      // User who reported the issue
    private String image;           // Image URL or path associated with the report
    private Long gaushalaId;
    private String status;          // Status of the report (e.g., "pending", "accepted", "resolved")

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Long getGaushalaId() {
        return gaushalaId;
    }

    public void setGaushalaId(Long gaushalaId) {
        this.gaushalaId = gaushalaId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status; // New getter for status
    }

    public void setStatus(String status) {
        this.status = status; // New setter for status
    }
}
