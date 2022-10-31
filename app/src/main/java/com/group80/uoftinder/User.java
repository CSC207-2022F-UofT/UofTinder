package com.group80.uoftinder;

public class User {

    private final String email;
    private final String username;
    private final String password;
    private final String userType;

    private int score;

    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String pronouns;

    User(String email, String username, String password, String type){
        this.email = email;
        this.username = username;
        this.password = password;
        this.userType = type;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName() {
        return firstName;
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

    public void setPronouns(String pronouns) {
        this.pronouns = pronouns;
    }
    public String getPronouns() {
        return pronouns;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
