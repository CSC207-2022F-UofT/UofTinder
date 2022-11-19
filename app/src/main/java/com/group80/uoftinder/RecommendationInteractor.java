package com.group80.uoftinder;

import com.group80.uoftinder.entities.User;

import java.util.List;
import java.util.Set;

public class RecommendationInteractor {

    private User currentUser;
    private List<String> compatibilityList;

    public RecommendationInteractor() {

    }

    /**
     * Mutates the instance variable compatibilityList based on criteria given by
     * filters parameter and minimum/maximum age values. Also updates compatibilityList
     * of currentUser, beyond just mutating the instance variable compatibilityList.
     *
     * @param filters   A list of sets of integers that represents the wanted criteria.
     *                  Each set in the list corresponds to a filtering question.
     *                  For academic users, filters.size() should be 3 (program of study,
     *                  year of study, and campus). Each integer represents a selected choice.
     *                  {0, 3, 5} means the first, fourth, and sixth choices were checked.
     * @param minAge    The minimum age, inclusive
     * @param maxAge    The maximum age, inclusive
     */
    public void filterCompatibilityList(List<Set<Integer>> filters, int minAge, int maxAge) {
        for(String userId: compatibilityList) {
            UserRealtimeDbFacade.getUserById(userId, new UserCallback() {
                @Override
                public void onUser(User user) {
                    // answers are formatted in the same way as filters, explained above
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
