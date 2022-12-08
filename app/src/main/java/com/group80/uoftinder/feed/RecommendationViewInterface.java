package com.group80.uoftinder.feed;

import com.group80.uoftinder.entities.User;

/**
 * A public interface that is implemented by RecommendationView.
 */
public interface RecommendationViewInterface {
    /**
     * Sets the user to display as displayed user.
     * @param displayedUser the user to display
     */
    void setDisplayedUser(User displayedUser);

    /**
     * Retrieves the current displayed user.
     * @return  the current displayed user
     */
    User getDisplayedUser();

    /**
     * Displays user that is set as displayed user right now.
     */
    void showUser();

    /**
     * Shows the case when no more compatible users are left.
     */
    void noCompatibleUser();

    /**
     * Creates a pop-up if a match has been created.
     */
    void createPopUp();
}
