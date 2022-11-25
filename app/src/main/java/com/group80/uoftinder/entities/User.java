package com.group80.uoftinder.entities;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user that is connected to their corresponding FirebaseUser account
 * with a user id.
 */
public class User {

    private final String uid;

    private String userType; // make this final later idk

    private int score;

    // this information is displayed on profile
    private String name;
    private Uri photoUrl;
    private int age;
    private String gender;

    private List<List<Integer>> answers;
    private List<String> viewed;
    private List<String> liked;
    private List<String> matches;

    public User(String uid){
        this.uid = uid;
        this.liked = new ArrayList<>();
        this.viewed = new ArrayList<>();
        this.matches = new ArrayList<>();
        this.answers = new ArrayList<>();
    }

    /**
     * Returns user's id which corresponds to their FirebaseUser id
     * @return User id
     */
    public String getUid() {
        return uid;
    }

    /**
     * Return user type of the current user
     * @return user type
     */
    public String getUserType(){
        return userType;
    }

    /**
     * Sets the user type: Academic, Romantic, or Friendship
     * @param userType String that indicates the desired type of user: Academic, Romantic, or Friendship
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Return score of the current user.
     * Score will be used for determining matches and compatibility with other users.
     * @return user's score
     */
    public int getScore() {
        return score;
    }
    /**
     * Sets the score of user, score is used for calculating compatibility with other users
     * @param score integer that indicates the desired score of user
     */
    public void setScore(int score) {
        this.score = score;
    }

    // Information used for user's profile
    /**
     * Return display name of the current user
     * @return user's display name
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the user's display name
     * Display name is displayed in user profile
     * @param name integer that indicates the desired display name of user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return url of the profile picture of the current user
     * @return user type
     */
    public Uri getPhotoUrl() {
        return photoUrl;
    }

    /**
     * Sets the user's profile picture url
     * Profile picture is displayed in user profile
     * @param photoUrl Uri that is the url of the user's desired profile picture
     */
    public void setPhotoUrl(Uri photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * Return age of the current user
     * @return age of user
     */
    public int getAge() {
        return age;
    }
    /**
     * Sets the user's age
     * Age is displayed in user profile
     * @param age integer that indicates the desired age of user
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Return gender of the current user
     * @return gender
     */
    public String getGender() {
        return gender;
    }
    /**
     * Sets the user's gender
     * Gender is displayed in user profile
     * @param gender String that indicates the desired gender of user
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Return answers of user to questions
     * Used for calculating user score
     * @return user's answers to questions when setting up their account
     */
    public List<List<Integer>> getAnswers() {
        return answers;
    }
    /**
     * Sets user's answers that will used to calculate user score
     * @param answers answers of user to questions when setting up account
     */
    public void setAnswers(List<List<Integer>> answers) {
        this.answers = answers;
    }


    // Used to create matches with other users
    /**
     * Return list of users that the current user has viewed
     * @return viewed (visited) users of user
     */
    public List<String> getViewed() {
        return viewed;
    }
    /**
     * Sets list of users that the current user has viewed
     * @param viewed list of users viewed by current user
     */
    public void setViewed(List<String> viewed) {
        this.viewed = viewed;
    }

    /**
     * Return list of users that the current user has liked
     * @return liked users of user
     */
    public List<String> getLiked() {
        return liked;
    }
    /**
     * Sets list of users that the current user has liked
     * @param liked list of users liked by current user
     */
    public void setLiked(List<String> liked) {
        this.liked = liked;
    }

    /**
     * Return list of users that the current user is matched with
     * @return matches with user
     */
    public List<String> getMatches() {
        return matches;
    }
    /**
     * Sets list of users that the current user is matched with
     * @param matches list of users matched with current user
     */
    public void setMatches(List<String> matches) {
        this.matches = matches;
    }
}