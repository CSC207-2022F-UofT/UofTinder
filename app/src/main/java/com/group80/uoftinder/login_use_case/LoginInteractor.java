package com.group80.uoftinder.login_use_case;

import android.text.TextUtils;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginInteractor extends AppCompatActivity implements LoginInput{

    final LoginPresenter loginPresenter;

    public LoginInteractor(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    public void loginUser(EditText loginEmail, EditText loginPassword) {
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) { // no email input, user == null
            loginPresenter.prepareFailureView(loginEmail, "Email is required!");
        }
        else if (TextUtils.isEmpty(password)){ // no password input, user == null
            loginPresenter.prepareFailureView(loginPassword, "Password is required!");
        }

        else { // user == null, signing in with email and password
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {

                        // Login Presenter
                        if (task.isSuccessful()) {
                            loginPresenter.prepareSuccessView(FirebaseAuth.getInstance().getCurrentUser());

                        } else {
                            // If sign in fails, display a message to the user.
                            loginPresenter.prepareFailureViewLogin("Login Unsuccessful :(");
                        }
                    });
        }
    }
}
