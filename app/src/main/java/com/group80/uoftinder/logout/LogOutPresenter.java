package com.group80.uoftinder.logout;

public class LogOutPresenter {
    private final LogOutViewInterface logOutViewInterface;
    private LogOutInteractor logOutInteractor;

    public LogOutPresenter(LogOutViewInterface logOutViewInterface) {
        this.logOutViewInterface = logOutViewInterface;
    }

    public void setLogOutInteractor(LogOutInteractor logOutInteractor) {
        this.logOutInteractor = logOutInteractor;
    }

    public void signOut() {
        logOutInteractor.signOut();
    }

    public void showLogin() {
        logOutViewInterface.showLogin();
    }
}
