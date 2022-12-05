package com.group80.uoftinder.feed;

import android.util.Log;

import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.firebase.realtime.UserRealtimeDbFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;

/**
 * A use case class that generates a list of compatible users for the current user
 */
public class GenerateCompatibilityList {
    private User curUser;
    private List<User> compatibilityList;
    private UserScoreFacade usf;
    private Map<User, Integer> compScores;
    private Comparator<User> userScoreComparator;
    private List<User> filteredCompatibilityList;
    private boolean showFilteredList;
    private User alrVisitedUser;
    private String type;

    /**
     * Initialize the attributes of a GenerateCompatibilityList instance
     */
    public GenerateCompatibilityList(User currUser) {
        this.curUser = currUser;
        this.type = this.curUser.getUserType();
        this.usf = new UserScoreFacade(curUser);
        this.userScoreComparator = Comparator.comparing(user -> compScores.get(user));
        calculateCompatibilityList();
        filteredCompatibilityList = new ArrayList<>();
    }

    /**
     * Get the list of all users from the database and
     * then assign compatibilityList to this list.
     */
    private void getAllUsers() {
        UserRealtimeDbFacade.getAllUsers(type, this::setCompatibilityList);
    }

    /**
     * Find and then remove current user from current user's list of
     * compatible users since they should not match with themselves.
     */
    public void removeCurrentUser() {
        User removeUser = null;
        for(User user: compatibilityList) {
            if(user.getUid().equals(curUser.getUid()))
                removeUser = user;
        }
        compatibilityList.remove(removeUser);
    }

    /**
     * Order the compatibility list for curUser and update the User's compatibilityList
     * attribute.
     */
    public void orderCompatibilityList() {
        if (compatibilityList.size() != 0) {
            compScores = calculateCompatibilityScores(compatibilityList);
            compatibilityList.sort(Collections.reverseOrder(userScoreComparator));
        } else {
            compScores = new HashMap<>();
        }
    }

    /**
     * Return a map of User to the user's compatibility score with the current user
     * @param users: a list of all User in the database
     * @return a map of User to the user's compatibility score with the current user
     */
    private Map<User, Integer> calculateCompatibilityScores(List<User> users) {
        Map<User, Integer> compScores = new HashMap<>();
        for (User user : users) {
            calculateCompatibilityScore(compScores, user);
        }
        return compScores;
    }

    /**
     * Mutate compScores to contain a map of compUser to their compatibility score with
     * curUser
     * @param compScores: a map of User to their compatibility score with curUser
     * @param compUser: the user to compare with curUser
     */
    private void calculateCompatibilityScore(Map<User, Integer> compScores, User compUser) {
        int userScore = compUser.getScore();
        int compScore = usf.compare(userScore);
        compScores.put(compUser, compScore);
    }

    /**
     * Returns the most compatible user (the first element) from compatibilityList or
     *  filteredCompatibilityList, depeneding on the value of showFilteredList.
     * @return  the most compatible user to current user
     */
    public User showMostCompUser() {
        List<User> showList = compatibilityList;
        if(showFilteredList)
            showList = filteredCompatibilityList;

        if (showList.size() > 0) {
            User displayUser = showList.get(0);
            return displayUser;
        }
        else {
            return null;
        }
    }

    /**
     * Remove the most compatible user (the first element) from compatibilityList.
     *
     * If showFilteredList is true, remove the most compatible user (the first element)
     * from filteredCompatibilityList and that user object from compatibilityList
     */
    public void removeMostCompUser() {
        if(showFilteredList) {
            compatibilityList.remove(filteredCompatibilityList.get(0));
            filteredCompatibilityList.remove(0);
        }
        else {
            compatibilityList.remove(0);
        }
    }

    /**
     * Calculate compatibilityList
     */
    public void calculateCompatibilityList() {
        getAllUsers();
        removeCurrentUser();
        removeVisitedUsers();
        orderCompatibilityList();
    }

    /**
     * Remove the users from compatibilityList that are in the current user's visited list
     */
    public void removeVisitedUsers() {
        List<String> visitedList = curUser.getViewed();
        for (String visitedUserId : visitedList) {
            UserRealtimeDbFacade.getUser(type, visitedUserId, this::setAlrVisitedUser);
            if (compatibilityList.contains(alrVisitedUser)) {
                compatibilityList.remove(alrVisitedUser);
            }
        }
    }

    /**
     * Set compatibilityList to usersList
     * @param usersList: what to set compatibilityList to
     */
    public void setCompatibilityList(List<User> usersList) {
        this.compatibilityList = usersList;
    }

    /**
     * Set usf to newUsf
     * @param newUsf: what to set usf to
     */
    public void setUsf(UserScoreFacade newUsf) {
        this.usf = newUsf;
    }

    /**
     * Set alrVisitedUser to newAlrVisitedUser
     * @param newAlrVisitedUser: what to set alrVisitedUser to
     */
    public void setAlrVisitedUser(User newAlrVisitedUser) {
        this.alrVisitedUser = newAlrVisitedUser;
    }

    /**
     * Get compatibilityList
     * @return the compatibilityList attribute
     */
    public List<User> getCompatibilityList() {
        return this.compatibilityList;
    }

    /**
     * Get filteredCompatibilityList
     * @return the filteredCompatibilityList instance variable
     */
    public List<User> getFilteredCompatibilityList() {
        return this.filteredCompatibilityList;
    }

    /**
     * Mutates the instance variable filteredCompatibilityList based on criteria given by
     * filters parameter and minimum/maximum age values.
     * @param filterInputData   An input data structure that contains the filtering criteria
     */
    public void filterCompatibilityList(RecommendationFilterInputData filterInputData) {
        List<Set<Integer>> filters = filterInputData.getFilters();
        int minAge = filterInputData.getMinAge();
        int maxAge = filterInputData.getMaxAge();

        filteredCompatibilityList = new ArrayList<>();
        filteredCompatibilityList.addAll(compatibilityList);

        for(int j = filteredCompatibilityList.size() - 1; j >= 0; j--) {
            User user = filteredCompatibilityList.get(j);
            if(user.getAge() < minAge || user.getAge() > maxAge) {
                filteredCompatibilityList.remove(j);
                continue;  // go to the next user
            }
            // answers are formatted in the same way as filters, explained above
            List<List<Integer>> answers = user.getAnswers();
            // answers.size() is used here for convenience, works under the
            // assumption that answers.size() == filters.size()
            for(int i = 0; i < filters.size(); i++) {
                Set<Integer> currentFilter = filters.get(i);
                List<Integer> currentAnswers = answers.get(i);
                if(currentFilter.size() == 0)  // if no filters selected, go to next set of answers
                    continue;
                boolean shouldRemove = true;
                for(Integer filterVal: currentFilter) {
                    if(currentAnswers.contains(filterVal))
                        shouldRemove = false;
                }
                if(shouldRemove) {
                    filteredCompatibilityList.remove(j);
                    break;  // go to the next user since user already failed 1 filter criteria
                }
            }
        }
        setShowFilteredList(true);
    }

    /**
     * Sets showFilteredList based on parameter showFilteredList.
     *
     * When this is false, compatibilityList.get(0) would be
     * displayed next instead of filteredCompatibilityList.get(0).
     * Vice versa for when this is true.
     *
     * @param showFilteredList  whether to display filteredCompatibilityList.get(0)
     */
    public void setShowFilteredList(boolean showFilteredList) {
        this.showFilteredList = showFilteredList;
    }
}