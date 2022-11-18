package com.group80.uoftinder;

import java.util.List;

public class RecommendationPresenter implements RecPresenterInterface{
    private GenerateCompatibilityList genCompatibilityList;

    public RecommendationPresenter() {
        genCompatibilityList = new GenerateCompatibilityList(this);
    }

    public void displayNextUser(User user) {
        genCompatibilityList.update();
    }

    public void displayUser(User user) {
        RecViewInterface.showUser(user);
    }

    public void displayNoCompatibleUser() {
        RecViewInterface.noCompatibleUser();
    }

    public void regenerate() {
        genCompatibilityList.generateCompatibilityList();
    }
}
