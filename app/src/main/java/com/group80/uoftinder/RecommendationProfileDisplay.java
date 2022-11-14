package com.group80.uoftinder;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class RecommendationProfileDisplay extends AppCompatActivity{
    // creating the users (TESTING)
    User current = new User("currentpic", "currentname", 0,
            "currentlocation", "currentbio");
    User one = new User("pic1", "name1", 1, "loc1", "bio1");
    User two = new User("pic2", "name2", 2, "loc2", "bio2");
    User three = new User("pic3", "name3", 3, "loc3", "bio3");
    User[] compatibleUser = {one,two,three};


    //compatibleUser[i] is the current displayed profile
    int i;
    User displayedUser = compatibleUser[i];
    // create variables for all elements that are displayed
    ImageView profilePicture;
    TextView nameAge;
    TextView location;
    TextView bio;
    Button noButton;
    Button yesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the view to recommendation profile display
        setContentView(R.layout.recommendation_profile_display);

        // connect all the different components of the screen
        profilePicture = findViewById(R.id.profilePicture);
        nameAge = findViewById(R.id.nameAge);
        location = findViewById(R.id.location);
        bio = findViewById(R.id.bio);
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
        //profilePicture.setImage(displayedUser.getProfilePicture());
        nameAge.setText(displayedUser.getName() + ", " + displayedUser.getAge());
        location.setText(displayedUser.getLocation());
        bio.setText(displayedUser.getBio());
    }
    public void setToNextDisplayedUser() {
        i += 1;
        if (i < compatibleUser.length) {
            displayedUser = compatibleUser[i];
            setDisplayText(displayedUser);
        }
        // else: display a new screen that says no more users left!
    }







}
