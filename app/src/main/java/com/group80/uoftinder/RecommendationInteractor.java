package com.group80.uoftinder;

import com.group80.uoftinder.entities.User;

import java.util.ArrayList;
import java.util.HashSet;

public class RecommendationInteractor {

    private User currUser;
    private ArrayList<User> compatibilityList;
//    private RecommendationOutputBoundary recommendationOutputBoundary;

    public RecommendationInteractor() {

    }

    public void FilterCompatibilityList(int minAge, int maxAge) {
        for(User user: compatibilityList) {
            HashSet<Integer>[] answers = user.getAnswers();

        }
    }
}
