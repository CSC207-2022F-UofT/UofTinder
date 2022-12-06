package com.group80.uoftinder.logout;

import com.google.firebase.auth.FirebaseAuth;

public class LogOutInteractor {
    final LogOutPresenterInterface logOutPresenter;

    public LogOutInteractor(LogOutPresenterInterface logOutPresenter) {
        this.logOutPresenter = logOutPresenter;
    }

    /**
     * Signs out the user, switches UI back to LoginActivity
     */
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        logOutPresenter.showLogin();
    }
}
