package com.group80.uoftinder.login_use_case;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.group80.uoftinder.HelloWorld;

public class LoginPresenterFormatter extends AppCompatActivity implements LoginPresenter {

    final Class<HelloWorld> helloWorld;
    final LoginViewInterface loginViewInterface;

    public LoginPresenterFormatter(Class<HelloWorld> helloWorldClass, LoginViewInterface loginViewInterface) {
        this.helloWorld = helloWorldClass;
        this.loginViewInterface = loginViewInterface;
    }

    /**
     * Pop-ups a message indicating login was successful and changes view to recommendation view
     * @param firebaseUser current FirebaseUser
     */
    @Override
    public void prepareSuccessView(FirebaseUser firebaseUser) {
        Log.d(TAG, "signInWithEmail:success");
        Toast.makeText((Context) loginViewInterface, "Login Successful!", Toast.LENGTH_SHORT).show();
        loginViewInterface.updateUI(firebaseUser);
    }

    /**
     * If email and password combination do not match, pop-ups a message indicating login failure
     * @param error error message
     */
    @Override
    public void prepareFailureViewLogin(String error) {
        Toast.makeText((Context) loginViewInterface, error, Toast.LENGTH_SHORT).show();
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
}
