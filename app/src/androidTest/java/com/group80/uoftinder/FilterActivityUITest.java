package com.group80.uoftinder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;

import android.util.Log;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.google.firebase.auth.FirebaseAuth;
import com.group80.uoftinder.login_use_case.LoginActivity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * A FilterActivityUITest that tests the activity_academic_filter view.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class FilterActivityUITest {
    private static String inputEmail;
    private static String inputPassword;
    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule = new ActivityScenarioRule<>(LoginActivity.class);

    /**
     * Initialize the expectedEmail and expectedPassword
     */
    @BeforeClass
    public static void setUp() {
        inputEmail = "csc207.group80.uoftinder.bot@gmail.com";
        inputPassword = "12345678";
    }

    /**
     * Logs that the FilterActivityUITest is over.
     */
    @AfterClass
    public static void tearDown() {
        Log.i("FilterViewUnitTests", "Done test");
    }

    @Before
    public void loginUser() {
        onView(withId(R.id.loginEmail)).perform(replaceText(inputEmail), closeSoftKeyboard());
        onView(withId(R.id.loginPassword)).perform(replaceText(inputPassword), closeSoftKeyboard());
        onView(withId(R.id.EnterLogin)).perform(click());
    }

    /**
     * Test that the program switches to the filter page after clicking on the 'Edit Filters'
     * button.
     */
    @Test
    public void testSwitchToFilterPage() {
        onView(withId(R.id.filterButton)).perform(click());
        onView(withId(R.id.resetButton)).check(matches(isDisplayed()));
    }

    /**
     * Test that the program switches to the recommendation view after clicking the 'reset' button.
     */
    @Test
    public void testResetChangeToRecommendation() {
        onView(withId(R.id.filterButton)).perform(click());
        onView(withId(R.id.resetButton)).perform(click());
        onView(withId(R.id.logoutButton)).check(matches(isDisplayed()));
    }

    /**
     * Test that the program 'reset' button properly deselects all options.
     */
    @Test
    public void testResetFilters() {
        onView(withId(R.id.filterButton)).perform(click());

        onView(withId(R.id.computerScienceBox)).perform(click());
        onView(withId(R.id.mathematicalPhysicalSciencesBox)).perform(click());
        onView(withId(R.id.secondYearBox)).perform(click());
        onView(withId(R.id.mississaugaBox)).perform(click());

        onView(withId(R.id.resetButton)).perform(click());
        onView(withId(R.id.filterButton)).perform(click());

        onView(withId(R.id.computerScienceBox)).check(matches(not(isChecked())));
        onView(withId(R.id.mathematicalPhysicalSciencesBox)).check(matches(not(isChecked())));
        onView(withId(R.id.lifeSciencesBox)).check(matches(not(isChecked())));
        onView(withId(R.id.socialSciencesBox)).check(matches(not(isChecked())));
        onView(withId(R.id.rotmanBox)).check(matches(not(isChecked())));
        onView(withId(R.id.otherStudyBox)).check(matches(not(isChecked())));

        onView(withId(R.id.firstYearBox)).check(matches(not(isChecked())));
        onView(withId(R.id.secondYearBox)).check(matches(not(isChecked())));
        onView(withId(R.id.thirdYearBox)).check(matches(not(isChecked())));
        onView(withId(R.id.fourthYearBox)).check(matches(not(isChecked())));
        onView(withId(R.id.graduateBox)).check(matches(not(isChecked())));

        onView(withId(R.id.stGeorgeBox)).check(matches(not(isChecked())));
        onView(withId(R.id.mississaugaBox)).check(matches(not(isChecked())));
        onView(withId(R.id.scarboroughBox)).check(matches(not(isChecked())));
    }

    /**
     * Test that the all program's filter check boxes can be selected.
     */
    @Test
    public void testFilterButtons() {
        onView(withId(R.id.filterButton)).perform(click());

        onView(withId(R.id.computerScienceBox)).perform(click());
        onView(withId(R.id.mathematicalPhysicalSciencesBox)).perform(click());
        onView(withId(R.id.lifeSciencesBox)).perform(click());
        onView(withId(R.id.socialSciencesBox)).perform(click());
        onView(withId(R.id.rotmanBox)).perform(click());
        onView(withId(R.id.otherStudyBox)).perform(click());

        onView(withId(R.id.firstYearBox)).perform(click());
        onView(withId(R.id.secondYearBox)).perform(click());
        onView(withId(R.id.thirdYearBox)).perform(click());
        onView(withId(R.id.fourthYearBox)).perform(click());
        onView(withId(R.id.graduateBox)).perform(click());

        onView(withId(R.id.stGeorgeBox)).perform(click());
        onView(withId(R.id.mississaugaBox)).perform(click());
        onView(withId(R.id.scarboroughBox)).perform(click());

        onView(withId(R.id.computerScienceBox)).check(matches(isChecked()));
        onView(withId(R.id.mathematicalPhysicalSciencesBox)).check(matches(isChecked()));
        onView(withId(R.id.lifeSciencesBox)).check(matches(isChecked()));
        onView(withId(R.id.socialSciencesBox)).check(matches(isChecked()));
        onView(withId(R.id.rotmanBox)).check(matches(isChecked()));
        onView(withId(R.id.otherStudyBox)).check(matches(isChecked()));

        onView(withId(R.id.firstYearBox)).check(matches(isChecked()));
        onView(withId(R.id.secondYearBox)).check(matches(isChecked()));
        onView(withId(R.id.thirdYearBox)).check(matches(isChecked()));
        onView(withId(R.id.fourthYearBox)).check(matches(isChecked()));
        onView(withId(R.id.graduateBox)).check(matches(isChecked()));

        onView(withId(R.id.stGeorgeBox)).check(matches(isChecked()));
        onView(withId(R.id.mississaugaBox)).check(matches(isChecked()));
        onView(withId(R.id.scarboroughBox)).check(matches(isChecked()));
    }

    /**
     * Test that the program switches to the recommendation view after clicking the 'Filter' button
     * on the filter page.
     */
    @Test
    public void testApplyFiltersChangeView() {
        onView(withId(R.id.filterButton)).perform(click());

        onView(withId(R.id.computerScienceBox)).perform(click());

        onView(withId(R.id.filterButton)).perform(click());
        onView(withId(R.id.logoutButton)).check(matches(isDisplayed()));
    }

    /**
     * Test that the keeps the selected filters after clicking the 'Filter' button and switching to
     * Recommendation view page.
     */
    @Test
    public void testApplyFiltersStay() {
        onView(withId(R.id.filterButton)).perform(click());

        onView(withId(R.id.lifeSciencesBox)).perform(click());
        onView(withId(R.id.otherStudyBox)).perform(click());
        onView(withId(R.id.thirdYearBox)).perform(click());
        onView(withId(R.id.scarboroughBox)).perform(click());

        onView(withId(R.id.filterButton)).perform(click());
        onView(withId(R.id.filterButton)).perform(click());

        onView(withId(R.id.lifeSciencesBox)).check(matches(isChecked()));
        onView(withId(R.id.otherStudyBox)).check(matches(isChecked()));
        onView(withId(R.id.thirdYearBox)).check(matches(isChecked()));
        onView(withId(R.id.scarboroughBox)).check(matches(isChecked()));
    }

    /**
     * Test that the program's filter check boxes can be deselected.
     */
    @Test
    public void testDeselect() {
        onView(withId(R.id.filterButton)).perform(click());

        onView(withId(R.id.computerScienceBox)).perform(click());
        onView(withId(R.id.computerScienceBox)).perform(click());

        onView(withId(R.id.computerScienceBox)).check(matches(not(isChecked())));
    }

    /**
     * Test that the program's filter check boxes can be deselected and other selected boxes will
     * not be affected.
     */
    @Test
    public void testTwoSelectOneDeselect() {
        onView(withId(R.id.filterButton)).perform(click());

        onView(withId(R.id.computerScienceBox)).perform(click());
        onView(withId(R.id.firstYearBox)).perform(click());
        onView(withId(R.id.stGeorgeBox)).perform(click());

        onView(withId(R.id.computerScienceBox)).perform(click());

        onView(withId(R.id.computerScienceBox)).check(matches(not(isChecked())));
        onView(withId(R.id.firstYearBox)).check(matches(isChecked()));
        onView(withId(R.id.stGeorgeBox)).check(matches(isChecked()));
    }

    @After
    public void logoutUser() {
        FirebaseAuth.getInstance().signOut();
    }
}