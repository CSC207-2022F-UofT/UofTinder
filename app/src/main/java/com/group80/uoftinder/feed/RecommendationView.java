package com.group80.uoftinder.feed;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.group80.uoftinder.Constants;
import com.group80.uoftinder.chat.contacts_list.ContactsActivity;
import com.group80.uoftinder.R;
import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.firebase.ProfileImagePresenter;
import com.group80.uoftinder.firebase.ProfileImageViewInterface;
import com.group80.uoftinder.login_use_case.LoginActivity;
import com.group80.uoftinder.logout.LogOutInteractor;
import com.group80.uoftinder.logout.LogOutPresenter;
import com.group80.uoftinder.logout.LogOutViewInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * This class displays the most compatible users to currentUser.
 * This serves as the central page of the app with connections
 * to the chat, logging out, and filtering functionality.
 */
public class RecommendationView extends AppCompatActivity implements RecommendationViewInterface, ProfileImageViewInterface, LogOutViewInterface {
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

    private LogOutPresenter logOutPresenter;

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
        if (shouldFilter) {
            filters = (List<Set<Integer>>) getIntent().getSerializableExtra(Constants.FILTERS_STRING);
            minAge = getIntent().getIntExtra(Constants.MIN_AGE_STRING, Constants.MIN_AGE);
            maxAge = getIntent().getIntExtra(Constants.MAX_AGE_STRING, Constants.MAX_AGE);
            recPresenter.filterCompatibilityList(filters, minAge, maxAge);
        } else {
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

        logOutPresenter = new LogOutPresenter(RecommendationView.this, new LogOutInteractor());

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Logs out the current logged in user and return to the main Login page.
             * @param view  Current view
             */
            @Override
            public void onClick(View view) {
                logOutPresenter.signOut();
            }
        });

        Button chatButton = findViewById(R.id.chatButton);
        chatButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Enters the chat page for the current user to chat with their matched users.
             * @param view  Current view
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecommendationView.this, ContactsActivity.class);
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
     * Switches UI to Login screen after user has signed out
     */
    @Override
    public void showLogin() {
        Toast.makeText(RecommendationView.this, "You have signed out!",
                Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RecommendationView.this, LoginActivity.class));
        finish();
    }

    /**
     * Returns displayedUser that is currently being displayed to currentUser.
     */
    @Override
    public User getDisplayedUser() {
        return this.displayedUser;
    }

    /**
     * Initializes displayedUser to the first User in currentUser's most compatible list.
     *
     * @param displayedUser is the user displayed currently to currentUser.
     */
    @Override
    public void setDisplayedUser(User displayedUser) {
        this.displayedUser = displayedUser;
    }

    /**
     * helper method for onClickListener method that listens
     * when the 'Yes' and 'No' button is clicked
     *
     * @param liked If true, currentUser 'likes' displayedUser, false otherwise
     */
    protected void buttonClick(boolean liked) {
        // add displayed User to viewed/liked list
        recPresenter.updateLists(liked);
        if (liked) {
            recPresenter.useMatchCreator(); // if we liked the displayed user, we call upon
            // the match creator to check if a match can be created
        }
        // displays next user
        recPresenter.nextUser();
        recPresenter.displayUser();
    }

    /**
     * Set the information on screen to the displayedUser's information
     */
    @Override
    public void showUser() {
        ProfileImagePresenter presenter = new ProfileImagePresenter(this);
        presenter.downloadBitmapToImageView(
                new String[]{displayedUser.getUid(), "img", "_profile_img.jpg"},
                ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_account_circle_24)
        );
        name.setText(displayedUser.getName());
        age.setText(Integer.toString(displayedUser.getAge()));
        gender.setText(displayedUser.getGender());
        info.setText(displayedUser.getUserInfoString());
    }

    /**
     * Displays a screen that tells currentUsers that there are no more compatible users.
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

    @Override
    public void setProfileImage(Bitmap bm) {
        this.profilePicture.setImageBitmap(bm);
    }

    @Override
    public void setProfileImage(Drawable drawable) {
        this.profilePicture.setImageDrawable(drawable);
    }
}
