package com.group80.uoftinder;
import static org.junit.Assert.assertEquals;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.feed.RecommendationPresenter;
import com.group80.uoftinder.feed.RecommendationView;

public class RecommendationViewTest extends AppCompatActivity {
    // create current User
    User current = new User("current");
    // create test Users to display to current User
    User test1 = new User("test1");
    User test2 = new User("test2");
    User test3 = new User("test3");
    // create a list of compatibleUsers
    List compatibleUserList = new ArrayList<User>();

    @Test
    public void goesToNextUser() {

        // set the test Users' names
        test1.setName("testName1");
        test2.setName("testName2");
        test3.setName("testName3");
        // add the test Users
        compatibleUserList.add(test1);
        compatibleUserList.add(test2);
        compatibleUserList.add(test3);
        // create an instance of RecommendationView
        RecommendationView recView = new RecommendationView();
        // create an instance of RecommendationPresenter
        RecommendationPresenter recPresenter = new RecommendationPresenter(new User(), recView);
        // set the displayedUser to the first test User
        User displayedUser = test1;
        //display on screen
        recView.showUser();
        // create yes and no buttons
        Button noButton = findViewById(R.id.noButton);
        Button yesButton = findViewById(R.id.yesButton);

        // currentUser likes displayedUser (yesButton is clicked)
        recView.buttonClick(displayedUser, true);





//      assertEquals(nextDisplayedUser, test2);
    }
    @Test
    public void goesToNoNewRecommendationScreen() {
        User displayedUser = test3;
//        noNewRecommendations

    }



}
