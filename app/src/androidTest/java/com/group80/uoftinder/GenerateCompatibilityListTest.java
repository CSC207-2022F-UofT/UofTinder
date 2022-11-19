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

@RunWith(AndroidJUnit4.class)
public class GenerateCompatibilityListTest {

    @Test
    public void generateCompatibilityList() {
        User curUser = User();
        RecommendationView recommendationView = new RecommendationView(curUser);
        RecommendationPresenter recPresenter = new RecommendationPresenter(recommendationView);
        GenerateCompatibilityList generateCompatibilityList = new GenerateCompatibilityList(recPresenter);
        User user2 = User();
        User user3 = User();
        int curUserScore = 11682;
        int user2Score = 18642;
        int user3Score = 3891;
        curUser.setScore(curUserScore);
        user2.setScore(user2Score);
        user3.setScore(user3Score);
        curUser.setUid("curUser");
        user2.setUid("user2");
        user3.setUid("user3");
        List<User> allUsers = new ArrayList<User>(Arrays.asList(user2, user3));
        List<String> expectedCompList = new ArrayList<String>(Arrays.asList("user3", "user2"));
        // user 3 compScore = 4
        // user 2 compScore = 3
        generateCompatibilityList.setAllUsers(allUsers);
        generateCompatibilityList.setCurUser(curUser);
        generateCompatibilityList.setCurUserScore(curUserScore);
        generateCompatibilityList.generateCompatibilityList();
        List<String> actual = generateCompatibilityList.getCompatibilityList();
        assertEquals(actual, expectedCompList);
    }
}
