package com.group80.uoftinder;
import java.util.ArrayList;

public class GenerateCompatibilityList {
    private User curUser;
    private ArrayList<User> compatibilityList;
    private RecOutputBoundary recOutputBoundary;

    public GenerateCompatibilityList {
        // use User.getScore() in a loop that iterates over all the users
        // to retrieve User scores and compute compatibility into a list
        for (User user : User.getAllUsers()) {
            userScore = user.getScore();
            
        }
    }

    public void update() {
        if (compatibilityList.size() > 0) {
            this.compatibilityList.remove(0);
            recOutputBoundary.displayCompatibleUsers(compatibilityList);
        }
    }
}
