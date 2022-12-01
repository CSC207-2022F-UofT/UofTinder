package com.group80.uoftinder.feed;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.group80.uoftinder.R;
import com.group80.uoftinder.UpdateList;
import com.group80.uoftinder.entities.User;

import java.util.List;
import java.util.Set;

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
        Button noButton = findViewById(R.id.noButton);
        Button yesButton = findViewById(R.id.yesButton);

        this.currentUser = (User) getIntent().getSerializableExtra("currentUser");
        this.recPresenter = new RecommendationPresenter(currentUser, RecommendationView.this);
        this.displayedUser = null;
        UpdateList update = new UpdateList(currentUser);

        boolean shouldFilter = getIntent().getBooleanExtra("shouldFilter", false);
        if(shouldFilter) {
            List<Set<Integer>> filters = (List<Set<Integer>>) getIntent().getSerializableExtra("filters");
            int minAge = getIntent().getIntExtra("minAge", 13);
            int maxAge = getIntent().getIntExtra("maxAge", 100);
            recPresenter.filterCompatibilityList(filters, minAge, maxAge);
        }

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

        Button filterButton = findViewById(R.id.filterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecommendationView.this, AcademicFilterActivity.class);
                intent.putExtra("currentUser", currentUser);
                startActivity(intent);
            }
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
        intent.putExtra("currentUser", currentUser);
        startActivity(intent);
    }
}
