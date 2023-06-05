package com.suhail.frutimarket.models;

public class RegisterRequest {
    private String full_name;
    private String email;
    private String password;
    private String gender;

    public RegisterRequest(String full_name, String email, String password, String gender) {
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public RegisterRequest() {
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
