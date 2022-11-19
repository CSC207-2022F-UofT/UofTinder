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
     * Initialize the RecommendationPresenter instance by creating an instance of
     * GenerateCompatibilityList and assigning the recViewInterface attribute to the an instance of
     * RecViewInterface
     * @param recViewInterface: an instance of RecViewInterface
     */
    public RecommendationPresenter(RecViewInterface recViewInterface) {
        this.genCompatibilityList = new GenerateCompatibilityList(this);
        this.recViewInterface = recViewInterface;
    }

    /**
     * Call the use case to retrieve the most compatible user to curUser and send it to the
     * presenter
     */
    public void displayNextUser() { // should change name of function to showUser be more clear
        genCompatibilityList.showMostCompUser();
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
     * @param user: the most compatible user to curUser
     */
    public void displayUser(User user) {
        recViewInterface.setDisplayedUser(user);
        recViewInterface.showUser(user);
    }

    /**
     * Display the no compatible user message in the view, through the recViewInterface
     */
    public void displayNoCompatibleUser() {
        recViewInterface.setDisplayedUser(null);
        recViewInterface.noCompatibleUser();
    }

    /**
     * Regenerate the compatibilityList to curUser so that it is up to date
     */
    public void regenerate() {
        genCompatibilityList.generateCompatibilityList();
    }
}
