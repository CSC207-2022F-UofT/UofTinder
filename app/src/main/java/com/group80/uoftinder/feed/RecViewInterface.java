package com.group80.uoftinder.feed;

import com.group80.uoftinder.entities.User;

/**
 * A public interface that is implemented by RecommendationView.
 */
public interface RecViewInterface {
    // set first displayed user
    void setDisplayedUser(User displayedUser);
    // display user
    void showUser();
    // no more compatible users
    void noCompatibleUser();

}
