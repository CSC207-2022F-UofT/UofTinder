package com.group80.uoftinder;

import java.util.ArrayList;

public class UpdateList extends User {
    private User displayedUser;
    private boolean liked;
    private ArrayList<User> viewedList;
    private ArrayList<User> likedList;

    public UpdateList(User displayedUser, boolean liked) {
        super();
        this.displayedUser = displayedUser;
        this.liked = liked;
    }
    public void addToList(User displayedUser, boolean liked) {
        viewedList.add(displayedUser);
        if (liked) {
            likedList.add(displayedUser);
        }
    }



}
