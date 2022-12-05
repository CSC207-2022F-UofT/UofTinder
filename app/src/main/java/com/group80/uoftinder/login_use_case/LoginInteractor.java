/**
 * Interactor that will check if a correct email and password combination is input to login a user
 * Depending on result, either error or success will be shown
 */

package com.group80.uoftinder.login_use_case;

// Use case layer

import android.text.TextUtils;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.firebase.realtime.UserRealtimeDbFacade;

import java.util.concurrent.ExecutionException;

public class LoginInteractor extends AppCompatActivity implements LoginInput {

    final LoginPresenterInterface loginPresenter;
    private User currentUser;

    public LoginInteractor(LoginPresenterInterface loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    /**
     * Uses loginEmail and loginPassword ot attempt to login a user.
     * Error will appear if there is no email input, if there is no password input,
     * or if incorrect email and password combination is input
     *
     * @param email    login email input
     * @param password login password input
     */
    public void loginUser(String email, String password) {
        if (TextUtils.isEmpty(email)) { // no email input, user == null
            loginPresenter.prepareEmailFailureView("Email is required!");
        } else if (TextUtils.isEmpty(password)) { // no password input, user == null
            loginPresenter.preparePasswordFailureView("Password is required!");
        } else { // user == null, signing in with email and password
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

                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                assert firebaseUser != null; // ensuring user logged in properly

                // getting the corresponding User from the RealtimeDatabase based off the uid
                String id = firebaseUser.getUid();
                UserRealtimeDbFacade.getUser("Academic", id, this::setCurrentUser);
                if (getCurrentUser() == null) {
                    UserRealtimeDbFacade.getUser("Romantic", id, this::setCurrentUser);
                    if (getCurrentUser() == null) {
                        UserRealtimeDbFacade.getUser("Friendship", id, this::setCurrentUser);
                    }
                }

                Log.e("TEST", this.currentUser == null ? "NULL" : "YESSSSS");
                // using the current user, change the UI
                loginPresenter.prepareSuccessView("Login Successful!", currentUser);
            } else {
                // If sign in fails, display a message to the user.
                loginPresenter.prepareLoginFailureView("Login Unsuccessful :(");
            }
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
