package com.group80.uoftinder;

import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.feed.RecommendationFilterInputData;
import com.group80.uoftinder.feed.GenerateCompatibilityList;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Unit tests for the filtering functionality in GenerateCompatibilityList class.
 *
 * Need to comment out getAllUsers() and removeCurrentUser() in
 * GenerateCompatibilityList constructor since this is a unit test for
 * filtering and NOT for Firebase database retrievals.
 */
public class FilterUseCaseUnitTest {

    @Test
    public void filterFeedTestFilters() {
        List<User> compatibilityList = new ArrayList<>();
        User user1 = new User("uid1");
        User user2 = new User("uid2");
        User user3 = new User("uid3");
        user1.setAge(19);
        user2.setAge(13);
        user3.setAge(83);

        List<List<Integer>> userAnswers1 = new ArrayList<>();
        userAnswers1.add(Collections.singletonList(4)); // single
        userAnswers1.add(Collections.singletonList(1)); // single
        userAnswers1.add(Arrays.asList(0, 1)); // multi

        List<List<Integer>> userAnswers2 = new ArrayList<>();
        userAnswers2.add(Collections.singletonList(2)); // single
        userAnswers2.add(Arrays.asList(0, 1)); // multi
        userAnswers2.add(Arrays.asList(0, 1)); // multi

        List<List<Integer>> userAnswers3 = new ArrayList<>();
        userAnswers3.add(Collections.singletonList(0)); // single
        userAnswers3.add(Collections.singletonList(1)); // single
        userAnswers3.add(Collections.singletonList(1)); // single

        user1.setAnswers(userAnswers1);
        user2.setAnswers(userAnswers2);
        user3.setAnswers(userAnswers3);

        compatibilityList.add(user1);
        compatibilityList.add(user2);
        compatibilityList.add(user3);

        User currUser = new User("currTest");
        GenerateCompatibilityList generateCompatibilityList = new GenerateCompatibilityList(currUser);
        generateCompatibilityList.setCompatibilityList(compatibilityList);

        List<User> copyCompatibilityList = new ArrayList<>();
        copyCompatibilityList.addAll(compatibilityList);

        List<Set<Integer>> filters = new ArrayList<>();
        filters.add(new HashSet<>(Arrays.asList(0, 1, 2, 3)));
        filters.add(new HashSet<>(Arrays.asList(0, 1)));
        filters.add(new HashSet<>(Collections.singletonList(0)));

        int minAge = 13;
        int maxAge = 100;
        RecommendationFilterInputData filterInputData = new RecommendationFilterInputData(filters, minAge, maxAge);
        generateCompatibilityList.filterCompatibilityList(filterInputData);

        List<User> filteredCompatibilityList = generateCompatibilityList.getFilteredCompatibilityList();
        assert filteredCompatibilityList.size() == 1 &&
                filteredCompatibilityList.contains(user2);
    }

    @Test
    public void filterFeedTestAge() {
        List<User> compatibilityList = new ArrayList<>();
        User user1 = new User("uid1");
        User user2 = new User("uid2");
        User user3 = new User("uid3");
        User user4 = new User("uid4");
        User user5 = new User("uid5");
        user1.setAge(19);
        user2.setAge(13);
        user3.setAge(83);
        user4.setAge(30);
        user5.setAge(25);

        List<List<Integer>> userAnswers = new ArrayList<>();

        userAnswers.add(Collections.singletonList(2)); // single
        userAnswers.add(Collections.singletonList(1)); // multi
        userAnswers.add(Arrays.asList(1, 2)); // multi

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

        User currUser = new User("currTest");
        GenerateCompatibilityList generateCompatibilityList = new GenerateCompatibilityList(currUser);
        generateCompatibilityList.setCompatibilityList(compatibilityList);

        List<User> copyCompatibilityList = new ArrayList<>();
        copyCompatibilityList.addAll(compatibilityList);

        List<Set<Integer>> filters = new ArrayList<>();
        filters.add(new HashSet<>());
        filters.add(new HashSet<>());
        filters.add(new HashSet<>());

        int minAge = 18;
        int maxAge = 30;
        RecommendationFilterInputData filterInputData = new RecommendationFilterInputData(filters, minAge, maxAge);
        generateCompatibilityList.filterCompatibilityList(filterInputData);

        List<User> filteredCompatibilityList = generateCompatibilityList.getFilteredCompatibilityList();
        assert filteredCompatibilityList.size() == 3 &&
                filteredCompatibilityList.contains(user1) &&
                filteredCompatibilityList.contains(user4) &&
                filteredCompatibilityList.contains(user5);
    }

