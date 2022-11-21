package com.group80.uoftinder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.feed.GenerateCompatibilityList;
import com.group80.uoftinder.feed.RecommendationPresenter;
import com.group80.uoftinder.feed.RecommendationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A GenerateCompatibilityListUnitTest class that tests the functionality of the
 * GenerateCompatibilityList class
 */
public class GenerateCompatibilityListUnitTest {

    /**
     * Test that GenerateCompatibilityList.orderCompatibilityList reorders the
     * compatibility list from most compatible user to least compatible user when there are
     * 3 total users
     */
    @Test
    public void orderCompatibilityListTest() {
        User curUser = new User("curUser");
        GenerateCompatibilityList genCompatibilityList = new GenerateCompatibilityList();
        User user2 = new User("user2");
        User user3 = new User("user3");
        int curUserScore = 11682;
        int user2Score = 18642;
        int user3Score = 3891;
        curUser.setScore(curUserScore);
        user2.setScore(user2Score);
        user3.setScore(user3Score);
        List<User> initCompList = new ArrayList<>(Arrays.asList(user2, user3));
        List<User> expectedCompList = new ArrayList<>(Arrays.asList(user3, user2));
        // user 3 compScore = 4
        // user 2 compScore = 3
        genCompatibilityList.setCompatibilityList(initCompList);
        genCompatibilityList.setCurUser(curUser);
        genCompatibilityList.setCurUserScore(curUserScore);
        genCompatibilityList.orderCompatibilityList();
        List<User> actual = genCompatibilityList.getCompatibilityList();
        assertEquals(actual, expectedCompList);
    }

    /**
     * Test that GenerateCompatibilityList.showMostCompUser returns the most compatible user when
     * there are 2 other users
     */
    @Test
    public void showMostCompUser2UsersTest() {
        User curUser = new User("curUser");
        GenerateCompatibilityList genCompatibilityList = new GenerateCompatibilityList();
        User user2 = new User("user2");
        User user3 = new User("user3");
        List<User> compList = new ArrayList<>(Arrays.asList(user3, user2));
        User expected = user3;
        User actual = genCompatibilityList.showMostCompUser();
        assertEquals(actual, expected);
    }

    /**
     * Test that GenerateCompatibilityList.showMostCompUser returns null when there are no other
     * compatible users
     */
    @Test
    public void showMostCompUserNoUsersTest() {
        GenerateCompatibilityList genCompatibilityList = new GenerateCompatibilityList();
        List<User> compList = new ArrayList<>();
        genCompatibilityList.setCompatibilityList(compList);
        User actual = genCompatibilityList.showMostCompUser();
        assertNull(actual);
    }

    /**
     * Test that GenerateCompatibilityList.removeMostCompUser removes the most compatible user
     * in the compatibility list
     */
    @Test
    public void removeMostCompUserTest() {
        GenerateCompatibilityList genCompatibilityList = new GenerateCompatibilityList();
        User user2 = new User("user2");
        User user3 = new User("user3");
        List<User> compList = new ArrayList<>(Arrays.asList(user2, user3));
        genCompatibilityList.setCompatibilityList(compList);
        genCompatibilityList.removeMostCompUser();
        List<User> expected = new ArrayList<>(Arrays.asList(user3));
        List<User> actual = genCompatibilityList.getCompatibilityList();
        assertEquals(actual, expected);
    }
}
