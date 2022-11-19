package com.group80.uoftinder.firebase.realtime;

public class User {
    private String firstname;
    private String lastname;
    private String uid;
    private String userType;

    public User() {}

    public User(String firstname, String lastname, String uid) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.uid = uid;
        this.userType = userType;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUid() {
        return uid;
    }

    public String getUserType() {
        return userType;
    }
}
