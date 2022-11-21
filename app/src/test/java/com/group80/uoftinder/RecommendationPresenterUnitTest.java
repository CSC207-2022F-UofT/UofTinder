package com.group80.uoftinder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.group80.uoftinder.feed.RecommendationPresenter;
import com.group80.uoftinder.entities.User;

import org.junit.Test;

public class RecommendationPresenterUnitTest {

    @Test
    public void displayMostCompUserTest() {
        User curUser = new User("curUser");
        RecommendationView recView = new RecommendationView(curUser);
        RecommendationPresenter recPresenter = new RecommendationPresenter(recView);
        User user2 = new User("user2");
        recView.setDisplayedUser(null);
        recPresenter.displayMostCompUser(user2);
        User actual = recView.getDisplayedUser();
        assertEquals(actual, user2);
    }

    @Test
    public void displayNoCompatibleUserTest() {
        User curUser = new User("curUser");
        RecommendationView recView = new RecommendationView(curUser);
        RecommendationPresenter recPresenter = new RecommendationPresenter(recView);
        recPresenter.displayNoCompatibleUser();
        User actual = recView.getDisplayedUser();
        assertNull(actual);
    }
}
