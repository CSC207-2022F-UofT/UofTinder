package com.group80.uoftinder.login_use_case;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.group80.uoftinder.CreateAccountView;
import com.group80.uoftinder.R;
import com.group80.uoftinder.entities.Constants;
import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.feed.RecommendationView;
import com.group80.uoftinder.firebase.realtime.UserRealtimeDbFacade;


public class LoginActivity extends AppCompatActivity implements LoginViewModel {

    EditText loginEmail;
    EditText loginPassword;
    Button enterLogin;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginview);

        // UserAccountController
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);

        LoginPresenter loginPresenter = new LoginPresenterFormatter(RecommendationView.class, LoginActivity.this);
        LoginInput loginInteractor = new LoginInteractor(loginPresenter);
        LoginController loginController = new LoginController(loginInteractor);


        enterLogin = findViewById(R.id.EnterLogin);
        enterLogin.setOnClickListener(view -> loginController.loginUser(loginEmail, loginPassword));

        // testing
//        Button button = findViewById(R.id.helloWorldEnterChatButton);
//        button.setOnClickListener(view -> {
//            Intent intent = new Intent(LoginActivity.this, ChatActivity.class);
//            // TODO: remove such dependency
//            intent.putExtra("name", "Bot");
//            intent.putExtra("contactUid", "FJuPu9PeQ8TpTPZmDXOVluUCp7c2");
//            startActivity(intent);
//        });
    }

    /**
     * Update the UI to the logged in user's recommendation view.
     * @param firebaseUser current FirebaseUser
     */
    @Override
    public void updateUI(FirebaseUser firebaseUser) {
        String id = firebaseUser.getUid();
        UserRealtimeDbFacade.getUser("Academic", id, this::setCurrentUser);
        if(getCurrentUser() == null) {
            UserRealtimeDbFacade.getUser("Romantic", id, this::setCurrentUser);
            if(getCurrentUser() == null) {
                UserRealtimeDbFacade.getUser("Friendship", id, this::setCurrentUser);
            }
        }
        Intent intent = new Intent(LoginActivity.this, RecommendationView.class);
        intent.putExtra(Constants.CURRENT_USER_STRING, currentUser);
        startActivity(intent);
    }

    @Override
    public void showMessageToast(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmailMessage(String error) {
        loginEmail.setError(error);
        loginEmail.requestFocus();
    }

    @Override
    public void showPasswordMessage(String error) {
        loginPassword.setError(error);
        loginPassword.requestFocus();
    }

    /**
     * Set user
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     * Return the current user
     * @return user
     */
    public User getCurrentUser() {
        return this.currentUser;
    }

    /**
     * Switches UI to create account when the create account button is clicked
     * @param view current view
     */
    public void showCreateAccountView(View view) {
        Intent intent = new Intent(LoginActivity.this, CreateAccountView.class);
        startActivity(intent);
        finish();
    }

}