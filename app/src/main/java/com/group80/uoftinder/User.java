package com.group80.uoftinder;

public class User {
    private String name;
    private int age;
    private String location;
    private String bio;
    private String profilePicture;

    public User() {}

    public User(String profilePicture, String name, int age, String location, String bio) {
        this.profilePicture = profilePicture;
        this.name = name;
        this.age = age;
        this.location = location;
        this.bio = bio;

    }
    
    public String getProfilePicture() {
        return profilePicture;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public void addToList(User displayedUser, boolean b) {
    }
}
