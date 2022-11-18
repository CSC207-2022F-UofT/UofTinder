package com.group80.uoftinder;

import java.util.ArrayList;
import java.util.List;

public class UpdateList extends User {
    private User displayedUser;
    private boolean liked;
    private List<User> viewedList;
    private List<User> likedList;

    public UpdateList(User displayedUser, boolean liked) {
        super();
        this.displayedUser = displayedUser;
        this.liked = liked;
        this.viewedList = currentUser.getViewed();
        this.likedList = currentUser.getLiked();
    }
    public void addToList(User displayedUser, boolean liked) {
        viewedList.add(displayedUser);
        currentUser.setViewed(viewedList);
        // push currentUser.getViewed() to firebase viewed list
        if (liked) {
            likedList.add(displayedUser);
            currentUser.setLiked(likedList);
            // push currentUser.getLiked() to firebase liked list
        }
        // sending display user id to be added to firebase
        // if liked is true, push to liked list and viewed list
        // if liked is false, push to viewed list
        updateListStatus(displayedUser.getUid(), liked);
    }



}
