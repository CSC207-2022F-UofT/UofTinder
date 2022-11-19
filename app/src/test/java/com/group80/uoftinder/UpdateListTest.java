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
        UpdateList update = new UpdateList(currentUser, displayedUser, false);
        update.addToList(displayedUser,false);

        List expectedLikedList = new ArrayList<>();
        List expectedVisitedList = new ArrayList<>();
        expectedVisitedList.add(displayedUser);

        assert expectedLikedList.equals(currentUser.getLiked());
        assert expectedVisitedList.equals(currentUser.getViewed());
    }

    @Test
    public void currUserLikeDisplayedUser() {
        User currentUser = new User();
        User displayedUser  = new User();
        UpdateList update = new UpdateList(currentUser, displayedUser, true);
        update.addToList(displayedUser, true);

        List expectedLikedList = new ArrayList<>();
        expectedLikedList.add(displayedUser);
        List expectedVisitedList = new ArrayList();
        expectedVisitedList.add(displayedUser);

        assert expectedLikedList.equals(currentUser.getLiked());
        assert expectedVisitedList.equals(currentUser.getViewed());
    }


}
