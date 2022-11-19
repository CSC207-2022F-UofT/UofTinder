package com.group80.uoftinder;

import java.util.List;

public class RecommendationPresenter implements RecPresenterInterface{
    private GenerateCompatibilityList genCompatibilityList;
    private RecViewInterface recViewInterface;

    public RecommendationPresenter(RecViewInterface recViewInterface) {
        this.genCompatibilityList = new GenerateCompatibilityList(this);
        this.recViewInterface = recViewInterface;
    }

    public void displayNextUser() {
        genCompatibilityList.update();
    }

    public void displayUser(User user) {
        recViewInterface.setDisplayedUser(user);
        recViewInterface.showUser(user);
    }

    public void displayNoCompatibleUser() {
        recViewInterface.setDisplayedUser(null);
        recViewInterface.noCompatibleUser();
    }

    public void regenerate() {
        genCompatibilityList.generateCompatibilityList();
    }
}
