package com.group80.uoftinder;

import android.util.Log;

import com.group80.uoftinder.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RecommendationInteractor {

    private User currentUser;
    private ArrayList<String> compatibilityList;
//    private RecommendationOutputBoundary recommendationOutputBoundary;

    public RecommendationInteractor() {

    }


    public void filterCompatibilityList(List<Set<Integer>> filters, int minAge, int maxAge) {
        for(String userId: compatibilityList) {
            UserRealtimeDbFacade.getUserById(userId, new UserCallback() {
                @Override
                public void onUser(User user) {
                    List<Set<Integer>> answers = user.getAnswers();
                    for(int i = 0; i < answers.size(); i++) {
                        Set<Integer> currentFilter = filters.get(i);
                        Set<Integer> currentAnswers = answers.get(i);
                        boolean shouldRemove = true;
                        for(Integer filterVal: currentFilter) {
                            if(currentAnswers.contains(filterVal))
                                shouldRemove = false;
                        }
                        if(shouldRemove || minAge > user.getAge() || user.getAge() > maxAge)
                            compatibilityList.remove(i);
                    }
                }
            });
        }
        currentUser.setCompatibilityList(compatibilityList);
    }
}
