package com.group80.uoftinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group80.uoftinder.feed.RecommendationView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginview);

        FirebaseAuth mAuth = FirebaseAuth.getInstance(); // initialize the Firebase Auth

        // UserAccountController
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);

        LoginPresenter loginPresenter = new LoginPresenterFormatter(RecommendationView.class, Login.this);
        LoginInput loginInteractor = new LoginInteractor(loginPresenter);
        LoginController loginController = new LoginController(loginInteractor);


        enterLogin = findViewById(R.id.EnterLogin);
        enterLogin.setOnClickListener(view -> loginController.loginUser(loginEmail, loginPassword));
    }

    /**
     * Update the UI to the logged in user's recommendation view.
     * @param firebaseUser current FirebaseUser
     */
    @Override
    public void updateUI(FirebaseUser firebaseUser) {
        startActivity(new Intent(Login.this, RecommendationView.class));
    }

    /**
     * Switches UI to create account when the create account button is clicked
     */
    public void showCreateAccountView(View view) {
        // CreateAccountView will be fine when merged with create account branch
        Intent intent = new Intent(Login.this, CreateAccountView.class);
        startActivity(intent);
        finish();
    }

}