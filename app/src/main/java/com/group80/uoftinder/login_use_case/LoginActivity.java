/**
 * Logs in a user so they can use the app
 */

package com.group80.uoftinder.login_use_case;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group80.uoftinder.Constants;
import com.group80.uoftinder.R;
import com.group80.uoftinder.create_account_use_case.CreateAccountActivity;
import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.feed.RecommendationView;
import com.group80.uoftinder.firebase.realtime.UserRealtimeDbFacade;

// Frameworks & Drivers Layer

public class LoginActivity extends AppCompatActivity implements LoginViewInterface {

    EditText loginEmail;
    EditText loginPassword;
    Button enterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        User user = new User("CSC207_Group80");
        user.setUserType("Administrator");
        UserRealtimeDbFacade.uploadUser(user);

        // UserAccountController
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);

        LoginPresenterInterface loginPresenter = new LoginPresenter(RecommendationView.class, LoginActivity.this);
        LoginInput loginInteractor = new LoginInteractor(loginPresenter);
        LoginController loginController = new LoginController(loginInteractor);

        enterLogin = findViewById(R.id.EnterLogin);
        enterLogin.setOnClickListener(view -> loginController.loginUser(loginEmail, loginPassword));
    }

    /**
     * Update the UI to the logged in user's recommendation view
     * and passes the current user to the next class
     *
     * @param currentUser current signed in User
     */
    @Override
    public void updateUI(User currentUser) {
        Intent intent = new Intent(LoginActivity.this, RecommendationView.class);
        intent.putExtra(Constants.CURRENT_USER_STRING, currentUser);
        startActivity(intent);
        finish();
    }

    /**
     * Toast a message that will pop up when a user attempts to sign in
     *
     * @param message display message
     */
    @Override
    public void showMessageToast(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Pop up if email input is null when signing in
     *
     * @param error error message
     */
    @Override
    public void showEmailMessage(String error) {
        loginEmail.setError(error);
        loginEmail.requestFocus();
    }

    /**
     * Pop up if password input is null when signing in
     *
     * @param error error message
     */
    @Override
    public void showPasswordMessage(String error) {
        loginPassword.setError(error);
        loginPassword.requestFocus();
    }

    /**
     * Switches UI to create account when the create account button is clicked
     *
     * @param view current view
     */
    public void showCreateAccountView(View view) {
        Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
        startActivity(intent);
        finish();
    }

}