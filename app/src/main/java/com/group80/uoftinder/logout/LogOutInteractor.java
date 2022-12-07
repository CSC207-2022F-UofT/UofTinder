package com.group80.uoftinder.logout;

import com.google.firebase.auth.FirebaseAuth;

public class LogOutInteractor {

    /**
     * Signs out the user
     */
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }
}
