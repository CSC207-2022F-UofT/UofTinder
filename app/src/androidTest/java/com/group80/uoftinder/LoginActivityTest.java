/**
 * Testing various aspects of the view of LoginActivity.java
 */

package com.group80.uoftinder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasFocus;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.util.Log;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.google.firebase.auth.FirebaseAuth;
import com.group80.uoftinder.login_use_case.LoginActivity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {
    private static String inputEmail;
    private static String inputPassword;
    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @BeforeClass
    public static void setUp() {
        inputEmail = "csc207.group80.uoftinder.bot@gmail.com";
        inputPassword = "12345678";
    }

    /**
     * Logs that the LoginActivityTest is over.
     */
    @AfterClass
    public static void tearDown() {
        Log.i("LoginActivityTests", "Done test");
    }

    /**
     * Checks if view switched activity_create_account.xml correctly
     */
    @Test
    public void testChangeToCreateAccount() {
        onView(withId(R.id.accountButton)).perform(click());
        onView(withId(R.id.CreateAccountTitle)).check(matches(isDisplayed()));
    }

    /**
     * Check if no email, no password is input that loginEmail has focus
     */
    @Test
    public void testNoEmailNoPassword() {
        onView(withId(R.id.EnterLogin)).perform(click());

        onView(withId(R.id.loginEmail)).check(matches(hasFocus()));
    }

    /**
     * Check if no email, with password is input that loginEmail has focus
     */
    @Test
    public void testNoEmailWithPassword() {
        onView(withId(R.id.loginPassword)).perform(replaceText(inputPassword), closeSoftKeyboard());
        onView(withId(R.id.EnterLogin)).perform(click());

        onView(withId(R.id.loginEmail)).check(matches(hasFocus()));
    }

    /**
     * Check if with email, no password is input that loginPassword has focus
     */
    @Test
    public void testWithEmailNoPassword() {
        onView(withId(R.id.loginEmail)).perform(replaceText(inputEmail), closeSoftKeyboard());
        onView(withId(R.id.EnterLogin)).perform(click());

        onView(withId(R.id.loginPassword)).check(matches(hasFocus()));
    }

    /**
     * Check if with incorrect email and password combination that current user is null
     */
    @Test
    public void testIncorrectEmailPasswordLogin() {
        onView(withId(R.id.loginEmail)).perform(replaceText("hellothere"), closeSoftKeyboard());
        onView(withId(R.id.loginPassword)).perform(replaceText("12345678"), closeSoftKeyboard());
        onView(withId(R.id.EnterLogin)).perform(click());

        assertNull(FirebaseAuth.getInstance().getCurrentUser());
    }

    /**
     * Check if with correct email and password combination that current user is not null
     */
    @Test
    public void testCorrectEmailPasswordLogin() {
        onView(withId(R.id.loginEmail)).perform(replaceText(inputEmail), closeSoftKeyboard());
        onView(withId(R.id.loginPassword)).perform(replaceText(inputPassword), closeSoftKeyboard());
        onView(withId(R.id.EnterLogin)).perform(click());

        assertNotNull(FirebaseAuth.getInstance().getCurrentUser());
    }

    @After
    public void afterTestCorrectEmailPasswordLogin() {
        FirebaseAuth.getInstance().signOut();
    }

}
