package com.group80.uoftinder;

import java.util.ArrayList;
import java.util.List;

public class UpdateList extends User {
    private User currentUser; // the current user
    private User displayedUser; // the user that is displayed to current user
    private boolean liked; // true if currentUser 'likes' displayedUser, false otherwise
    private List<User> viewedList; // currentUser's viewedList
    private List<User> likedList; // currentUser's likedList

    /**
     * Constructor for UpdateList Class.
     * @param displayedUser is the user that is currently being displayed to currentUser.
     * @param liked is True when currentUser 'likes' displayedUser, False otherwise.
     */
    public UpdateList(User currentUser, User displayedUser, boolean liked) {
        super();
        this.currentUser = currentUser;
        this.displayedUser = displayedUser;
        this.liked = liked;
        this.viewedList = currentUser.getViewed();
        this.likedList = currentUser.getLiked();
    }

    /**
     * Adds displayedUser to currentUser's viewed list.
     * If liked is true, also adds displayedUser to currentUser's liked list.
     * @param displayedUser is the user that is currently being displayed to currentUser.
     * @param liked is True when currentUser 'likes' displayedUser, False otherwise.
     */
    public void addToList(User displayedUser, boolean liked) {
        viewedList.add(displayedUser);
        currentUser.setViewed(viewedList);
        // push currentUser.getViewed() to firebase viewed list
        if (liked) {
            likedList.add(displayedUser);
            currentUser.setLiked(likedList);
            // push currentUser.getLiked() to firebase liked list
        }
        // TODO: write a function that writes to currentUser's liked and visited list in firebase
        // if liked is true, push to liked list and viewed list
        // if liked is false, push to viewed list
        // sending display user id to be added to firebase
        // updateListStatus(displayedUser.getUid(), liked);
    }



}
