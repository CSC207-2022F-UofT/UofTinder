package com.group80.uoftinder;

import java.util.ArrayList;

public class RecommendationPresenter implements RecOutputBoundary{

    public void displayCompatibleUsers(ArrayList<String> compatibilityList) {
        if (compatibilityList.size() > 0) {
            // display compatibilityList.get(0) and pop
        }
        else {
            // display No Compatible User Error Message
        }
    }
}
