package com.group80.uoftinder.firebase.auth;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.concurrent.ExecutionException;

public class ucCloudAccountStatusChecker {
    /**
     * Determines if there is already an account associated with the provided email.
     *
     * @param email the email to be checked
     * @return true if there is already an account associated with the provided email
     */
    static boolean isEmailUserExists(String email) {
        if (email.equals("")) return false;

        /* TODO: there should be an easier way to do this. I will do more research later.
         *  -- Lance (Nov.20, 2022) */

        // Creates a task to check the email
        Task<SignInMethodQueryResult> signInMethodQueryResultTask = FirebaseAuth.getInstance().fetchSignInMethodsForEmail(email);

        // A Runnable to be run in another thread, used to waiting for task completion
        class ResultRunnable implements Runnable {
            private boolean result;

            @Override
            public void run() {
                try {
                    this.result = Tasks.await(signInMethodQueryResultTask).getSignInMethods().isEmpty();
                } catch (ExecutionException | InterruptedException e) {
                    // TODO: error handling?
                    this.result = false;
                }
            }

            /**
             * Get the result
             * @return
             */
            public boolean getResult() {
                return result;
            }
        }

        ResultRunnable resultRunnable = new ResultRunnable();
        Thread thread = new Thread(resultRunnable);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO: error handling?
            return false;
        }

        return resultRunnable.getResult();
    }
}
