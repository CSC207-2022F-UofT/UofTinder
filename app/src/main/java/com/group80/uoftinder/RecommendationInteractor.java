package com.group80.uoftinder;

import com.group80.uoftinder.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RecommendationInteractor {

//    private User currentUser;
    private List<User> compatibilityList;
//    private List<String> compatibilityList;

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
        for(int j = compatibilityList.size() - 1; j >= 0; j--) {
            User user = compatibilityList.get(j);
            if(user.getAge() < minAge || user.getAge() > maxAge) {
                compatibilityList.remove(j);
                continue;  // go to the next user
            }
            // answers are formatted in the same way as filters, explained above
            List<Set<Integer>> answers = user.getAnswers();
            // answers.size() is used here for convenience, works under the
            // assumption that answers.size() == filters.size()
            for(int i = 0; i < answers.size(); i++) {
                Set<Integer> currentFilter = filters.get(i);
                Set<Integer> currentAnswers = answers.get(i);
                if(currentFilter.size() == 0)  // if no filters selected, go to next set of answers
                    continue;
                boolean shouldRemove = true;
                for(Integer filterVal: currentFilter) {
                    if(currentAnswers.contains(filterVal))
                        shouldRemove = false;
                }
                if(shouldRemove) {
                    compatibilityList.remove(j);
                    break;  // go to the next user since user already failed 1 filter criteria
                }
            }
        }
//        currentUser.setCompatibilityList(compatibilityList);
    }

    public void setCompatibilityList(List<User> compatibilityList) {
        this.compatibilityList = compatibilityList;
    }

    public List<User> getCompatibilityList() {
        return compatibilityList;
    }

//    public void addToCompatbilityList(User user) {
//        this.compatibilityList.add(user);
//    }
//
//    public void populateCompatibilityList(List<String> userIds) {
//        for(String userId: userIds) {
//            UserRealtimeDbFacade.getUserById(userId, new UserCallback() {
//                @Override
//                public void onUser(User user) {
//                    addToCompatbilityList(user);
//                }
//            });
//        }
//    }
}
