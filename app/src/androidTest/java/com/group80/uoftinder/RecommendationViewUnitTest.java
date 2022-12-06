package com.group80.uoftinder;

import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.RootMatchers;

import com.group80.uoftinder.feed.RecommendationView;
import com.group80.uoftinder.login_use_case.LoginActivity;
import com.group80.uoftinder.login_use_case.LoginInteractor;
import com.group80.uoftinder.login_use_case.LoginPresenter;

import org.junit.Test;

public class RecommendationViewUnitTest extends LoginActivity {

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//
//        likeUserTest();
//    }

    @Test
    public void likeUserTest() {
        LoginActivity loginActivity = new LoginActivity();
        LoginPresenter loginPresenter = new LoginPresenter(RecommendationView.class, loginActivity);
        LoginInteractor loginInteractor = new LoginInteractor(loginPresenter);

        Thread thread = new Thread();

        Intent intent = new Intent(new LoginActivity(), RecommendationView.class);
        Looper.prepare();
        startActivity(intent);



        String email = "test@mail.utoronto.ca";
        String password = "12345678";

        Espresso.onView(withId(R.id.loginEmail)).perform(typeText(email));
        Espresso.onView(withId(R.id.loginPassword)).perform(typeText(password));
        Espresso.onView(withId(R.id.EnterLogin)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.noButton)).perform(ViewActions.click());
//        Espresso.onView(ViewMatchers.withText("a")).inRoot(new ToastMatcher()).check(matches(withText("Test")));
    }
}
