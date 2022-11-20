package com.group80.uoftinder.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;

public class ucCurrentUserStatusChecker {
    /**
     * Checks if the current user has logged in
     *
     * @return true if the current user jas logged in
     */
    static boolean isLoggedIn() {
        return FirebaseAuth.getInstance().getCurrentUser() == null;
    }

    /**
     * Gets the uid of the current logged in user, or `null` of the user has not logged in
     *
     * @return the uid of the current user, or `null` if the user has not logged in
     */
    static String getCurrentUserUid() {
        if (isLoggedIn())
            return FirebaseAuth.getInstance().getCurrentUser().getUid();
        return null;
    }

}
