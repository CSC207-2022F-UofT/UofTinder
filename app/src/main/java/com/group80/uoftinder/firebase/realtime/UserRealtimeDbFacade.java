package com.group80.uoftinder.firebase.realtime;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group80.uoftinder.firebase.realtime.User;

import java.util.ArrayList;
import java.util.List;

public class UserRealtimeDbFacade {

    public static void uploadUser(User user) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUserType()).child(user.getUid());
        databaseReference.setValue(user);
    }

    public static List<User> getAllUsers(String userType) {
        List<User> listOfUsers = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(userType);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    listOfUsers.add(child.getValue(User.class));
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return listOfUsers;
    }


    /**
     * Search for a User entry in the realtime database with the given user ID.
     * @param uid       the uid entry searched in the realtime database
     * @param callback  UserCallback to handle the value retrieved since onComplete has type void
     */
    public static void getUserById(String uid, UserCallback callback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child("academic");
        databaseReference.orderByChild("uid").equalTo(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    for (DataSnapshot childSnapshot : task.getResult().getChildren())
                        callback.onUser(childSnapshot.getValue(User.class));
                }
            }
        });
    }
}
