package com.group80.uoftinder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNull;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.google.firebase.auth.FirebaseAuth;
import com.group80.uoftinder.login_use_case.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LogoutTest {
    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    /**
     * Test that the logout button works in Recommendation View
     */
    @Test
    public void logoutTest() {
        //log in using bot email
        String inputEmail = "csc207.group80.uoftinder.bot@gmail.com";
        String inputPassword = "12345678";
        // simulate typing in email, password and press login
        onView(withId(R.id.loginEmail)).perform(replaceText(inputEmail), closeSoftKeyboard());
        onView(withId(R.id.loginPassword)).perform(replaceText(inputPassword), closeSoftKeyboard());
        onView(withId(R.id.EnterLogin)).perform(click());
        // Navigated to RecommendationView

        // simulate clicking the logout button
        onView(withId(R.id.logoutButton)).perform(click());
        // test that the current user is now null after logout
        assertNull(FirebaseAuth.getInstance().getCurrentUser());
    }
}
