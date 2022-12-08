package com.group80.uoftinder.firebase.realtime;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group80.uoftinder.entities_layer.User;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ucUserReader {
    /**
     * Returns a list of Users of the given userType.
     *
     * @param userType the type of user
     * @param callBack an interface handles the value retrieved
     */
    public static void getAllUsers(String userType, RealtimeDbCallback<List<User>> callBack) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(userType);
        Task<DataSnapshot> task = reference.get();

        Thread thread = new Thread(() -> {
            try {
                DataSnapshot dataSnapshot = Tasks.await(task, 2000, TimeUnit.MILLISECONDS);
                List<User> userList = new LinkedList<>();
                for (DataSnapshot child : dataSnapshot.getChildren())
                    userList.add(child.getValue(User.class));
                callBack.onData(userList);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                Log.e("ucUserWriter", "`getAllUsers` time out!");
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        Task<DataSnapshot> task = reference.child(uid).get();

        Thread thread = new Thread(() -> {
            try {
                DataSnapshot dataSnapshot = Tasks.await(task, 2000, TimeUnit.MILLISECONDS);
                User user = dataSnapshot.getValue(User.class);
                Log.e("TEST", "getUser: " + (user == null ? "NULL" : "NOT NULL"));
                callback.onData(user);
            } catch (NullPointerException e) { // data does not exist in given location
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                Log.e("ucUserWriter", "`getUser` time out!");
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
