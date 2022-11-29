package com.group80.uoftinder.feed;
import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.group80.uoftinder.R;
import com.group80.uoftinder.UpdateList;
import com.group80.uoftinder.entities.User;
/**
 * Public class that extends AppCompatActivity and implements RecViewInterface.
 * This class displays the most compatible users to currentUser.
 */
public class RecommendationView extends AppCompatActivity implements RecViewInterface {
    private User currentUser;
    private RecommendationPresenter recPresenter;
    private User displayedUser;

    // create variables for all elements that are displayed
    private ImageView profilePicture;
    private TextView name;
    private TextView gender;
    private TextView age;
    private Button noButton;
    private Button yesButton;

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

        this.currentUser = (User) getIntent().getSerializableExtra("currentUser");
        this.recPresenter = new RecommendationPresenter(currentUser, RecommendationView.this);
        this.displayedUser = null;
        UpdateList update = new UpdateList(currentUser);

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

    @Override
    /**
     * Initializes displayedUser to the first User in currentUser's most compatible list.
     * @param displayedUser is the user displayed currently to currentUser.
     */
    public void setDisplayedUser(User displayedUser) {
        this.displayedUser = displayedUser;
    }

    @Override
    /**
     * Returns displayedUser that is currently being displayed to currentUser.
     */
    public User getDisplayedUser() { return this.displayedUser; }


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
        if (liked) {
            recPresenter.useMatchCreator(); // if we liked the displayed user, we call upon
            // the match creator to check if a match can be created
        }
        recPresenter.nextUser();
        recPresenter.displayUser();
    }

    @Override
    /**
     * Set the information on screen to the displayedUser's information
     * @param displayedUser is the user displayed currently to currentUser
     */
    public void showUser() {
        profilePicture.setImageURI(displayedUser.getPhotoUrl());
        name.setText(displayedUser.getName());
        age.setText(Integer.toString(displayedUser.getAge()));
        gender.setText(displayedUser.getGender());
    }

    @Override
    /**
     * displays a screen that tells currentUsers that there are no more compatible users.
     */
    public void noCompatibleUser() {
        final Context context = this;
        Intent intent = new Intent(context, NoNewRecommendation.class);
        startActivity(intent);
    }

    @Override
    /**
     * Creates a pop-up message at the button of the screen when the current user has matched
     * with the person they clicked like on
     */
    public void createPopUp() {
        Toast.makeText(RecommendationView.this,
                "You matched with " + getDisplayedUser().getName() + "!",
                Toast.LENGTH_SHORT).show();
    }
}
