package com.abhi.firebasedemo;

public class CustomUserData {

    String username, fullName,profession;

    public CustomUserData() {
    }

    public CustomUserData(String username, String fullName, String profession) {
        this.username = username;
        this.fullName = fullName;
        this.profession = profession;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "CustomUserData{" +
                "username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }
}
