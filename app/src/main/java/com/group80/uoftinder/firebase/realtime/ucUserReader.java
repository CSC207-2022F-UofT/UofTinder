package com.group80.uoftinder.firebase.realtime;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group80.uoftinder.entities.User;

import java.util.LinkedList;
import java.util.List;

public class ucUserReader {
    /**
     * Returns a list of Users of the given userType.
     *
     * @param userType the type of user
     * @param callBack an interface handles the value retrieved
     */
    public static void getAllUsers(String userType, RealtimeDbCallback<List<User>> callBack) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(userType);
        reference.get().addOnSuccessListener(dataSnapshot -> {
            List<User> userList = new LinkedList<>();
            for (DataSnapshot child : dataSnapshot.getChildren())
                userList.add(child.getValue(User.class));

            callBack.onData(userList);
        }).addOnFailureListener(Throwable::printStackTrace);
    }

    /**
     * Search for a User entry in the realtime database with the given user ID.
     *
     * @param userType the type of the user
     * @param uid      the uid entry searched in the realtime database
     * @param callback UserCallback to handle the value retrieved since onComplete has type void
     */
    public static void getUser(String userType, String uid, RealtimeDbCallback<User> callback) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(userType);
        try {
            reference.child(uid).get().addOnSuccessListener(dataSnapshot -> callback.onData(dataSnapshot.getValue(User.class)));
        } catch (NullPointerException e) { // data does not exist in given location
            e.printStackTrace();
        }
    }
}
