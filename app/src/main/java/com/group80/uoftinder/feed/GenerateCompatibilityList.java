package com.group80.uoftinder.feed;

import com.group80.uoftinder.entities.User;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * A use case class that generates a list of compatible users for the current user
 */
public class GenerateCompatibilityList {
    private User curUser;
    private int curUserScore;
    private List<User> compatibilityList;
    private RecPresenterInterface recPresenterInterface;
    private UserScoreFacade usf;
    private Map<User, Integer> compScores;
    private Comparator<User> userScoreComparator;

    /**
     * Initialize the attributes of a GenerateCompatibilityList instance
     */
    public GenerateCompatibilityList(RecPresenterInterface recPresenterInterface) {
        getAllUsers();
        this.usf = new UserScoreFacade(curUser);
        this.curUser = UserRealtimeDbFacade.getCurrentUser();
        this.curUserScore = curUser.getScore();
        this.recPresenterInterface = recPresenterInterface;
        this.userScoreComparator = Comparator.comparing(user -> {
            compScores.get(user.getUid());
        });
    }

    /**
     * Get the list of all users from the database and assign compatibilityList to this list
     */
    private void getAllUsers() {
        UserRealtimeDbFacade.getAllUsers(userList -> {
            setCompatibilityList(userList);
        });
    }

    /**
     * Order the compatibility list for curUser and update the User's compatibilityList
     * attribute.
     */
    public void orderCompatibilityList() {
        compScores = calculateCompatibilityScores(compatibilityList);
        compatibilityList.sort(userScoreComparator);
        curUser.setCompatibilityList(compatibilityList);

//        compatibilityList = new ArrayList<>();
//        Map<String, Integer> compScores = calculateCompatibilityScores(allUsers);
//        for (int i = 0 ; i < users.size() ; i++) {
//            String maxKey = getMaxKey(compScores);
//            compatibilityList.add(maxKey);
//            compScores.remove(maxKey);
//        }
//        curUser.setCompatibilityList(compatibilityList);
//        recPresenterInterface.showUsers(compatibilityList);
    }

//    /**
//     * Return the key that corresponds to the max value in userScore
//     * @param userScore: a map that maps the user ID to their compatibility score with the current
//     *                 user
//     * @return the key that corresponds to the max value in userScore
//     */
//    private String getMaxKey(Map<String, Integer> userScore) {
//        int maxValue = 0;
//        String maxKey = "";
//        for (String key : userScore.keySet()) {
//            Integer currentScore = userScore.get(key);
//            if (currentScore != null && currentScore > maxValue) {
////                if (currentScore > maxValue) {
//                maxValue = currentScore;
//                maxKey = key;
////                }
//            }
//
//        }
//        return maxKey;
//    }

    /**
     * Return a map of User to the user's compatibility score with the current user
     * @param users: a list of all User in the database
     * @return a map of User to the user's compatibility score with the current user
     */
    private Map<User, Integer> calculateCompatibilityScores(List<User> users) {
        Map<User, Integer> compScores = new HashMap<User, Integer>();
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
     * Get the most compatible user (the first element) from compatibilityList and send it to
     * the recPresenterInterface to display it
     */
    public void showMostCompUser() {
        if (compatibilityList.size() > 0) {
//            String displayUserId = compatibilityList.get(0);
//            User displayUser = UserRealtimeDbFacade.getUser(displayUserId);
            User displayUser = compatibilityList.get(0);
            recPresenterInterface.displayMostCompUser(displayUser);
        }
        else {
            recPresenterInterface.displayNoCompatibleUser();
        }
    }

    /**
     * Remove the most compatible user (the first element) from compatibilityList and update the
     * User object's compatibilityList
     */
    public void removeMostCompUser() {
        this.compatibilityList.remove(0);
        curUser.setCompatibilityList(compatibilityList);
    }

    /**
     * Recalculate compatibilityList
     */
    public void recalculateCompatibilityList() {
        getAllUsers();
        orderCompatibilityList();
    }

    /**
     * Set curUser to newUser
     * @param newUser: what to set curUser to
     */
    public void setCurUser(User newUser) {
        this.curUser = newUser;
    }

    /**
     * Set compatibilityList to usersList
     * @param usersList: what to set CompatibilityList to
     */
    public void setCompatibilityList(List<User> usersList) {
        this.compatibilityList = usersList;
    }

    /**
     * Set curUserScore to newScore
     * @param newScore: what to set curUserScore to
     */
    public void setCurUserScore(int newScore) {
        this.curUserScore = newScore;
    }

    /**
     * Get compatibilityList
     * @return the compatibilityList attribute
     */
    public List<User> getCompatibilityList() {
        return this.compatibilityList;
    }
}
