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
     *
     */
    @Test
    public void removeCurrentUserTest() {
        User curUser = new User("curUser");
        int curUserScore = 11682;
        curUser.setScore(curUserScore);
        curUser.setUserType("Academic");

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
        int user2Score = 18642;
        user2.setScore(user2Score);
        user2.setUserType("Academic");

        User user3 = new User("user3");
        int user3Score = 3891;
        user3.setScore(user3Score);
        user2.setUserType("Academic");

        List<User> initCompList = new ArrayList<>(Arrays.asList(curUser, user2, user3));
        List<User> expectedCompList = new ArrayList<>(Arrays.asList(user2, user3));

        genCompatibilityList.setCompatibilityList(initCompList);

        genCompatibilityList.removeCurrentUser();
        List<User> actual = genCompatibilityList.getCompatibilityList();
        assertEquals(expectedCompList, actual);
    }

    /**
     *
     */
    @Test
    public void removeVisitedUsersTest() {
        User curUser = new User("curUser");
        int curUserScore = 11682;
        curUser.setScore(curUserScore);
        curUser.setUserType("Academic");

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
        int user2Score = 18642;
        user2.setScore(user2Score);
        user2.setUserType("Academic");

        User user3 = new User("user3");
        int user3Score = 3891;
        user3.setScore(user3Score);
        user3.setUserType("Academic");

        User user4 = new User("user4");
        int user4Score = 18643;
        user4.setScore(user4Score);
        user4.setUserType("Academic");

        UserRealtimeDbFacade.uploadUser(user2);
        UserRealtimeDbFacade.uploadUser(user3);
        UserRealtimeDbFacade.uploadUser(user4);

        List<String> visitedList = new ArrayList<>(Collections.singletonList("user3"));

        curUser.setViewed(visitedList);

        List<User> initCompList = new ArrayList<>(Arrays.asList(user2, user3, user4));
        List<User> expectedCompList = new ArrayList<>(Arrays.asList(user2, user4));

        genCompatibilityList.setCompatibilityList(initCompList);

        genCompatibilityList.removeVisitedUsers();
        List<User> actual = genCompatibilityList.getCompatibilityList();
        assertEquals(expectedCompList, actual);
    }
}