    @Test
    public void filterFeedTestNoFilters() {
        List<User> compatibilityList = new ArrayList<>();
        User user1 = new User("uid1");
        User user2 = new User("uid2");
        User user3 = new User("uid3");
        User user4 = new User("uid4");
        User user5 = new User("uid5");
        user1.setAge(19);
        user2.setAge(20);
        user3.setAge(83);
        user4.setAge(46);
        user5.setAge(50);

        List<List<Integer>> userAnswers = new ArrayList<>();
        userAnswers.add(Collections.singletonList(2)); // single
        userAnswers.add(Collections.singletonList(1)); // multi
        userAnswers.add(Arrays.asList(1, 2)); // multi

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

        User currUser = new User("currTest");
        GenerateCompatibilityList generateCompatibilityList = new GenerateCompatibilityList(currUser);
        generateCompatibilityList.setCompatibilityList(compatibilityList);

        List<User> copyCompatibilityList = new ArrayList<>();
        copyCompatibilityList.addAll(compatibilityList);

        List<Set<Integer>> filters = new ArrayList<>();
        filters.add(new HashSet<>());
        filters.add(new HashSet<>());
        filters.add(new HashSet<>());

        int minAge = 13;
        int maxAge = 100;
        RecommendationFilterInputData filterInputData = new RecommendationFilterInputData(filters, minAge, maxAge);
        generateCompatibilityList.filterCompatibilityList(filterInputData);

        List<User> filteredCompatibilityList = generateCompatibilityList.getFilteredCompatibilityList();
        assert filteredCompatibilityList.equals(copyCompatibilityList);
    }

    @Test
    public void filterFeedTestFiltersAndAge() {
        List<User> compatibilityList = new ArrayList<>();
        User user1 = new User("uid1");
        User user2 = new User("uid2");
        User user3 = new User("uid3");
        user1.setAge(19);
        user2.setAge(13);
        user3.setAge(25);

        List<List<Integer>> userAnswers1 = new ArrayList<>();
        userAnswers1.add(Collections.singletonList(3)); // single
        userAnswers1.add(Arrays.asList(0, 1)); // multi
        userAnswers1.add(Collections.singletonList(0)); // single

        List<List<Integer>> userAnswers2 = new ArrayList<>();
        userAnswers2.add(Collections.singletonList(2)); // single
        userAnswers2.add(Arrays.asList(0, 1)); // multi
        userAnswers2.add(Arrays.asList(0, 1)); // multi

        List<List<Integer>> userAnswers3 = new ArrayList<>();
        userAnswers3.add(Collections.singletonList(0)); // single
        userAnswers3.add(Collections.singletonList(1)); // single
        userAnswers3.add(Collections.singletonList(1)); // single

        user1.setAnswers(userAnswers1);
        user2.setAnswers(userAnswers2);
        user3.setAnswers(userAnswers3);

        compatibilityList.add(user1);
        compatibilityList.add(user2);
        compatibilityList.add(user3);

        User currUser = new User("currTest");
        GenerateCompatibilityList generateCompatibilityList = new GenerateCompatibilityList(currUser);
        generateCompatibilityList.setCompatibilityList(compatibilityList);

        List<User> copyCompatibilityList = new ArrayList<>();
        copyCompatibilityList.addAll(compatibilityList);

        List<Set<Integer>> filters = new ArrayList<>();
        filters.add(new HashSet<>(Arrays.asList(0, 1, 2, 3)));
        filters.add(new HashSet<>(Arrays.asList(0, 1)));
        filters.add(new HashSet<>(Collections.singletonList(0)));

        int minAge = 18;
        int maxAge = 30;
        RecommendationFilterInputData filterInputData = new RecommendationFilterInputData(filters, minAge, maxAge);
        generateCompatibilityList.filterCompatibilityList(filterInputData);

        List<User> filteredCompatibilityList = generateCompatibilityList.getFilteredCompatibilityList();
        assert filteredCompatibilityList.size() == 1 &&
                filteredCompatibilityList.contains(user1);
    }
}
