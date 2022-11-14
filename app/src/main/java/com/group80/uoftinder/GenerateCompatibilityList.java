package com.group80.uoftinder;
import android.icu.text.UFormat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class GenerateCompatibilityList {
    private User curUser;
    private ArrayList<User> compatibilityList;
    private RecOutputBoundary recOutputBoundary;
    private List<User> users;

    //TODO: use API
    // Navigate all users
    // RelatimDbControllder realtimeDbController = ...;
    // List<Users> realtimeDbController.getAllUsers(Void unused);
    //TODO: end use API

    public GenerateCompatibilityList() {
        RealtimeDbController realtimeDbController = ...;
        List<User> users = realtimeDbController.getAllUsers(Void unused, type=curUser.getUserType());
    }

    public void generateCompatibilityList() {
        ArrayList<Integer> compScores = calculateCompatibilityScores(users);
        for (int i = 0 ; i < users.size() ; i++) {
            int maxIndex = getMaxIndex(compScores);
            compatibilityList.add(users.get(maxIndex));
            compScores.remove(maxIndex);
            users.remove(maxIndex);
        }
        recOutputBoundary.displayCompatibleUsers(compatibilityList);
    }

    public int getMaxIndex(ArrayList<Integer> lst) {
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < len(lst); i++) {
            if (lst.get(i) > max) {
                max = lst.get(i);
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    public ArrayList<Integer> calculateCompatibilityScores(List<User> users) {
        ArrayList<Integer> compScores = new ArrayList<>();
        for (User user : users) {
            compScores.add(user.getScore() & curUser.getScore());
        }

        return compScores;
    }

    public void update() {
        if (compatibilityList.size() > 0) {
            this.compatibilityList.remove(0);
            recOutputBoundary.displayCompatibleUsers(compatibilityList);
        }
    }
}
