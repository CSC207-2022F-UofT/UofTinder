package com.group80.uoftinder.logout;

public class LogOutPresenter implements LogOutPresenterInterface{
    private final LogOutViewInterface logOutViewInterface;
    private final LogOutInteractor logOutInteractor;

    public LogOutPresenter(LogOutViewInterface logOutViewInterface, LogOutInteractor logOutInteractor) {
        this.logOutViewInterface = logOutViewInterface;
        this.logOutInteractor = logOutInteractor;

    }

    /**
     * Calls interactor's signOut() method to log out the user, changes to the Login screen
     */
    @Override
    public void signOut() {
        logOutInteractor.signOut();
        logOutViewInterface.showLogin();
    }
}
