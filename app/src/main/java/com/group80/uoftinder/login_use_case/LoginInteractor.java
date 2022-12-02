/**
 * Interactor that will check if a correct email and password combination is input to login a user
 * Depending on result, either error or success will be shown
 */

package com.group80.uoftinder.login_use_case;

import android.text.TextUtils;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.ExecutionException;

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
            Task<AuthResult> task = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password);
            Thread thread = new Thread(() -> {
                try {
                    Tasks.await(task);  // wait until sign in method finishes
                    Log.e("Login", "Done!");
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            });

            thread.start(); // start the thread

            try {
                thread.join(); // join this thread back to main thread
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // after finishing method, then we can execute the following code
            if (task.isSuccessful()) {
                loginPresenter.prepareSuccessView("Login Successful!", FirebaseAuth.getInstance().getCurrentUser());

            } else {
                // If sign in fails, display a message to the user.
                loginPresenter.prepareLoginFailureView("Login Unsuccessful :(");
            }
        }
    }
}
