package com.example.refining_gaushala_app.models;

public class Bioplant {
    private String name;
    private String registrationNumber;
    private String location;
    private String phoneNumber;
    private String userId;
    private String password;

    // Constructor
    public Bioplant(String name, String registrationNumber, String location, String phoneNumber, String userId, String password) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.password = password;
    }

    public Bioplant(String userId, String password){
        this.userId = userId;
        this.password = password;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
