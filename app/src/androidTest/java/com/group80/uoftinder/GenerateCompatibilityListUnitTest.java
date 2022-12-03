package com.group80.uoftinder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.feed.GenerateCompatibilityList;
import com.group80.uoftinder.firebase.realtime.UserRealtimeDbFacade;

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
     * Test that GenerateCompatibilityList.removeCurrentUser removes the current user
     * from the current user's compatibility list
     */
    @Test
    public void removeCurrentUserTest() {
        User curUser = new User("curUser");
        int curUserScore = 1909569;
        curUser.setScore(curUserScore);
        curUser.setUserType("Romantic");

        List<List<Integer>> userAnswers = new ArrayList<>();
        userAnswers.add(Collections.singletonList(1)); // single
        userAnswers.add(Arrays.asList(0, 1, 3)); // multi
        userAnswers.add(Collections.singletonList(2)); // single
        userAnswers.add(Arrays.asList(2, 3, 5)); // multi
        userAnswers.add(Collections.singletonList(0)); // single
        userAnswers.add(Collections.singletonList(1)); // single
        curUser.setAnswers(userAnswers);

        GenerateCompatibilityList genCompatibilityList = new GenerateCompatibilityList(curUser);

        User user2 = new User("user2");
        int user2Score = 2893321;
        user2.setScore(user2Score);
        user2.setUserType("Romantic");

        User user3 = new User("user3");
        int user3Score = 3891;
        user3.setScore(user3Score);
        user2.setUserType("Romantic");

        List<User> initCompList = new ArrayList<>(Arrays.asList(curUser, user2, user3));
        List<User> expectedCompList = new ArrayList<>(Arrays.asList(user2, user3));

        genCompatibilityList.setCompatibilityList(initCompList);

        genCompatibilityList.removeCurrentUser();
        List<User> actual = genCompatibilityList.getCompatibilityList();
        assertEquals(expectedCompList, actual);
    }

    /**
     * Test that GenerateCompatibilityList.removeVisitedUsers removes the visited users from
     * the current user's compatibility list
     */
    @Test
    public void removeVisitedUsersTest() {
        User curUser = new User("curUser");
        int curUserScore = 1909569;
        curUser.setScore(curUserScore);
        curUser.setUserType("Romantic");

        List<List<Integer>> userAnswers = new ArrayList<>();
        userAnswers.add(Collections.singletonList(1)); // single
        userAnswers.add(Arrays.asList(0, 1, 3)); // multi
        userAnswers.add(Collections.singletonList(2)); // single
        userAnswers.add(Arrays.asList(2, 3, 5)); // multi
        userAnswers.add(Collections.singletonList(0)); // single
        userAnswers.add(Collections.singletonList(1)); // single
        curUser.setAnswers(userAnswers);

        GenerateCompatibilityList genCompatibilityList = new GenerateCompatibilityList(curUser);

        User user2 = new User("user2");
        int user2Score = 2893321;
        user2.setScore(user2Score);
        user2.setUserType("Romantic");

        User user3 = new User("user3");
        int user3Score = 3891;
        user3.setScore(user3Score);
        user3.setUserType("Romantic");

        User user4 = new User("user4");
        int user4Score = 18643;
        user4.setScore(user4Score);
        user4.setUserType("Romantic");

        UserRealtimeDbFacade.uploadUser(user2);
        UserRealtimeDbFacade.uploadUser(user3);
        UserRealtimeDbFacade.uploadUser(user4);

        List<String> visitedList = new ArrayList<>(Arrays.asList("user4", "user3"));

        curUser.setViewed(visitedList);

        List<User> initCompList = new ArrayList<>(Arrays.asList(user2, user3, user4));
        List<User> expectedCompList = new ArrayList<>(Arrays.asList(user2));

        genCompatibilityList.setCompatibilityList(initCompList);

        genCompatibilityList.removeVisitedUsers();
        List<User> actual = genCompatibilityList.getCompatibilityList();
        assertEquals(expectedCompList, actual);
    }
}