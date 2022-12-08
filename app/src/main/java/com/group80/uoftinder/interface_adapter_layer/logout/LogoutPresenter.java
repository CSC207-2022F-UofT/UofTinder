package com.group80.uoftinder.interface_adapter_layer.logout;

import com.group80.uoftinder.use_case_layer.logout.LogoutInteractor;
import com.group80.uoftinder.use_case_layer.logout.LogoutPresenterInterface;

/**
 * Responsible for responding to user interaction of logging out and updating the view
 */
public class LogoutPresenter implements LogoutPresenterInterface {
    private final LogoutViewInterface logoutViewInterface;
    private final LogoutInteractor logoutInteractor;

    public LogoutPresenter(LogoutViewInterface logoutViewInterface, LogoutInteractor logoutInteractor) {
        this.logoutViewInterface = logoutViewInterface;
        this.logoutInteractor = logoutInteractor;
    }

    /**
     * Calls interactor's signOut() method to log out the user, changes to the Login screen
     */
    @Override
    public void signOut() {
        logoutInteractor.signOut();
        logoutViewInterface.showLogin();
    }
}