package com.example.musicapp.model;


import java.util.UUID;

public class User {
    private String id  ;
    private String username;
    private String email;
    private String dob;
    private String phone;
    private String password;

    public User() {
        this.id = UUID.randomUUID().toString();
    }

    public User(String username, String email, String dob) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
        this.password = password;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

