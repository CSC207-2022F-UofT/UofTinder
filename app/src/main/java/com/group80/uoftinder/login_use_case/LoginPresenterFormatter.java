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

    /**
     * Pop-ups a message indicating login was successful and changes view to recommendation view
     * @param user current FirebaseUser
     */
    @Override
    public void prepareSuccessView(FirebaseUser user) {
        Log.d(TAG, "signInWithEmail:success");
        Toast.makeText(login, "Login Successful!", Toast.LENGTH_SHORT).show();
        updateUI(user);
    }

    /**
     * If email and password combination do not match, pop-ups a message indicating login failure
     * @param error error message
     */
    @Override
    public void prepareFailureViewLogin(String error) {
        Toast.makeText(login, error, Toast.LENGTH_SHORT).show();
    }

    /**
     * If there is a missing input, sets an error to text and also requests focus, shows error as error message
     * @param text EditText of where error should show
     * @param error error message
     */
    @Override
    public void prepareFailureView(EditText text, String error) {
        text.setError(error);
        text.requestFocus();
    }

    /**
     * Update the UI to the logged in user's recommendation view.
     * @param firebaseUser current FirebaseUser
     */
    private void updateUI(FirebaseUser firebaseUser) {
        // TODO: implement this method so that after logging in, the user is brought to their recommendation feed
        // User loggedInUser = ____.getUserById(firebaseUser.getUid())
    }
}
