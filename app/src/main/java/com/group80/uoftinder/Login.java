package com.group80.uoftinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.feed.RecommendationView;
import com.group80.uoftinder.firebase.realtime.UserRealtimeDbFacade;
import com.group80.uoftinder.login_use_case.LoginController;
import com.group80.uoftinder.login_use_case.LoginInput;
import com.group80.uoftinder.login_use_case.LoginInteractor;
import com.group80.uoftinder.login_use_case.LoginPresenter;
import com.group80.uoftinder.login_use_case.LoginPresenterFormatter;
import com.group80.uoftinder.login_use_case.LoginViewInterface;


public class Login extends AppCompatActivity implements LoginViewInterface {

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

        LoginPresenter loginPresenter = new LoginPresenterFormatter(RecommendationView.class, Login.this);
        LoginInput loginInteractor = new LoginInteractor(loginPresenter);
        LoginController loginController = new LoginController(loginInteractor);


        enterLogin = findViewById(R.id.EnterLogin);
        enterLogin.setOnClickListener(view -> loginController.loginUser(loginEmail, loginPassword));

        // testing
        Button button = findViewById(R.id.helloWorldEnterChatButton);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this, ChatActivity.class);
            // TODO: remove such dependency
            intent.putExtra("name", "Bot");
            intent.putExtra("contactUid", "FJuPu9PeQ8TpTPZmDXOVluUCp7c2");
            startActivity(intent);
        });
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
        Intent intent = new Intent(Login.this, RecommendationView.class);
        intent.putExtra("currentUser", currentUser);
        startActivity(intent);
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
        // CreateAccountView will be fine when merged with create account branch
        Intent intent = new Intent(Login.this, CreateAccountView.class);
        startActivity(intent);
        finish();
    }

}