package com.group80.uoftinder.firebase.realtime;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserRealtimeDbFacade1 {
    public static void uploadUser(User user) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUserType()).child(user.getUid());
        databaseReference.setValue(user);
    }

    public static List<User> getAllUsers(String userType) {
        List<User> listOfUsers = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
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
}
