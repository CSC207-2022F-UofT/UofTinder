package com.group80.uoftinder.entities;

import android.net.Uri;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class User {

    private final String uid;

    private String userType; // make this final later idk

    private int score;

    // this information is displayed on profile
    private String name; // last name and first name displayed on profile
    private Uri photoUrl;
    private int age;
    private String gender;

    private ArrayList<HashSet<Integer>> answers;
    private List<String> viewed;
    private List<String> liked;
    private List<String> matches;

    public User(String uid){
        this.uid = uid;
        this.liked = new ArrayList<>();
        this.viewed = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public void setUserType(String userType) {
        this.userType = userType;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public ArrayList<HashSet<Integer>> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<HashSet<Integer>> answers) {
        this.answers = answers;
    }

    public List<String> getViewed() {
        return viewed;
    }

    public void setViewed(List<String> viewed) {
        this.viewed = viewed;
    }

    public List<String> getLiked() {
        return liked;
    }

    public void setLiked(List<String> liked) {
        this.liked = liked;
    }

    public List<String> getMatches() {
        return matches;
    }

    public void setMatches(List<String> matches) {
        this.matches = matches;
    }
}
