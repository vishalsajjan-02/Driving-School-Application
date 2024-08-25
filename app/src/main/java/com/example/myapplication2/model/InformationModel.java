package com.example.myapplication2.model;

public class InformationModel {
    String username;
    String email;
    String address;
    String age;

    String phone;
    String url;

    public InformationModel() {

    }
    public InformationModel(String username, String email, String address, String age, String phone, String url) {
        this.username = username;
        this.email = email;
        this.address = address;
        this.age = age;
        this.phone = phone;
        this.url = url;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}