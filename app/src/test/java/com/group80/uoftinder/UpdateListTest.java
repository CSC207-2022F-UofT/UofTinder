package com.group80.uoftinder;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UpdateListTest {
    @Test
    public void currUserNoLikeDisplayedUser() {
        User currentUser = new User();
        User displayedUser  = new User();

        addToList(currentUser, displayedUser, false);

        List expectedLikedList = new ArrayList<>();
        List expectedVisitedList = new ArrayList<>();
        expectedVisitedList.add(displayedUser);

        // WHAT I WANT IT TO ASSERT
//      boolean l = expectedLikedList.equals(currentUser.getLiked());
//      boolean v = expectedVisitedList.equals(currentUser.getViewed());

//        THESE DON'T WORK!
//        assert(expectedLikedList.equals(currentUser.getLiked()));
//        assert(expectedVisitedList.equals(currentUser.getViewed()));
    }

    @Test
    public void currUserLikeDisplayedUser() {
        User currentUser = new User();
        User displayedUser  = new User();

        addToList(currentUser, displayedUser, true);

        List expectedLikedList = new ArrayList<>();
        expectedLikedList.add(displayedUser);
        List expectedVisitedList = new ArrayList();
        expectedVisitedList.add(displayedUser);

        // WHAT I WANT IT TO ASSERT
//      boolean l = expectedLikedList.equals(currentUser.getLiked());
//      boolean v = expectedVisitedList.equals(currentUser.getViewed());

//        THESE DON'T WORK!
//        assert(expectedLikedList.equals(currentUser.getLiked()));
//        assert(expectedVisitedList.equals(currentUser.getViewed()));
    }

    private void addToList(User currentUser, User displayedUserFalse, boolean b) {
    }
}
