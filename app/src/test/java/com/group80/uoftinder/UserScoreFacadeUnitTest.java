package com.group80.uoftinder;

import com.group80.uoftinder.entities_layer.User;
import com.group80.uoftinder.use_case_layer.UserScoreFacade;
import com.group80.uoftinder.use_case_layer.UserScoreInterface;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Unit tests for the UserScoreFacade class
 */
public class UserScoreFacadeUnitTest {
    /**
     * Testing to see whether the correct compatibility gets generated for current user
     * Creates first sample user - we repeat this thrice to have three unique users with
     * different userAnswers
     */
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

        UserScoreInterface usf = new UserScoreFacade(currUser);
        int userScore = currUser.getScore();
        // binary: 001-110100-10-00110100-00-01
        // decimal: 1909569
        assert userScore == 1909569;
    }

    /**
     * Testing to see whether the correct compatibility gets generated for current user
     * Creates second sample user
     */
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

        UserScoreInterface usf = new UserScoreFacade(user2);
        int userScore = user2.getScore();
        // binary: 010-110000-10-01100000-10-01
        // decimal: 2893321
        assert userScore == 2893321;
    }

    /**
     * Testing to see whether the correct compatibility gets generated for current user
     * Creates third sample user
     */
    @Test
    public void generateCompatibilityScoreTest3() {
        List<List<Integer>> userAnswers = new ArrayList<>();
        userAnswers.add(Collections.singletonList(3)); // single
        userAnswers.add(Arrays.asList(2, 3)); // multi
        userAnswers.add(Collections.singletonList(2)); // single
        userAnswers.add(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7)); // multi
        userAnswers.add(Collections.singletonList(0)); // single
        userAnswers.add(Collections.singletonList(2)); // single

        User user3 = new User("user3");
        user3.setAnswers(userAnswers);
        user3.setUserType("Romantic");

        UserScoreInterface usf = new UserScoreFacade(user3);
        int userScore = user3.getScore();
        // binary: 011-001100-10-11111111-00-10
        // decimal: 3354610
        assert userScore == 3354610;
    }

    /**
     * Testing to see if the compare method returns the correct similarity score for
     * user1 and user2, whose compatibility scores are tested above
     */
    @Test
    public void compareTest1() {
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

        UserScoreInterface usf = new UserScoreFacade(currUser);
        // binary: 001-110100-10-00110100-00-01
        // decimal: 1909569
        int userScore2 = 2893321;
        // binary: 010-110000-10-01100000-10-01

        int similarity = usf.compare(userScore2);
        assert similarity == 5; // 0 + 2 + 1 + 1 + 0 + 1
    }

    /**
     * Testing to see if the compare method returns the correct similarity score for
     * user1 and user3, whose compatibility scores are tested above
     */
    @Test
    public void compareTest2() {
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

        UserScoreInterface usf = new UserScoreFacade(currUser);
        // binary: 001-110100-10-00110100-00-01
        // decimal: 1909569
        int userScore3 = 3354610;
        // binary: 011-001100-10-11111111-00-10

        int similarity = usf.compare(userScore3);
        assert similarity == 6;
    }
}
