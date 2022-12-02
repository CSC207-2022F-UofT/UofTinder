/**
 * Interactor that will check if a correct email and password combination is input to login a user
 * Depending on result, either error or success will be shown
 */

package com.group80.uoftinder.login_use_case;

import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginInteractor extends AppCompatActivity implements LoginInput{

    final LoginPresenter loginPresenter;

    public LoginInteractor(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    /**
     * Uses loginEmail and loginPassword ot attempt to login a user.
     * Error will appear if there is no email input, if there is no password input,
     * or if incorrect email and password combination is input
     *
     * @param email login email input
     * @param password login password input
     */
    public void loginUser(String email, String password) {
        if (TextUtils.isEmpty(email)) { // no email input, user == null
            loginPresenter.prepareEmailFailureView("Email is required!");
        }
        else if (TextUtils.isEmpty(password)){ // no password input, user == null
            loginPresenter.preparePasswordFailureView("Password is required!");
        }

        else { // user == null, signing in with email and password
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {

                        // Login Presenter
                        if (task.isSuccessful()) {
                            loginPresenter.prepareSuccessView("Login Successful!", FirebaseAuth.getInstance().getCurrentUser());

                        } else {
                            // If sign in fails, display a message to the user.
                            loginPresenter.prepareLoginFailureView("Login Unsuccessful :(");
                        }
                    });
        }
    }
}
