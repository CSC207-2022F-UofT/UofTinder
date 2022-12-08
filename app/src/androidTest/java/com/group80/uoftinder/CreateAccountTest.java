/**
 * Testing various aspects of the view of CreateAccountActivity.java
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

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group80.uoftinder.create_account_use_case.CreateAccountActivity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreateAccountTest {
    private static String inputEmail;
    private static String inputPassword;

    @Rule
    public ActivityScenarioRule<CreateAccountActivity> activityRule =
            new ActivityScenarioRule<>(CreateAccountActivity.class);

    @BeforeClass
    public static void setUp() {
        inputEmail = "random@gmail.com";
        inputPassword = "000000";
    }

    @AfterClass
    public static void tearDown() {
        Log.i("CreateAccountActivityTest", "Done test");
    }

    /**
     * Checks if view switched activity_login.xml correctly
     */
    @Test
    public void testChangeToLogin() {
        onView(withId(R.id.createAccountViewRetLoginButton)).perform(click());
        onView(withId(R.id.LoginText)).check(matches(isDisplayed()));
    }

    /**
     * Check if no email, no passwords are input that createActViewEmailEdtTxt has focus
     */
    @Test
    public void testNoEmailNoPassword1NoPassword2() {
        onView(withId(R.id.createActViewCreateActBtn)).perform(click());
        onView(withId(R.id.createActViewEmailEdtTxt)).check(matches(hasFocus()));
    }

    /**
     * Check if with email, no passwords are input that createActViewEmailEdtTxt has focus
     */
    @Test
    public void testWithEmailNoPassword1NoPassword2() {
        onView(withId(R.id.createActViewEmailEdtTxt)).perform(replaceText(inputEmail),
                closeSoftKeyboard());

        onView(withId(R.id.createActViewCreateActBtn)).perform(click());
        onView(withId(R.id.createActViewPasswordEdtTxt)).check(matches(hasFocus()));
    }

    /**
     * Check if with email, and only one password are input that createActViewReEnterPasswordEdtTxt
     * has focus
     */
    @Test
    public void testWithEmailWithPassword1NoPassword2() {
        onView(withId(R.id.createActViewEmailEdtTxt)).perform(replaceText(inputEmail),
                ViewActions.closeSoftKeyboard());
        onView(withId(R.id.createActViewPasswordEdtTxt)).perform(replaceText(inputPassword),
                closeSoftKeyboard());

        onView(withId(R.id.createActViewCreateActBtn)).perform(click());
        onView(withId(R.id.createActViewReEnterPasswordEdtTxt)).check(matches(hasFocus()));
    }

    /**
     * Check if with email and two passwords are input, but the passwords do not match,
     * createActViewReEnterPasswordEdtTxt has focus
     */
    @Test
    public void testWithEmailWithPassword1WithPassword2NotMatching() {
        onView(withId(R.id.createActViewEmailEdtTxt)).perform(replaceText(inputEmail),
                closeSoftKeyboard());
        onView(withId(R.id.createActViewPasswordEdtTxt)).perform(replaceText(inputPassword),
                closeSoftKeyboard());
        onView(withId(R.id.createActViewReEnterPasswordEdtTxt)).perform(replaceText(
                        "123456"),
                closeSoftKeyboard());

        onView(withId(R.id.createActViewCreateActBtn)).perform(click());

        onView(withId(R.id.createActViewReEnterPasswordEdtTxt)).check(matches(hasFocus()));
    }

    /**
     * Check if with email and two passwords are input, and the passwords do match but the user
     * already exists, no user is created
     */
    @Test
    public void testWithExistingEmailWithPassword1WithPassword2Matching() {
        onView(withId(R.id.createActViewEmailEdtTxt)).perform(replaceText(
                        "csc207.group80.uoftinder.bot@gmail.com"),
                closeSoftKeyboard());
        onView(withId(R.id.createActViewPasswordEdtTxt)).perform(replaceText(
                        "12345678"),
                closeSoftKeyboard());
        onView(withId(R.id.createActViewReEnterPasswordEdtTxt)).perform(replaceText(
                        "12345678"),
                closeSoftKeyboard());

        onView(withId(R.id.createActViewCreateActBtn)).perform(click());

        assertNull(FirebaseAuth.getInstance().getCurrentUser());
    }

    /**
     * Check if with email and two passwords are input, and the passwords do match, and the user
     * does not exist in the database, a user is created
     */
    @Test
    public void testSuccessfulWithEmailWithPassword1WithPassword2Matching() {
        onView(withId(R.id.createActViewEmailEdtTxt)).perform(replaceText(inputEmail),
                closeSoftKeyboard());
        onView(withId(R.id.createActViewPasswordEdtTxt)).perform(replaceText(inputPassword),
                closeSoftKeyboard());
        onView(withId(R.id.createActViewReEnterPasswordEdtTxt)).perform(replaceText(inputPassword),
                closeSoftKeyboard());

        onView(withId(R.id.createActViewCreateActBtn)).perform(click());

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertNotNull(FirebaseAuth.getInstance().getCurrentUser());
    }

    @After
    public void deleteUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.delete();
        }
    }

}
