package com.group80.uoftinder.logout;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Signs out the current user
 */
public class LogOutInteractor {

    /**
     * Signs out the user
     */
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }
}