package com.group80.uoftinder.entities;

import android.net.Uri;

import java.util.HashSet;

public class User{

    private String email;
    private String uid;
    private String password;

    private String userType; // make this final later idk

    private int score;

    // this information is displayed on profile
    private String displayName; // last name and first name displayed on profile
    private Uri photoUrl;
    private int age;
    private String gender;

    private HashSet<Integer>[] answers;

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getUserType(){
        return userType;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }

    public Uri getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(Uri photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUid() {
        return uid;
    }

    public HashSet<Integer>[] getAnswers() {
        return answers;
    }

    public void setAnswers(HashSet<Integer>[] answers) {
        this.answers = answers;
    }
}
