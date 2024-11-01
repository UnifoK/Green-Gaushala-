package com.example.refining_gaushala_app.models;

public class Report {
    private Long id;                // Unique identifier for the report
    private String area;            // Area of the report
    private String timeSlot;        // Time slot for the report
    private String location;        // Location of the incident or report
    private String reportedBy;      // User who reported the issue
    private String image;           // Image URL or path associated with the report
    private Long gaushalaId;        // ID of the gaushala assigned to this report
    private String status;          // Status of the report (e.g., "pending", "accepted", "resolved")
    private AcceptedBy acceptedBy;        // ID of the gaushala that accepted the report

    // Constructor to initialize the Report object
    public Report(Long id, String area, String timeSlot, String location, String reportedBy, String image, Long gaushalaId, String status, AcceptedBy acceptedBy) {
        this.id = id;
        this.area = area;
        this.timeSlot = timeSlot;
        this.location = location;
        this.reportedBy = reportedBy;
        this.image = image;
        this.gaushalaId = gaushalaId;
        setStatus(status);  // Using setter for validation
        this.acceptedBy = acceptedBy;
    }

    // Getters and Setters
    public Long getId() {
        return id;
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

    public Long getGaushalaId() {
        return gaushalaId;
    }

    public void setGaushalaId(Long gaushalaId) {
        this.gaushalaId = gaushalaId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equals("pending") || status.equals("accepted") || status.equals("resolved")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid status value");
        }
    }

    public AcceptedBy getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(AcceptedBy acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", area='" + area + '\'' +
                ", timeSlot='" + timeSlot + '\'' +
                ", location='" + location + '\'' +
                ", reportedBy='" + reportedBy + '\'' +
                ", image='" + image + '\'' +
                ", gaushalaId=" + gaushalaId +
                ", status='" + status + '\'' +
                ", acceptedBy=" + acceptedBy +
                '}';
    }

    public class AcceptedBy {
        private Long userId;
        private String username;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        // Getters and setters...
    }
}
