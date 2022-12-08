package com.group80.uoftinder.use_case_layer.logout;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Signs out the current user
 */
public class LogoutInteractor {

    /**
     * Signs out the user
     */
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }
}