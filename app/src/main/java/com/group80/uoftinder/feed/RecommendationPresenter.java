package com.group80.uoftinder.feed;

import com.group80.uoftinder.MatchInteractor;
import com.group80.uoftinder.entities.User;

import java.util.List;
import java.util.Set;

/**
 * A presenter class (that also acts as a controller, per the MVP design pattern) that
 * calls the appropriate use case and return the necessary information to the view, through
 * recViewInterface
 */
public class RecommendationPresenter {
    private GenerateCompatibilityList genCompatibilityList;
    private RecViewInterface recViewInterface;
//    private MatchCreatorInteractor mci;
    private User currUser;

    /**
     * Initialize the attributes of a RecommendationPresenter instance
     */
    public RecommendationPresenter(User currUser, RecViewInterface recViewInterface) {
        this.currUser = currUser;
        this.genCompatibilityList = new GenerateCompatibilityList(currUser);
        this.recViewInterface = recViewInterface;
    }

    /**
     * Calls the filterCompatibilityList method in the Use Case Interactor to actually
     * perform the logic required for filtering the list of compatible users based on filters.
     * @param filters   A list of sets of integers that represents the wanted criteria
     * @param minAge    The minimum age, inclusive
     * @param maxAge    The maximum age, inclusive
     */
    public void filterCompatibilityList(List<Set<Integer>> filters, int minAge, int maxAge) {
        RecommendationFilterInputData filterInputData = new RecommendationFilterInputData(filters, minAge, maxAge);
        genCompatibilityList.filterCompatibilityList(filterInputData);
    }

    /**
     * Revert all previously selected filters to show all users in compatibility list
     * without checking or caring whether they satisfy certain filtering criteria.
     */
    public void revertFilters() {
        genCompatibilityList.setShowFilteredList(false);
    }

    /**
     * Call the use case to retrieve the most compatible user to curUser and send it to the
     * presenter
     */
    public void displayUser() {
        User mostCompUser = genCompatibilityList.showMostCompUser();
        if (mostCompUser != null) {
            displayMostCompUser(mostCompUser);
        }
        else {
            displayNoCompatibleUser();
        }
    }

    /**
     * Remove the most compatible user to curUser so that the next most compatible user is ready to
     * be displayed
     */
    public void nextUser() {
        genCompatibilityList.removeMostCompUser();
    }

    /**
     * Display the most compatible user to curUser in the view, through the recViewInterface
     * and update the displayedUser attribute
     * @param user: the most compatible user to curUser
     */
    public void displayMostCompUser(User user) {
        recViewInterface.setDisplayedUser(user);
        recViewInterface.showUser();
    }

    /**
     * Display the no compatible user message in the view, through the recViewInterface and update
     * the displayedUser attribute
     */
    public void displayNoCompatibleUser() {
        recViewInterface.setDisplayedUser(null);
        recViewInterface.noCompatibleUser();
    }

    /**
     * Use the MatchInteractor static method checkforMatchAndCreate in order to determine if a match
     * can be created between the current user and the displayed user
     */
    public void useMatchCreator() {
        boolean matchCreated = MatchInteractor.checkForMatchAndCreate(currUser,
                recViewInterface.getDisplayedUser());
        if (matchCreated) { // if we have successfully created a match, we display a pop-up using
            // the recViewInterface
            recViewInterface.createPopUp();
        }
    }

    /**
     * Use the MatchInteractor to update the current user's displayed and liked lists
     * @param liked: whether currUser likes the displayed user
     */
    public void updateLists(boolean liked) {
        MatchInteractor.addToList(currUser, recViewInterface.getDisplayedUser(), liked);
    }

    /**
     * Regenerate the compatibilityList to curUser so that it is up to date
     */
    public void regenerate() {
        genCompatibilityList.calculateCompatibilityList();
    }
}
