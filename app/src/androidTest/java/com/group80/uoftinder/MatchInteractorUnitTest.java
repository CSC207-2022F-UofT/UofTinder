package com.group80.uoftinder;

import static org.junit.Assert.assertEquals;

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
     * Test to see if the match lists for two users are both updated in the local User classes
     */
    @Test
    public void checkMatchListsUpdatedLocal() {
        User user1 = new User("Raghav");
        User user2 = new User("Future partner");
        user1.setUserType("Academic");
        user2.setUserType("Academic");
        user1.getLiked().add(user2.getUid());
        user2.getLiked().add(user1.getUid());
        MatchInteractor.checkForMatchAndCreate(user1, user2);
        assert user1.getMatches().contains(user2.getUid());
        assert user2.getMatches().contains(user1.getUid());
    }

    /**
     * Test to see if the match lists for two users are both updated in the database upon match
     */
    @Test
    public void checkMatchListsUpdatedRemote() {
        User user1 = new User("user1");
        User user2 = new User("user2");
        user1.setUserType("Romantic");
        user2.setUserType("Romantic");
        user1.getLiked().add(user2.getUid());
        user2.getLiked().add(user1.getUid());
        MatchInteractor.checkForMatchAndCreate(user1, user2);
        UserRealtimeDbFacade.getUser(
                "Romantic", "user1",
                u1 -> {
                    UserRealtimeDbFacade.getUser(
                            "Romantic", "user2",
                            u2 -> {
                                assert u1.getMatches().contains(u2.getUid());
                                assert u2.getMatches().contains(u1.getUid());
                            }
                    );
                }
        );
    }

    /**
     * Test to see if the local User viewed and liked lists are updated when the currentUser
     * does not 'like' the displayedUser
     */
    @Test
    public void currUserSkipsDisplayedUserLocal() {
        // set the currentUser and displayedUser
        User currentUser = new User("currUser");
        User displayedUser  = new User("displayedUser");
        currentUser.setUserType("Academic");
        displayedUser.setUserType("Academic");

        // add displayedUser to viewedList but not likedList
        MatchInteractor.addToList(currentUser, displayedUser, false);

        // expected results: displayedUser in currentUser's visited list but not liked list.
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
     * Test to see if the local User viewed and liked lists are updated when the currentUser
     * 'likes' the displayedUser
     */
    @Test
    public void currUserLikesDisplayedUserLocal() {
        // set the currentUser and displayedUser
        User currentUser = new User("currUser");
        User displayedUser  = new User("displayedUser");
        currentUser.setUserType("Academic");
        displayedUser.setUserType("Academic");

        // add displayedUser to viewedList and likedList
        MatchInteractor.addToList(currentUser, displayedUser, true);

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
    /**
     * Test to see if the viewed and liked lists of the current user are updated in the database
     * when the currentUser does not 'like' the displayedUser
     */
    @Test
    public void currUserSkipsDisplayedUserRemote() {
        User currentUser = new User("user1");
        User displayedUser = new User("user2");
        currentUser.setUserType("Romantic");
        displayedUser.setUserType("Romantic");
        MatchInteractor.addToList(currentUser, displayedUser, false);
        UserRealtimeDbFacade.getUser(
                "Romantic", "user1",
                u1 -> {
                    UserRealtimeDbFacade.getUser(
                            "Romantic", "user2",
                            u2 -> {
                                assert u1.getViewed().contains(u2.getUid());
                            }
                    );
                }
        );
    }
    /**
     * Test to see if the viewed and liked lists of the current user are updated in the database
     * when the currentUser does not 'like' the displayedUser
     */
    @Test
    public void currUserLikesDisplayedUserRemote() {
        User currentUser = new User("user1");
        User displayedUser = new User("user2");
        currentUser.setUserType("Romantic");
        displayedUser.setUserType("Romantic");
        MatchInteractor.addToList(currentUser, displayedUser, true);
        UserRealtimeDbFacade.getUser(
                "Romantic", "user1",
                u1 -> {
                    UserRealtimeDbFacade.getUser(
                            "Romantic", "user2",
                            u2 -> {
                                assert u1.getViewed().contains(u2.getUid());
                                assert u1.getLiked().contains(u2.getUid());
                            }
                    );
                }
        );
    }
}
