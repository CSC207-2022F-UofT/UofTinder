package com.group80.uoftinder;

import com.group80.uoftinder.entities.User;

import java.util.ArrayList;
import java.util.Map;
import java.util.Hashtable;
import java.util.List;

/**
 * A use case that generates a list of compatible users for the current user
 */
public class GenerateCompatibilityList {
    private User curUser;
    private int curUserScore;
    private List<String> compatibilityList;
    private RecPresenterInterface recPresenterInterface;
    private List<User> allUsers;
    private UserScoreFacade usf;

    /**
     * Initialize the class by loading in all the users in the database, creating the
     * UserScoreFacade instance, and getting the score of curUser
     */
    public GenerateCompatibilityList(RecPresenterInterface recPresenterInterface) {
        List<User> allUsers = UserRealtimeDbFacade.getAllUsers(curUser.getUserType());
        usf = new UserScoreFacade(curUser, new CreateAccountInteractor()); // may need to change 2nd param
        User curUser = UserRealtimeDbFacade.getCurrentUser();
        int curUserScore = curUser.getScore();
        this.recPresenterInterface = recPresenterInterface;
    }

    /**
     * Generate the compatibility list for curUser and update the User's compatibilityList attribute.
     * Pass the compatibilityList to recPresenterInterface so that it can display it
     */
    public void generateCompatibilityList() {
        compatibilityList = new ArrayList<>();
        Map<String, Integer> compScores = calculateCompatibilityScores(allUsers);
        for (int i = 0 ; i < users.size() ; i++) {
            String maxKey = getMaxKey(compScores);
            compatibilityList.add(maxKey);
            compScores.remove(maxKey);
        }
//        curUser.setCompatibilityList(compatibilityList);
//        recPresenterInterface.showUsers(compatibilityList);
    }

    /**
     * Return the key that corresponds to the max value in userScore
     * @param userScore: a map that maps the user ID to their compatibility score with the current
     *                 user
     * @return the key that corresponds to the max value in userScore
     */
    private String getMaxKey(Map<String, Integer> userScore) {
        // TODO: change this to get key of max value
        int maxValue = 0;
        String maxKey = "";
        for (String key : userScore.keySet()) {
            Integer currentScore = userScore.get(key);
            if (currentScore != null && currentScore > maxValue) {
//                if (currentScore > maxValue) {
                maxValue = currentScore;
                maxKey = key;
//                }
            }

        }
        return maxKey;
    }

    /**
     * Return a map of user ID to the user's compatibility score with the current user
     * @param users: a list of all User in the database
     * @return a map of user ID to the user's compatibility score with the current user
     */
    private Map<String, Integer> calculateCompatibilityScores(List<User> users) {
        Map<String, Integer> compScores = new Hashtable<>();
        for (User user : users) {
            calculateCompatibiltyScore(compScores, user);
        }
        return compScores;
    }

    /**
     * Mutate compScores to contain a map of compUser's user ID to their compatibility score with
     * curUser
     * @param compScores: a map of user IDs to their compatibility score with curUser
     * @param compUser: the user to compare with curUser
     */
    private void calculateCompatibiltyScore(Map<String, Integer> compScores, User compUser) {
        int userScore = compUser.getScore();
        int compScore = usf.compare(curUserScore, userScore);
        compScores.put(compUser.getUid(), compScore);
    }

    /**
     * Removes the most compatible user (the first element) from compatibilityList and send it to
     * the recPresenterInterface to display it
     */
    public void update() {
        if (compatibilityList.size() > 0) {
            String nextUserId = compatibilityList.get(0);
            User nextUser = UserRealtimeDbFacade.getUser(nextUserId);
            this.compatibilityList.remove(0);
            curUser.setCompatibilityList(compatibilityList);
            recPresenterInterface.displayUser(nextUser);
        }
        else {
            recPresenterInterface.displayNoCompatibleUser();
        }
    }
}
