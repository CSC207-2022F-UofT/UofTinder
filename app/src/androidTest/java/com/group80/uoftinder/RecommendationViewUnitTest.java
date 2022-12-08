package com.group80.uoftinder;

import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.Log;
import android.view.Display;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.group80.uoftinder.feed.RecommendationView;
import com.group80.uoftinder.login_use_case.LoginActivity;
import com.group80.uoftinder.login_use_case.LoginInteractor;
import com.group80.uoftinder.login_use_case.LoginPresenter;

import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class RecommendationViewUnitTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> rule = new ActivityScenarioRule<LoginActivity>(LoginActivity.class);

    @Test
    public void randomTest() {
        ActivityScenario<LoginActivity> scenario = rule.getScenario();
        String email = "test@mail.utoronto.ca";
        String password = "12345678";

        Espresso.onView(withId(R.id.loginEmail)).perform(typeText(email));
        Espresso.onView(withId(R.id.loginPassword)).perform(typeText(password));
        Espresso.onView(withId(R.id.EnterLogin)).perform(ViewActions.click());
    }
}
//    @Rule
//    public UiThread uiThreadTestRule = new UiThreadTestRule();

//    @Test
//    public void likeUserTest() {
//        runOnUiThread(() -> {
////        Intent intent = new Intent(new LoginActivity(), RecommendationView.class);
//        Intent intent = new Intent(RecommendationViewUnitTest.this, LoginActivity.class);
////        launchActivity<LoginActivity>(intent);
////            LoginActivity loginActivity = new LoginActivity();
////            LoginPresenter loginPresenter = new LoginPresenter(RecommendationView.class, loginActivity);
////            LoginInteractor loginInteractor = new LoginInteractor(loginPresenter);
//
//            Looper.prepare();
//            startActivity(intent);
//
//            String email = "test@mail.utoronto.ca";
//            String password = "12345678";
//
//            Espresso.onView(withId(R.id.loginEmail)).perform(typeText(email));
//            Espresso.onView(withId(R.id.loginPassword)).perform(typeText(password));
//            Espresso.onView(withId(R.id.EnterLogin)).perform(ViewActions.click());
//
//            Espresso.onView(withId(R.id.noButton)).perform(ViewActions.click());
////        Espresso.onView(ViewMatchers.withText("a")).inRoot(new ToastMatcher()).check(matches(withText("Test")));
//        });
