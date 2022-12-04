package com.group80.uoftinder.feed;
import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.group80.uoftinder.ContactsActivity;
import com.group80.uoftinder.R;
import com.group80.uoftinder.Constants;
import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.login_use_case.LoginActivity;

import java.io.Serializable;
import java.util.ArrayList;
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
    private TextView info;

    private List<Set<Integer>> filters = new ArrayList<>();
    private int minAge = Constants.MIN_AGE;
    private int maxAge = Constants.MAX_AGE;

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
        info = findViewById(R.id.info);
        Button noButton = findViewById(R.id.noButton);
        Button yesButton = findViewById(R.id.yesButton);

        this.currentUser = (User) getIntent().getSerializableExtra(Constants.CURRENT_USER_STRING);
        this.recPresenter = new RecommendationPresenter(currentUser, RecommendationView.this);
        this.displayedUser = null;

        boolean shouldFilter = getIntent().getBooleanExtra(Constants.SHOULD_FILTER_STRING, false);
        if(shouldFilter) {
            filters = (List<Set<Integer>>) getIntent().getSerializableExtra(Constants.FILTERS_STRING);
            minAge = getIntent().getIntExtra(Constants.MIN_AGE_STRING, Constants.MIN_AGE);
            maxAge = getIntent().getIntExtra(Constants.MAX_AGE_STRING, Constants.MAX_AGE);
            recPresenter.filterCompatibilityList(filters, minAge, maxAge);
        }
        else {
            recPresenter.revertFilters();
        }

        // initialize first user
        recPresenter.displayUser();

        // yes button click listener
        yesButton.setOnClickListener(view -> {
            buttonClick(true);
        });

        // no button click listener
        noButton.setOnClickListener(view -> {
            buttonClick(false);
        });

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Logs out the current logged in user and return to the main Login page.
             * @param view  Current view
             */
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(RecommendationView.this, LoginActivity.class));
            }
        });

        Button chatButton = findViewById(R.id.chatButton);
        chatButton.setOnClickListener(new View.OnClickListener()  {
            /**
             * Enters the chat page for the current user to chat with their matched users.
             * @param view  Current view
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecommendationView.this, ContactsActivity.class);
                // TODO: remove such dependency
                intent.putExtra(Constants.CURRENT_USER_STRING, currentUser);
                startActivity(intent);
            }
        });

        Button filterButton = findViewById(R.id.filterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Enters the filter page for current user to choose filtering criteria.
             * @param view  Current view
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecommendationView.this, AcademicFilterActivity.class);
                intent.putExtra(Constants.CURRENT_USER_STRING, currentUser);
                intent.putExtra(Constants.FILTERS_STRING, (Serializable) filters);
                intent.putExtra(Constants.MIN_AGE_STRING, minAge);
                intent.putExtra(Constants.MAX_AGE_STRING, maxAge);
                startActivity(intent);
            }
        });
    }

    /**
     * Initializes displayedUser to the first User in currentUser's most compatible list.
     * @param displayedUser is the user displayed currently to currentUser.
     */
    @Override
    public void setDisplayedUser(User displayedUser) {
        this.displayedUser = displayedUser;
    }

    /**
     * Returns displayedUser that is currently being displayed to currentUser.
     */
    @Override
    public User getDisplayedUser() { return this.displayedUser; }


    /**
     * helper method for onClickListener method that listens
     * when the 'Yes' and 'No' button is clicked
     * @param liked If true, currentUser 'likes' displayedUser, false otherwise
     */
    protected void buttonClick(boolean liked) {
        // add displayed User to viewed/liked list
        recPresenter.updateLists(liked);
        // displays next user
        if (liked) {
            recPresenter.useMatchCreator(); // if we liked the displayed user, we call upon
            // the match creator to check if a match can be created
        }
        recPresenter.nextUser();
        recPresenter.displayUser();
    }

    /**
     * Set the information on screen to the displayedUser's information
     */
    @Override
    public void showUser() {
        profilePicture.setImageURI(displayedUser.getPhotoUrl());
        name.setText(displayedUser.getName());
        age.setText(Integer.toString(displayedUser.getAge()));
        gender.setText(displayedUser.getGender());
        info.setText(displayedUser.getUserInfoString());
    }

    /**
     * displays a screen that tells currentUsers that there are no more compatible users.
     */
    @Override
    public void noCompatibleUser() {
        final Context context = this;
        Intent intent = new Intent(context, NoNewRecommendation.class);
        intent.putExtra(Constants.CURRENT_USER_STRING, currentUser);
        intent.putExtra(Constants.FILTERS_STRING, (Serializable) filters);
        intent.putExtra(Constants.MIN_AGE_STRING, minAge);
        intent.putExtra(Constants.MAX_AGE_STRING, maxAge);
        startActivity(intent);
    }

    /**
     * Creates a pop-up message at the button of the screen when the current user has matched
     * with the person they clicked like on
     */
    @Override
    public void createPopUp() {
        Toast.makeText(RecommendationView.this,
                "You matched with " + getDisplayedUser().getName() + "!",
                Toast.LENGTH_SHORT).show();
    }
}
