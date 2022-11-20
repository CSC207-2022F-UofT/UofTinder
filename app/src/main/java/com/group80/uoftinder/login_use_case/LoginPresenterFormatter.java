package com.group80.uoftinder.login_use_case;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.group80.uoftinder.HelloWorld;
import com.group80.uoftinder.Login;

public class LoginPresenterFormatter extends AppCompatActivity implements LoginPresenter {

    final Login login;
    final Class<HelloWorld> helloWorld;

    public LoginPresenterFormatter(Login login, Class<HelloWorld> helloWorldClass) {
        this.login = login;
        this.helloWorld = helloWorldClass;
    }

    @Override
    public void prepareSuccessView(FirebaseUser user) {
        Log.d(TAG, "signInWithEmail:success");
        Toast.makeText(login, "Login Successful!", Toast.LENGTH_SHORT).show();
        updateUI(user);
    }

    @Override
    public void prepareFailureViewLogin(String error) {
        Toast.makeText(login, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void prepareFailureView(EditText text, String error) {
        text.setError(error);
        text.requestFocus();
    }

    private void updateUI(FirebaseUser user) {
        // after logging in, the user should be brought to the Recommendation View
        // for testing purposes, we switch to the HelloWorld View

        // TODO
    }
}
