package com.group80.uoftinder;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.group80.uoftinder.firebase.realtime.User;
import com.group80.uoftinder.firebase.realtime.UserCallback;
import com.group80.uoftinder.firebase.realtime.UserRealtimeDbFacade;

import java.util.List;

public class HelloWorld extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // working example of get user by ID
        UserRealtimeDbFacade.getUserById("lisinan2", new UserCallback() {
            @Override
            public void onUser(User user) {
                Log.d("firebase", "User:" + user.getLastname() + user.getFirstname());
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_world);

//        UserRealtimeDbFacade.getAllUsers("academic");
//
//        User user1 = new User("Lance", "Li", "lisinan2", "academic");
//        UserRealtimeDbFacade.uploadUser(user1);
//        User user2 = new User("Vedant", "Goel", "vgvg", "academic");
//        UserRealtimeDbFacade.uploadUser(user2);
//
//        int counter = 0;
//        List<User> allUsers = UserRealtimeDbFacade.getAllUsers("academic");
//
//        for (User user : allUsers) {
//            Log.d(TAG, user.getUid());
//            counter++;
//        }

//
//        TextView textView = findViewById(R.id.helloWorldTextEdit);
//        textView.setText(result.getFirstname());
    }
}