package com.group80.uoftinder;

import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.feed.RecommendationInteractor;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Unit tests for the RecommendationInteractor class.
 */
public class RecommendationInteractorUnitTest {

    @Test
    public void filterFeedTestFilters() {
        List<User> compatibilityList = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        user1.setAge(19);
        user2.setAge(13);
        user3.setAge(83);

        List<Set<Integer>> userAnswers1 = new ArrayList<>();
        userAnswers1.add(new HashSet<>(Collections.singletonList(4))); // single
        userAnswers1.add(new HashSet<>(Collections.singletonList(1))); // single
        userAnswers1.add(new HashSet<>(Arrays.asList(0, 1))); // multi

        List<Set<Integer>> userAnswers2 = new ArrayList<>();
        userAnswers2.add(new HashSet<>(Collections.singletonList(2))); // single
        userAnswers2.add(new HashSet<>(Arrays.asList(0, 1))); // multi
        userAnswers2.add(new HashSet<>(Arrays.asList(0, 1))); // multi

        List<Set<Integer>> userAnswers3 = new ArrayList<>();
        userAnswers3.add(new HashSet<>(Collections.singletonList(0))); // single
        userAnswers3.add(new HashSet<>(Collections.singletonList(1))); // single
        userAnswers3.add(new HashSet<>(Collections.singletonList(1))); // single

        user1.setAnswers(userAnswers1);
        user2.setAnswers(userAnswers2);
        user3.setAnswers(userAnswers3);

        compatibilityList.add(user1);
        compatibilityList.add(user2);
        compatibilityList.add(user3);

        RecommendationInteractor recommendationInteractor = new RecommendationInteractor();
        recommendationInteractor.setCompatibilityList(compatibilityList);

        List<User> copyCompatibilityList = new ArrayList<>();
        copyCompatibilityList.addAll(compatibilityList);

        List<Set<Integer>> filters = new ArrayList<>();
        filters.add(new HashSet<>(Arrays.asList(0, 1, 2, 3)));
        filters.add(new HashSet<>(Arrays.asList(0, 1)));
        filters.add(new HashSet<>(Collections.singletonList(0)));

        int minAge = 13;
        int maxAge = 100;
        recommendationInteractor.filterCompatibilityList(filters, minAge, maxAge);

        List<User> filteredCompatibilityList = recommendationInteractor.getFilteredCompatibilityList();
        assert filteredCompatibilityList.size() == 1 &&
                filteredCompatibilityList.contains(user2);
    }

    @Test
    public void filterFeedTestAge() {
        List<User> compatibilityList = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();
        User user5 = new User();
        user1.setAge(19);
        user2.setAge(13);
        user3.setAge(83);
        user4.setAge(30);
        user5.setAge(25);

        List<Set<Integer>> userAnswers = new ArrayList<>();
        userAnswers.add(new HashSet<>(Collections.singletonList(2))); // single
        userAnswers.add(new HashSet<>(Collections.singletonList(1))); // multi
        userAnswers.add(new HashSet<>(Arrays.asList(1, 2))); // multi

        user1.setAnswers(userAnswers);
        user2.setAnswers(userAnswers);
        user3.setAnswers(userAnswers);
        user4.setAnswers(userAnswers);
        user5.setAnswers(userAnswers);

        compatibilityList.add(user1);
        compatibilityList.add(user2);
        compatibilityList.add(user3);
        compatibilityList.add(user4);
        compatibilityList.add(user5);

        RecommendationInteractor recommendationInteractor = new RecommendationInteractor();
        recommendationInteractor.setCompatibilityList(compatibilityList);

        List<User> copyCompatibilityList = new ArrayList<>();
        copyCompatibilityList.addAll(compatibilityList);

        List<Set<Integer>> filters = new ArrayList<>();
        filters.add(new HashSet<>());
        filters.add(new HashSet<>());
        filters.add(new HashSet<>());

        int minAge = 18;
        int maxAge = 30;
        recommendationInteractor.filterCompatibilityList(filters, minAge, maxAge);

        List<User> filteredCompatibilityList = recommendationInteractor.getFilteredCompatibilityList();
        assert filteredCompatibilityList.size() == 3 &&
                filteredCompatibilityList.contains(user1) &&
                filteredCompatibilityList.contains(user4) &&
                filteredCompatibilityList.contains(user5);
    }

