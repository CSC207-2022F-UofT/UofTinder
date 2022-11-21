package com.group80.uoftinder.feed;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.group80.uoftinder.R;
import com.group80.uoftinder.UpdateList;
import com.group80.uoftinder.entities.User;
/**
 * Public class that extends AppCompatActivity and implements RecViewInterface.
 * This class displays the most compatible users to currentUser.
 */
public class RecommendationView extends AppCompatActivity implements RecViewInterface {
    private final User currentUser;
    private final RecommendationPresenter recPresenter;
    private User displayedUser;

    // create variables for all elements that are displayed
    private ImageView profilePicture;
    private TextView name;
    private TextView gender;
    private TextView age;
    private Button noButton;
    private Button yesButton;

    /**
     * Initialize an instance of RecommendationView by passing the currentUser.
     * @param currentUser is the current user.
     */
    public RecommendationView(User currentUser) {
        this.currentUser = currentUser;
        this.recPresenter = new RecommendationPresenter(this);
        this.displayedUser = null;
        UpdateList update = new UpdateList(currentUser);
    }


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
        recPresenter.displayUser();


        // yes button click listener
        yesButton.setOnClickListener(view -> {
            buttonClick(displayedUser, true);
        });


        // no button click listener
        noButton.setOnClickListener(view -> {
            buttonClick(displayedUser, false);
        });
    }

    /**
     * Initializes displayedUser to the first User in currentUser's most compatible list.
     * @param displayedUser is the user displayed currently to currentUser.
     */
    public void setDisplayedUser(User displayedUser) {
        this.displayedUser = displayedUser;
    }


    /**
     * helper method for onClickListener method that listens
     * when the 'Yes' and 'No' button is clicked
     * @param displayedUser is the user displayed currently to currentUser
     * @param liked If true, currentUser 'likes' displayedUser, false otherwise
     */
    protected void buttonClick(User displayedUser, boolean liked) {
        // add displayed User to viewed/liked list
        UpdateList.addToList(displayedUser, liked, currentUser.getViewed(), currentUser.getLiked());
        // displays next user
        recPresenter.nextUser();
        recPresenter.displayUser();
    }

    /**
     * Set the information on screen to the displayedUser's information
     * @param displayedUser is the user displayed currently to currentUser
     */
    public void showUser(User displayedUser) {
        profilePicture.setImageURI(displayedUser.getPhotoUrl());
        name.setText(displayedUser.getName());
        age.setText(Integer.toString(displayedUser.getAge()));
        gender.setText(displayedUser.getGender());
    }

    /**
     * displays a screen that tells currentUsers that there are no more compatible users.
     */
    public void noCompatibleUser() {
        final Context context = this;
        Intent intent = new Intent(context, NoNewRecommendation.class);
        startActivity(intent);
    }
}
