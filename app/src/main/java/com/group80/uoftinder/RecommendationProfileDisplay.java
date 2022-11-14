package com.group80.uoftinder;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class RecommendationProfileDisplay extends AppCompatActivity {
    private User current;
    private List<User> compatibleUser;


    //compatibleUser[i] is the current displayed profile
    int i;
    User displayedUser;
    // create variables for all elements that are displayed
    ImageView profilePicture;
    TextView name;
    TextView gender;
    TextView age;
    Button noButton;
    Button yesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("I am here!");
        // set the view to recommendation profile display
        setContentView(R.layout.recommendation_profile_display);

        //TODO: something
        compatibleUser = new ArrayList<>();
        compatibleUser.add(new User());
        displayedUser = compatibleUser.get(i);

        // connect all the different components of the screen
        profilePicture = findViewById(R.id.profilePicture);
        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        age = findViewById(R.id.age);
        noButton = findViewById(R.id.noButton);
        yesButton = findViewById(R.id.yesButton);

        // initialize first user
        setDisplayText(displayedUser);

        // yes button click listener
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToNextDisplayedUser();
                current.addToList(displayedUser, true);


            }
        });
        // no button click listener
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               setToNextDisplayedUser();
                current.addToList(displayedUser, false);
            }
        });
    }

    public void setDisplayText(User displayedUser) {
        profilePicture.setImageURI(displayedUser.getPhotoUrl());
        name.setText(displayedUser.getDisplayName());
        age.setText(Integer.toString(displayedUser.getAge()));
        gender.setText(displayedUser.getGender());
    }
    public void setToNextDisplayedUser() {
        i += 1;
        if (i < compatibleUser.size()) {
            displayedUser = compatibleUser.get(i);
            setDisplayText(displayedUser);
        }
        // else: display a new screen that says no more users left!
        final Context context = this;
        Intent intent = new Intent(context, NoNewRecommendation.class);
        startActivity(intent);
    }
}
