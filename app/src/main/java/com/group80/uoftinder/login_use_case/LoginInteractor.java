package com.group80.uoftinder.login_use_case;

import android.text.TextUtils;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

public class LoginInteractor extends AppCompatActivity implements LoginInput{

    final LoginPresenter loginPresenter;

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
            loginPresenter.prepareFailureView(loginEmail, "Email is required!");
        }
        else if (TextUtils.isEmpty(password)){ // no password input, user == null
            loginPresenter.prepareFailureView(loginPassword, "Password is required!");
        }

        else { // user == null, signing in with email and password
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // LoginInteractor checks

                            // Login Presenter?
                            if (task.isSuccessful()) {
                                loginPresenter.prepareSuccessView(task, mAuth.getCurrentUser());

                            } else {
                                // If sign in fails, display a message to the user.
                                loginPresenter.prepareFailureViewLogin("Login Unsuccessful :(");
                            }
                        }
                    });
        }
    }
}
