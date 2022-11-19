package com.group80.uoftinder.login_use_case;

import android.text.TextUtils;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.group80.uoftinder.Login;

import java.util.concurrent.Executor;

public class LoginInteractor implements LoginInput{

    final LoginPresenter loginPresenter;
    Login login;

    public LoginInteractor(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    public void loginUser(FirebaseAuth mAuth, EditText loginEmail, EditText loginPassword) {
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        /* To log-in, use the following
         * mAuth.signInWithEmailAndPassword("demo@email.com", "password");
         * To get user uid, after you have signed in, do the following
         * mAuth.getCurrentUser().getUid();
         */
        if (TextUtils.isEmpty(email)) { // no email input, user == null
            loginEmail.setError("Email is required!");
            loginEmail.requestFocus();
        }
        else if (TextUtils.isEmpty(password)){ // no password input, user == null
            loginPassword.setError("Password is required!");
            loginPassword.requestFocus();
        }

        else { // user == null, signing in with email and password
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // LoginInteractor checks

                            // Login Presenter?
                            if (task.isSuccessful()) {
                                loginPresenter.prepareSuccessView(mAuth.getCurrentUser());

                            } else {
                                // If sign in fails, display a message to the user.
                                loginPresenter.prepareFailureView("Login failure :(");
                            }
                        }
                    });
        }
    }
}
