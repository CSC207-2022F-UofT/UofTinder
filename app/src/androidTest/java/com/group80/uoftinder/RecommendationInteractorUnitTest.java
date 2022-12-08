package com.group80.uoftinder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.group80.uoftinder.entities_layer.User;
import com.group80.uoftinder.use_case_layer.feed.RecommendationInteractor;
import com.group80.uoftinder.use_case_layer.feed.UserScoreFacade;
import com.group80.uoftinder.use_case_layer.firebase.realtime.UserRealtimeDbFacade;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A RecommendationInteractorUnitTest class that tests the functionality of the
 * RecommendationInteractor class
 */
public class RecommendationInteractorUnitTest {

    /**
     * Test that RecommendationInteractor.orderCompatibilityList reorders the
     * compatibility list from most compatible user to least compatible user when there are
     * 3 total users
     */
    @Test
    public void orderCompatibilityListTest1() {
        List<List<Integer>> userAnswers = new ArrayList<>();
        userAnswers.add(Collections.singletonList(1)); // single
        userAnswers.add(Arrays.asList(0, 1, 3)); // multi
        userAnswers.add(Collections.singletonList(2)); // single
        userAnswers.add(Arrays.asList(2, 3, 5)); // multi
        userAnswers.add(Collections.singletonList(0)); // single
        userAnswers.add(Collections.singletonList(1)); // single

        User curUser = new User("Alice");
        curUser.setName("Alice");
        curUser.setAnswers(userAnswers);
        curUser.setUserType("Romantic");

        RecommendationInteractor genCompatibilityList = new RecommendationInteractor(curUser);
        genCompatibilityList.setUsf(new UserScoreFacade(curUser));

        User user2 = new User("Benjamin");
        int user2Score = 2893321; // User 2 similarity score = 5
        user2.setName("Benjamin");

        User user3 = new User("Clark");
        int user3Score = 3354610; // User 3 similarity score = 6
        user3.setName("Clark");

        user2.setScore(user2Score);
        user3.setScore(user3Score);

        List<User> initCompList = new ArrayList<>(Arrays.asList(user2, user3));
        List<User> expectedCompList = new ArrayList<>(Arrays.asList(user3, user2));

        genCompatibilityList.setCompatibilityList(initCompList);
        genCompatibilityList.orderCompatibilityList();

        List<User> actual = genCompatibilityList.getCompatibilityList();
        assertEquals(expectedCompList, actual);
    }

    /**
     * Test that RecommendationInteractor.orderCompatibilityList does nothing to the compatibility
     * list when there are no other users in the database (besides the current user)
     */
    @Test
    public void orderCompatibilityListTest2() {
        List<List<Integer>> userAnswers = new ArrayList<>();
        userAnswers.add(Collections.singletonList(1)); // single
        userAnswers.add(Arrays.asList(0, 1, 3)); // multi
        userAnswers.add(Collections.singletonList(2)); // single
        userAnswers.add(Arrays.asList(2, 3, 5)); // multi
        userAnswers.add(Collections.singletonList(0)); // single
        userAnswers.add(Collections.singletonList(1)); // single

        User curUser = new User("Alice");
        curUser.setName("Alice");
        curUser.setAnswers(userAnswers);
        curUser.setUserType("Romantic");

        RecommendationInteractor genCompatibilityList = new RecommendationInteractor(curUser);
        genCompatibilityList.setUsf(new UserScoreFacade(curUser));

        List<User> userList = new ArrayList<>();
        genCompatibilityList.setCompatibilityList(userList);
        genCompatibilityList.orderCompatibilityList();
        List<User> actual = genCompatibilityList.getCompatibilityList();
        List<User> expected = new ArrayList<>();
        assertEquals(actual, expected);
    }

    /**
     * Test that RecommendationInteractor.showMostCompUser returns the most compatible user when
     * there are 2 other users
     */
    @Test
    public void showMostCompUser2UsersTest() {
        List<List<Integer>> userAnswers = new ArrayList<>();
        userAnswers.add(Collections.singletonList(1)); // single
        userAnswers.add(Arrays.asList(0, 1, 3)); // multi
        userAnswers.add(Collections.singletonList(2)); // single
        userAnswers.add(Arrays.asList(2, 3, 5)); // multi
        userAnswers.add(Collections.singletonList(0)); // single
        userAnswers.add(Collections.singletonList(1)); // single

        User curUser = new User("Alice");
        curUser.setName("Alice");
        curUser.setAnswers(userAnswers);
        curUser.setUserType("Romantic");

        RecommendationInteractor genCompatibilityList = new RecommendationInteractor(curUser);
        User user2 = new User("Benjamin");
        user2.setName("Benjamin");
        User user3 = new User("Clark");
        user3.setName("Clark");
        List<User> compList = new ArrayList<>(Arrays.asList(user3, user2));
        genCompatibilityList.setCompatibilityList(compList);
        User expected = user3;
        User actual = genCompatibilityList.showMostCompUser();
        assertEquals(actual, expected);
    }

