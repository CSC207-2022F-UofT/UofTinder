package com.group80.uoftinder;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import android.service.autofill.FieldClassification;

import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.firebase.realtime.UserRealtimeDbFacade;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A MatchInteractorUnitTest class that tests the functionality of the MatchInteractor class
 */
public class MatchInteractorUnitTest {

    /**
     *
     */
    @Test
    public void checkMatchListsUpdatedLocal() {
        User user1 = new User("Raghav");
        User user2 = new User("Future partner");
        user1.getLiked().add(user2.getUid());
        user2.getLiked().add(user1.getUid());
        MatchInteractor.checkForMatchAndCreate(user1, user2);
        assert user1.getMatches().contains(user2.getUid());
        assert user2.getMatches().contains(user1.getUid());
    }

    /**
     *
     */
    @Test
    public void checkMatchListsUpdatedRemote() {
        UserRealtimeDbFacade.getUser(
                "Romantic", "user2",
                user1 -> {
                    UserRealtimeDbFacade.getUser(
                            "Romantic", "user3",
                            user2 -> {
                                user1.getLiked().add(user2.getUid());
                                user2.getLiked().add(user1.getUid());
                                MatchInteractor.checkForMatchAndCreate(user1, user2);
                                assert user1.getMatches().contains(user2.getUid());
                                assert user2.getMatches().contains(user1.getUid());
                            }
                    );
                }
        );
    }

    /**
     * Test when the currentUser does not 'like' the displayedUser.
     */
    @Test
    public void currUserNoLikeDisplayedUser() {
        // set the currentUser and displayedUser
        User currentUser = new User("currUser");
        User displayedUser  = new User("displayedUser");
        // make the currentUser's viewedList and likedList
        List<String> viewedList = new ArrayList<>();
        List<String> likedList = new ArrayList<>();

        // add displayedUser to viewedList but not likedList
        MatchInteractor.addToList(displayedUser, currentUser, false, viewedList, likedList);

        // expected results:  displayedUser in currentUser's visited list but not liked list.
        List<String> expectedLikedList = new ArrayList<>();
        List<String> expectedVisitedList = new ArrayList<>(Collections.singletonList(
                displayedUser.getUid()));

        // actual results:
        List<String> actualLikedList = currentUser.getLiked();
        List<String> actualVisitedList = currentUser.getViewed();

        // assert statements
        assertEquals(expectedLikedList, actualLikedList);
        assertEquals(expectedVisitedList, actualVisitedList);
    }


    /**
     * Test when the currentUser does 'like' the displayedUser.
     */
    @Test
    public void currUserLikeDisplayedUser() {
        // set the currentUser and displayedUser
        User currentUser = new User("currUser");
        User displayedUser  = new User("displayedUser");
        // make the currentUser's viewedList and likedList
        List<String> viewedList = new ArrayList<>();
        List<String> likedList = new ArrayList<>();
        // add displayedUser to viewedList and likedList
        MatchInteractor.addToList(displayedUser, currentUser, true, viewedList, likedList);

        // expected results:  displayedUser in currentUser's liked and visited list
        List<String> expectedLikedList = new ArrayList<>(Collections.singletonList(
                displayedUser.getUid()));
        List<String> expectedVisitedList = new ArrayList<>(Collections.singletonList(
                displayedUser.getUid()));

        // actual results:
        List<String> actualLikedList = currentUser.getLiked();
        List<String> actualVisitedList = currentUser.getViewed();

        // assert statements
        assertEquals(expectedLikedList, actualLikedList);
        assertEquals(expectedVisitedList, actualVisitedList);
    }

}
