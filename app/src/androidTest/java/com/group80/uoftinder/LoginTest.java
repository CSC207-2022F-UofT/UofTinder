package com.group80.uoftinder;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.util.Log;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.group80.uoftinder.login_use_case.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {
    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<LoginActivity>(LoginActivity.class);


    @Test
    public void testTypeInEmail() {
        String expectedEmail = "test@mail.utoronto.ca";
        String expectedPassword = "12345678";

        onView(withId(R.id.loginEmail)).perform(replaceText(expectedEmail), closeSoftKeyboard());
        onView(withId(R.id.loginPassword)).perform(replaceText(expectedPassword), closeSoftKeyboard());
        onView(withId(R.id.EnterLogin)).perform(click());


        // Check that the text was changed.
//        onView(withId(R.id.textToBeChanged)).check(matches(withText(STRING_TO_BE_TYPED)));
//        onView(withText("Password:")).check(matches(isDisplayed()));
    }

//    @Test
//    public void testChangeToCreateAccount() {
//        onView(withId(R.id.createAccountViewCreateAccountButton)).perform(click());
//        onView(withText("Re-enter Password:")).check(matches(isDisplayed()));
//
//    }
}
