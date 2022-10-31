package com.group80.uoftinder;

import java.util.ArrayList;

public class RecommendationPresenter implements RecOutputBoundary{
    private User currentUser;

    public void displayCompatibleUsers(ArrayList<User> compatibilityList) {
        if (compatibilityList.size() > 0) {
            // display compatibilityList[0]
        }
        else {
            // display No Compatible User Error Message
        }
    }
}