    @Test
    public void filterFeedTestNoFilters() {
        List<User> compatibilityList = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();
        User user5 = new User();
        user1.setAge(19);
        user2.setAge(20);
        user3.setAge(83);
        user4.setAge(46);
        user5.setAge(50);

        List<Set<Integer>> userAnswers = new ArrayList<>();
        userAnswers.add(new HashSet<>(Collections.singletonList(2))); // single
        userAnswers.add(new HashSet<>(Collections.singletonList(1))); // multi
        userAnswers.add(new HashSet<>(Arrays.asList(1, 2))); // multi

        user1.setAnswers(userAnswers);
        user2.setAnswers(userAnswers);
        user3.setAnswers(userAnswers);
        user4.setAnswers(userAnswers);
        user5.setAnswers(userAnswers);

        compatibilityList.add(user1);
        compatibilityList.add(user2);
        compatibilityList.add(user3);
        compatibilityList.add(user4);
        compatibilityList.add(user5);

        RecommendationInteractor recommendationInteractor = new RecommendationInteractor();
        recommendationInteractor.setCompatibilityList(compatibilityList);

        List<User> copyCompatibilityList = new ArrayList<>();
        copyCompatibilityList.addAll(compatibilityList);

        List<Set<Integer>> filters = new ArrayList<>();
        filters.add(new HashSet<>());
        filters.add(new HashSet<>());
        filters.add(new HashSet<>());

        int minAge = 13;
        int maxAge = 100;
        recommendationInteractor.filterCompatibilityList(filters, minAge, maxAge);

        List<User> filteredCompatibilityList = recommendationInteractor.getFilteredCompatibilityList();
        assert filteredCompatibilityList.equals(copyCompatibilityList);
    }

    @Test
    public void filterFeedTestFiltersAndAge() {
        List<User> compatibilityList = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        user1.setAge(19);
        user2.setAge(13);
        user3.setAge(25);

        List<Set<Integer>> userAnswers1 = new ArrayList<>();
        userAnswers1.add(new HashSet<>(Collections.singletonList(3))); // single
        userAnswers1.add(new HashSet<>(Arrays.asList(0, 1))); // multi
        userAnswers1.add(new HashSet<>(Collections.singletonList(0))); // single

        List<Set<Integer>> userAnswers2 = new ArrayList<>();
        userAnswers2.add(new HashSet<>(Collections.singletonList(2))); // single
        userAnswers2.add(new HashSet<>(Arrays.asList(0, 1))); // multi
        userAnswers2.add(new HashSet<>(Arrays.asList(0, 1))); // multi

        List<Set<Integer>> userAnswers3 = new ArrayList<>();
        userAnswers3.add(new HashSet<>(Collections.singletonList(0))); // single
        userAnswers3.add(new HashSet<>(Collections.singletonList(1))); // single
        userAnswers3.add(new HashSet<>(Collections.singletonList(1))); // single

        user1.setAnswers(userAnswers1);
        user2.setAnswers(userAnswers2);
        user3.setAnswers(userAnswers3);

        compatibilityList.add(user1);
        compatibilityList.add(user2);
        compatibilityList.add(user3);

        RecommendationInteractor recommendationInteractor = new RecommendationInteractor();
        recommendationInteractor.setCompatibilityList(compatibilityList);

        List<User> copyCompatibilityList = new ArrayList<>();
        copyCompatibilityList.addAll(compatibilityList);

        List<Set<Integer>> filters = new ArrayList<>();
        filters.add(new HashSet<>(Arrays.asList(0, 1, 2, 3)));
        filters.add(new HashSet<>(Arrays.asList(0, 1)));
        filters.add(new HashSet<>(Collections.singletonList(0)));

        int minAge = 18;
        int maxAge = 30;
        recommendationInteractor.filterCompatibilityList(filters, minAge, maxAge);

        List<User> filteredCompatibilityList = recommendationInteractor.getFilteredCompatibilityList();
        assert filteredCompatibilityList.size() == 1 &&
                filteredCompatibilityList.contains(user1);
    }
}
