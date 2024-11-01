package com.example.refining_gaushala_app.models;

public class Gaushala {
    private Long id;
    private String name;
    private String registrationNumber;
    private String location;
    private String email;
    private String phone;
    private String userId;
    private String password;
    private double dungAmount;

    // Added fields for dung details
    private double freshDungAmount; // Amount of fresh dung
    private double dryDungAmount;   // Amount of dry dung
    private double freshDungPrice;  // Price per unit of fresh dung
    private double dryDungPrice;    // Price per unit of dry dung

    // Default constructor
    public Gaushala() {}

    public Gaushala(String name, String registrationNumber, String location,
                    String dungAmount,String email, String phone, String userId, String password) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.location = location;
        this.dungAmount = Double.parseDouble(dungAmount);
        this.email = email;
        this.phone = phone;
        this.userId = userId;
        this.password = password;
    }

    // Parameterized constructor for all fields
    public Gaushala(String name, String registrationNumber, String location,
                    double freshDungAmount, double dryDungAmount,
                    double freshDungPrice, double dryDungPrice,
                    String email, String phone, String userId, String password) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.location = location;
        this.freshDungAmount = freshDungAmount;
        this.dryDungAmount = dryDungAmount;
        this.freshDungPrice = freshDungPrice;
        this.dryDungPrice = dryDungPrice;
        this.email = email;
        this.phone = phone;
        this.userId = userId;
        this.password = password;
    }

    // Constructor for userId and password
    public Gaushala(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    // Getters and setters for the new fields
    public double getFreshDungAmount() {
        return freshDungAmount;
    }

    public void setFreshDungAmount(double freshDungAmount) {
        this.freshDungAmount = freshDungAmount;
    }

    public double getDryDungAmount() {
        return dryDungAmount;
    }

    public void setDryDungAmount(double dryDungAmount) {
        this.dryDungAmount = dryDungAmount;
    }

    public double getFreshDungPrice() {
        return freshDungPrice;
    }

    public void setFreshDungPrice(double freshDungPrice) {
        this.freshDungPrice = freshDungPrice;
    }

    public double getDryDungPrice() {
        return dryDungPrice;
    }

    public void setDryDungPrice(double dryDungPrice) {
        this.dryDungPrice = dryDungPrice;
    }

    // Existing getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
