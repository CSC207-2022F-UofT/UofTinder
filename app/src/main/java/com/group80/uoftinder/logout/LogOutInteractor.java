package com.group80.uoftinder.logout;

import com.google.firebase.auth.FirebaseAuth;

public class LogOutInteractor {
    final LogOutPresenter logOutPresenter;

    public LogOutInteractor(LogOutPresenter logOutPresenter) {
        this.logOutPresenter = logOutPresenter;
    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        logOutPresenter.showLogin();
    }
}
