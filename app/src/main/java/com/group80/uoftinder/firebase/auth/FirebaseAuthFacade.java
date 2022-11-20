package com.group80.uoftinder.firebase.auth;

public class FirebaseAuthFacade {
    /**
     * Checks if the current user has logged in
     *
     * @return true if the current user jas logged in
     */
    static boolean isLoggedIn() {
        return ucCurrentUserStatusChecker.isLoggedIn();
    }

    /**
     * Gets the uid of the current logged in user, or `null` of the user has not logged in
     *
     * @return the uid of the current user, or `null` if the user has not logged in
     */
    static String getCurrentUserUid() {
        return ucCurrentUserStatusChecker.getCurrentUserUid();
    }

    /**
     * Determines if there is already an account associated with the provided email.
     *
     * @param email the email to be checked
     * @return true if there is already an account associated with the provided email
     */
    static boolean isEmailUserExists(String email) {
        return ucCloudAccountStatusChecker.isEmailUserExists(email);
    }
}
