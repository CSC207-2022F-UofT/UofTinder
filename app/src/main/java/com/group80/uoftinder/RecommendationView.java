package com.group80.uoftinder;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class RecommendationView extends AppCompatActivity implements RecommendationViewInterface {
    private User current;

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
        // set the view to recommendation profile display
        setContentView(R.layout.recommendation_profile_display);

        // connect all the different components of the screen
        profilePicture = findViewById(R.id.profilePicture);
        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        age = findViewById(R.id.age);
        noButton = findViewById(R.id.noButton);
        yesButton = findViewById(R.id.yesButton);

        // initialize first user
        RecommendationPresenter recPresenter = new RecommendationPresenter();
        User displayedUser = recPresenter.displayNextUser();

        // yes button click listener
        yesButton.setOnClickListener(view -> {
            buttonClick(displayedUser, true);
        });

        // no button click listener
        noButton.setOnClickListener(view -> {
            buttonClick(displayedUser, false);

        });
    }

    private void buttonClick(User displayedUser, boolean liked) {
        // add displayed User to viewed/liked list
        current.addToList(displayedUser, liked);
        // displays next user
        RecommendationPresenter recPresenter = new RecommendationPresenter();
        recPresenter.displayNextUser();

    }
    // set information on screen to the displayed User's information
    public void displayUser(User displayedUser) {
        profilePicture.setImageURI(displayedUser.getPhotoUrl());
        name.setText(displayedUser.getDisplayName());
        age.setText(Integer.toString(displayedUser.getAge()));
        gender.setText(displayedUser.getGender());
    }

    public void noCompatibleUser() {
        // display a new screen that says no more users left!
        final Context context = this;
        Intent intent = new Intent(context, NoNewRecommendation.class);
        startActivity(intent);
    }
}
