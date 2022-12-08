package com.group80.uoftinder;

import com.group80.uoftinder.entities_layer.User;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unit tests for the methods in the User class beyond the default getters and setters.
 */
public class UserUnitTest {

    /**
     * Unit test for getting the correct display information string for an academic user.
     */
    @Test
    public void infoStringTestAcademicUser() {
        User currentUser = new User();
        currentUser.setUserType("Academic");
        List<List<Integer>> answers = new ArrayList<>();
        answers.add(Arrays.asList(1));
        answers.add(Arrays.asList(1, 2));
        answers.add(Arrays.asList(0));
        currentUser.setAnswers(answers);

        String infoString = currentUser.getUserInfoString();
        assert infoString.equals("Second Year / Mathematical & Physical Sciences Life Sciences / St. George / ");
    }

    /**
     * Unit test for getting the correct display information string for a romantic user.
     * Also tests the case of not providing answers for several questions.
     */
    @Test
    public void infoStringTestRomanticUser() {
        User currentUser = new User();
        currentUser.setUserType("Romantic");
        List<List<Integer>> answers = new ArrayList<>();
        answers.add(Arrays.asList(0));
        answers.add(Arrays.asList(3, 4, 5));
        answers.add(Arrays.asList(0));
        answers.add(Arrays.asList(6, 7));
        currentUser.setAnswers(answers);

        String infoString = currentUser.getUserInfoString();
        assert infoString.equals("Heterosexual / Social Sciences & Humanities Rotman Other / St. George / Technology Sports & Athletics / ");
    }

    /**
     * Unit test for getting the correct display information string for a friendship user.
     * Also tests the case of not providing an answer for a question
     * between two questions with answers provided.
     */
    @Test
    public void infoStringTestFriendshipUser() {
        User currentUser = new User();
        currentUser.setUserType("Friendship");
        List<List<Integer>> answers = new ArrayList<>();
        answers.add(Arrays.asList(3));
        answers.add(Arrays.asList(4, 0));
        answers.add(Arrays.asList(1));
        answers.add(Arrays.asList());
        answers.add(Arrays.asList(2));
        currentUser.setAnswers(answers);

        String infoString = currentUser.getUserInfoString();
        assert infoString.equals("Fourth Year / Rotman Computer Science / Mississauga / / Yellow / ");
    }
}
