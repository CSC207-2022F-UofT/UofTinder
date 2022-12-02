package com.group80.uoftinder;

import com.group80.uoftinder.create_account_use_case.CreateAccountInteractor;
import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.feed.UserScoreFacade;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Unit tests for the UserScoreFacade class
 */
public class UserScoreFacadeUnitTest {
    @Test
    public void generateCompatibilityScoreTest1() {
        List<List<Integer>> userAnswers = new ArrayList<>();
        userAnswers.add(Collections.singletonList(1)); // single
        userAnswers.add(Arrays.asList(0, 1, 3)); // multi
        userAnswers.add(Collections.singletonList(2)); // single
        userAnswers.add(Arrays.asList(2, 3, 5)); // multi
        userAnswers.add(Collections.singletonList(0)); // single
        userAnswers.add(Collections.singletonList(1)); // single

        User currUser = new User("currUser");
        currUser.setAnswers(userAnswers);
        currUser.setUserType("Romantic");

        UserScoreFacade usf = new UserScoreFacade(currUser);
        int userScore = usf.generateCompatibilityScore();
        // binary: 001-110100-10-00110100-00-01
        // decimal: 1909569
        assert userScore == 1909569;
    }

    @Test
    public void generateCompatibilityScoreTest2() {
        List<List<Integer>> userAnswers = new ArrayList<>();
        userAnswers.add(Collections.singletonList(2)); // single
        userAnswers.add(Arrays.asList(0, 1)); // multi
        userAnswers.add(Collections.singletonList(2)); // single
        userAnswers.add(Arrays.asList(1, 2)); // multi
        userAnswers.add(Collections.singletonList(2)); // single
        userAnswers.add(Collections.singletonList(1)); // single

        User user2 = new User("user2");
        user2.setAnswers(userAnswers);
        user2.setUserType("Romantic");

        UserScoreFacade usf = new UserScoreFacade(user2);
        int userScore = usf.generateCompatibilityScore();
        // binary: 010-110000-10-01100000-10-01
        // decimal: 2893321
        assert userScore == 2893321;
    }

    @Test
    public void compareTest() {
        List<List<Integer>> userAnswers = new ArrayList<>();
        userAnswers.add(Collections.singletonList(1)); // single
        userAnswers.add(Arrays.asList(0, 1, 3)); // multi
        userAnswers.add(Collections.singletonList(2)); // single
        userAnswers.add(Arrays.asList(2, 3, 5)); // multi
        userAnswers.add(Collections.singletonList(0)); // single
        userAnswers.add(Collections.singletonList(1)); // single

        User currUser = new User("currUser");
        currUser.setAnswers(userAnswers);
        currUser.setUserType("Romantic");

        UserScoreFacade usf = new UserScoreFacade(currUser);
        currUser.setScore(usf.generateCompatibilityScore());
        // binary: 001-110100-10-00110100-00-01
        // decimal: 1909569
        int userScore2 = 2893321;
        // binary: 010-110000-10-01100000-10-01
        // single-multi-single-multi-single-single

        int similarity = usf.compare(userScore2);
        assert similarity == 5; // 0 + 2 + 1 + 1 + 0 + 1
    }
}
