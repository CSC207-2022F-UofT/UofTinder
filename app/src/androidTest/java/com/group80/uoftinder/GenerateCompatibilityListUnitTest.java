package com.group80.uoftinder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.google.firebase.auth.FirebaseAuth;
import com.group80.uoftinder.create_account_use_case.CreateAccountInteractor;
import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.feed.GenerateCompatibilityList;
import com.group80.uoftinder.feed.RecommendationPresenter;
import com.group80.uoftinder.feed.RecommendationView;
import com.group80.uoftinder.feed.UserScoreFacade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    public void orderCompatibilityListTest1() {
        User curUser = new User("curUser");
        int curUserScore = 11682;
        curUser.setScore(curUserScore);
//        genCompatibilityList.setCurUser(curUser);

        CreateAccountInteractor.setAnswerSchema(new int[] {3, 4, 4, 5, 4});
        CreateAccountInteractor.setIsMultiSelect(new boolean[] {false, true, true, false, false});

        List<List<Integer>> userAnswers = new ArrayList<>();
        userAnswers.add(Collections.singletonList(1));
        userAnswers.add(Arrays.asList(1, 2));
        userAnswers.add(Arrays.asList(0, 1, 3));
        userAnswers.add(Collections.singletonList(0));
        userAnswers.add(Collections.singletonList(2));
        curUser.setAnswers(userAnswers);

        GenerateCompatibilityList genCompatibilityList = new GenerateCompatibilityList(curUser);
        User user2 = new User("user2");
        User user3 = new User("user3");

        int user2Score = 18642;
        int user3Score = 3891;

        user2.setScore(user2Score);
        user3.setScore(user3Score);

        List<User> initCompList = new ArrayList<>(Arrays.asList(user2, user3));
        List<User> expectedCompList = new ArrayList<>(Arrays.asList(user3, user2));


        genCompatibilityList.setUsf(new UserScoreFacade(curUser));

        // user 3 compScore = 4
        // user 2 compScore = 3
        genCompatibilityList.setCompatibilityList(initCompList);
        genCompatibilityList.orderCompatibilityList();
        List<User> actual = genCompatibilityList.getCompatibilityList();
        assertEquals(expectedCompList, actual);
    }

    /**
     * Test that GenerateCompatibilityList.orderCompatibilityList does nothing to the compatibility
     * list when there are no other users in the database (besides the current user)
     */
    @Test
    public void orderCompatibilityListTest2() {
        User curUser = new User("curUser");
        GenerateCompatibilityList genCompatibilityList = new GenerateCompatibilityList(curUser);
        List<User> userList = new ArrayList<>();
        genCompatibilityList.setCompatibilityList(userList);
        genCompatibilityList.orderCompatibilityList();
        List<User> actual = genCompatibilityList.getCompatibilityList();
        List<User> expected = new ArrayList<>();
        assertEquals(actual, expected);
    }

    /**
     * Test that GenerateCompatibilityList.showMostCompUser returns the most compatible user when
     * there are 2 other users
     */
    @Test
    public void showMostCompUser2UsersTest() {
        User curUser = new User("curUser");
        GenerateCompatibilityList genCompatibilityList = new GenerateCompatibilityList(curUser);
        User user2 = new User("user2");
        User user3 = new User("user3");
        List<User> compList = new ArrayList<>(Arrays.asList(user3, user2));
        genCompatibilityList.setCompatibilityList(compList);
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
        User curUser = new User("curUser");
        GenerateCompatibilityList genCompatibilityList = new GenerateCompatibilityList(curUser);
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
        User curUser = new User("curUser");
        GenerateCompatibilityList genCompatibilityList = new GenerateCompatibilityList(curUser);
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
