package com.group80.uoftinder;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A GenerateCompatibilityListTest class that tests the functionality of the
 * GenerateCompatibilityList class
 */
@RunWith(AndroidJUnit4.class)
public class GenerateCompatibilityListTest {

    /**
     * A class that tests that GenerateCompatibilityList.orderCompatibilityList reorders the
     * compatibility list correctly when there are 3 total users
     */
    @Test
    public void orderCompatibilityListTest() {
        User curUser = User("curUser");
        RecommendationView recommendationView = new RecommendationView(curUser);
        RecommendationPresenter recPresenter = new RecommendationPresenter(recommendationView);
        GenerateCompatibilityList generateCompatibilityList = new GenerateCompatibilityList(recPresenter);
        User user2 = User("user2");
        User user3 = User("user3");
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
        generateCompatibilityList.setCompatibilityList(initCompList);
        generateCompatibilityList.setCurUser(curUser);
        generateCompatibilityList.setCurUserScore(curUserScore);
        generateCompatibilityList.orderCompatibilityList();
        List<User> actual = generateCompatibilityList.getCompatibilityList();
        assertEquals(actual, expectedCompList);
    }
}