    /**
     * Test that RecommendationInteractor.showMostCompUser returns null when there are no other
     * compatible users
     */
    @Test
    public void showMostCompUserNoUsersTest() {
        List<List<Integer>> userAnswers = new ArrayList<>();
        userAnswers.add(Collections.singletonList(1)); // single
        userAnswers.add(Arrays.asList(0, 1, 3)); // multi
        userAnswers.add(Collections.singletonList(2)); // single
        userAnswers.add(Arrays.asList(2, 3, 5)); // multi
        userAnswers.add(Collections.singletonList(0)); // single
        userAnswers.add(Collections.singletonList(1)); // single

        User curUser = new User("Alice");
        curUser.setName("Alice");
        curUser.setAnswers(userAnswers);
        curUser.setUserType("Romantic");

        RecommendationInteractor genCompatibilityList = new RecommendationInteractor(curUser);

        List<User> compList = new ArrayList<>();
        genCompatibilityList.setCompatibilityList(compList);
        User actual = genCompatibilityList.showMostCompUser();
        assertNull(actual);
    }

    /**
     * Test that RecommendationInteractor.removeMostCompUser removes the most compatible user
     * in the compatibility list
     */
    @Test
    public void removeMostCompUserTest() {
        List<List<Integer>> userAnswers = new ArrayList<>();
        userAnswers.add(Collections.singletonList(1)); // single
        userAnswers.add(Arrays.asList(0, 1, 3)); // multi
        userAnswers.add(Collections.singletonList(2)); // single
        userAnswers.add(Arrays.asList(2, 3, 5)); // multi
        userAnswers.add(Collections.singletonList(0)); // single
        userAnswers.add(Collections.singletonList(1)); // single

        User curUser = new User("Alice");
        curUser.setName("Alice");
        curUser.setAnswers(userAnswers);
        curUser.setUserType("Romantic");

        RecommendationInteractor genCompatibilityList = new RecommendationInteractor(curUser);

        User user2 = new User("Benjamin");
        user2.setName("Benjamin");
        User user3 = new User("Clark");
        user3.setName("Clark");
        List<User> compList = new ArrayList<>(Arrays.asList(user2, user3));
        genCompatibilityList.setCompatibilityList(compList);
        genCompatibilityList.removeMostCompUser();
        List<User> expected = new ArrayList<>(Collections.singletonList(user3));
        List<User> actual = genCompatibilityList.getCompatibilityList();
        assertEquals(actual, expected);
    }

    /**
     * Test that RecommendationInteractor.removeCurrentUser removes the current user
     * from the current user's compatibility list
     */
    @Test
    public void removeCurrentUserTest() {
        User curUser = new User("Alice");
        curUser.setName("Alice");
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

        RecommendationInteractor genCompatibilityList = new RecommendationInteractor(curUser);

        User user2 = new User("Benjamin");
        user2.setName("Benjamin");
        int user2Score = 2893321;
        user2.setScore(user2Score);
        user2.setUserType("Romantic");

        User user3 = new User("Clark");
        user3.setName("Clark");
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
     * Test that RecommendationInteractor.removeVisitedUsers removes the visited users from
     * the current user's compatibility list
     */
    @Test
    public void removeVisitedUsersTest() {
        User curUser = new User("Alice");
        curUser.setName("Alice");
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

        RecommendationInteractor genCompatibilityList = new RecommendationInteractor(curUser);

        User user2 = new User("Benjamin");
        user2.setName("Benjamin");
        int user2Score = 2893321;
        user2.setScore(user2Score);
        user2.setUserType("Romantic");

        User user3 = new User("Clark");
        user3.setName("Clark");
        int user3Score = 3891;
        user3.setScore(user3Score);
        user3.setUserType("Romantic");

        User user4 = new User("Ester");
        user4.setName("Ester");
        int user4Score = 18643;
        user4.setScore(user4Score);
        user4.setUserType("Romantic");

        UserRealtimeDbFacade.uploadUser(user2);
        UserRealtimeDbFacade.uploadUser(user3);
        UserRealtimeDbFacade.uploadUser(user4);

        List<String> visitedList = new ArrayList<>(Arrays.asList("Ester", "Clark"));

        curUser.setViewed(visitedList);

        List<User> initCompList = new ArrayList<>(Arrays.asList(user2, user3, user4));
        List<User> expectedCompList = new ArrayList<>(Collections.singletonList(user2));

        genCompatibilityList.setCompatibilityList(initCompList);

        genCompatibilityList.removeVisitedUsers();
        List<User> actual = genCompatibilityList.getCompatibilityList();
        assertEquals(expectedCompList, actual);
    }
}
