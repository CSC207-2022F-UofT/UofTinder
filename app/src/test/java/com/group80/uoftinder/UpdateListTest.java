package com.group80.uoftinder;

import static org.junit.Assert.assertEquals;

import com.group80.uoftinder.entities.User;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for UpdateList class.
 */
public class UpdateListTest {
    // create currentUser
    User currentUser = new User();
    // create an instance of updateList
    UpdateList update = new UpdateList(currentUser);

    @Test
    /**
     * Test when the currentUser does not 'like' the displayedUser.
     */
    public void currUserNoLikeDisplayedUser() {
        // set the currentUser and displayedUser
        User displayedUser  = new User();
        // make the currentUser's viewedList and likedList
        List viewedList = new ArrayList<>();
        List likedList = new ArrayList<>();

        // add displayedUser to viewedList but not likedList
        UpdateList.addToList(displayedUser,false, viewedList, likedList);

        // expected results:  displayedUser in currentUser's visited list but not liked list.
        List expectedLikedList = new ArrayList<>();
        List expectedVisitedList = new ArrayList<>();
        expectedVisitedList.add(displayedUser);

        // assert statements
        assert expectedLikedList.equals(currentUser.getLiked());
        assert expectedVisitedList.equals(currentUser.getViewed());
    }

    @Test
    /**
     * Test when the currentUser does 'like' the displayedUser.
     */
    public void currUserLikeDisplayedUser() {
        // set the currentUser and displayedUser
        User displayedUser  = new User();
        // make the currentUser's viewedList and likedList
        List viewedList = new ArrayList<>();
        List likedList = new ArrayList<>();
        // add displayedUser to viewedList and likedList
        UpdateList.addToList(displayedUser,true, viewedList, likedList);

        // expected results:  displayedUser in currentUser's liked and visited list
        List expectedLikedList = new ArrayList<>();
        expectedLikedList.add(displayedUser);
        List expectedVisitedList = new ArrayList();
        expectedVisitedList.add(displayedUser);

        // assert statements
        assert expectedLikedList.equals(currentUser.getLiked());
        assert expectedVisitedList.equals(currentUser.getViewed());
    }


}
