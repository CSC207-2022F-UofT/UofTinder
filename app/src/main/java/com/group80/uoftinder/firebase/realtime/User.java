package com.group80.uoftinder.firebase.realtime;

public class User {
    private String firstname;
    private String lastname;
    private String uid;
    private String userType;

    public User() {
    }

    public User(String firstname, String lastname, String pID, String userType) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.uid = pID;
        this.userType = userType;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}

