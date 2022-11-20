package com.group80.uoftinder;

import java.util.List;

/**
 * A presenter class (that also acts as a controller, per the MVP design pattern) that
 * calls the appropriate use case and return the necessary information to the view, through
 * recViewInterface
 */
public class RecommendationPresenter implements RecPresenterInterface{
    private GenerateCompatibilityList genCompatibilityList;
    private RecViewInterface recViewInterface;

    /**
     * Initialize the attributes of a RecommendationPresenter instance
     * @param recViewInterface: an instance of RecViewInterface
     */
    public RecommendationPresenter(RecViewInterface recViewInterface) {
        this.genCompatibilityList = new GenerateCompatibilityList();
        genCompatibilityList.orderCompatibilityList();
        this.recViewInterface = recViewInterface;
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
        recViewInterface.showUser(user);
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
     * Regenerate the compatibilityList to curUser so that it is up to date
     */
    public void regenerate() {
        genCompatibilityList.recalculateCompatibilityList();
    }
}
