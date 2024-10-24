package com.example.refining_gaushala_app.models;

public class Gaushala {
    private Long id;
    private String name;
    private String registrationNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String location;
    private String dungAmount;
    private String email;
    private String phone;
    private String userId;
    private String password;

    // Default constructor
    public Gaushala() {}

    // Parameterized constructor
    public Gaushala(String name, String registrationNumber, String location, String dungAmount, String email, String phone, String userId, String password) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.location = location;
        this.dungAmount = dungAmount;
        this.email = email;
        this.phone = phone;
        this.userId = userId;
        this.password = password;
    }

    public Gaushala(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
    // Getters and setters
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

    public String getDungAmount() {
        return dungAmount;
    }

    public void setDungAmount(String dungAmount) {
        this.dungAmount = dungAmount;
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
