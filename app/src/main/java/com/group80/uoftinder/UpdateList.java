package com.group80.uoftinder;

import com.group80.uoftinder.entities.User;

import java.util.ArrayList;
import java.util.List;

import com.group80.uoftinder.entities.User;

public class UpdateList extends User {
    private static User currentUser; // the current user

    /**
     * Constructor for UpdateList Class.
     * @param currentUser is the currentUser.
     */
    public UpdateList(User currentUser) {
        super(currentUser.getUid());
        this.currentUser = currentUser;
    }

    /**
     * Adds displayedUser to currentUser's viewed list.
     * If liked is true, also adds displayedUser to currentUser's liked list.
     * @param displayedUser is the user that is currently being displayed to currentUser.
     * @param liked is True when currentUser 'likes' displayedUser, False otherwise.
     * @param viewedList is a list of Users that currentUser has currently viewed
     * @param likedList is a list of Users that currentUser has currently liked
     */
    public static void addToList(User displayedUser, boolean liked, List viewedList, List likedList) {
        viewedList.add(displayedUser.getUid());
        currentUser.setViewed(viewedList);
        // push currentUser.getViewed() to firebase viewed list
        if (liked) {
            likedList.add(displayedUser.getUid());
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
