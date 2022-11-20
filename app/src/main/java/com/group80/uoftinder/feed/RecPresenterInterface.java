package com.group80.uoftinder.feed;

import com.group80.uoftinder.entities.User;

/**
 * An interface that is implemented by the RecommendationPresenter
 */
public interface RecPresenterInterface {
    void displayMostCompUser(User user);
    void displayNoCompatibleUser();
}
