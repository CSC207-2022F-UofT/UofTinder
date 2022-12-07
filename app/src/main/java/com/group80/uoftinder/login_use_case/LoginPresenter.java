package com.group80.uoftinder.login_use_case;

import com.group80.uoftinder.entities.User;

// Interface Adapter Layer
/**
 * Updates view according to user logging in successfully or unsuccessfully
 */
public class LoginPresenter implements LoginPresenterInterface {

    final LoginViewInterface loginViewInterface;

    /**
     * Creates the presenter object to interact with the view and use case layers.
     * @param loginViewInterface    LoginViewInterface so the presenter can update view
     */
    public LoginPresenter(LoginViewInterface loginViewInterface) {
        this.loginViewInterface = loginViewInterface;
    }

    /**
     * Pop-ups a message indicating login was successful and changes view to recommendation view
     *
     * @param success       the success message for the toast to display
     * @param currentUser   the current user object
     */
    @Override
    public void prepareSuccessView(String success, User currentUser) {
        loginViewInterface.showMessageToast(success);
        loginViewInterface.updateUI(currentUser);
    }


    /**
     * If email and password combination do not match, pop-ups a message indicating login failure
     * @param unsuccessful  the error message for the toast to display
     */
    @Override
    public void prepareLoginFailureView(String unsuccessful) {
        loginViewInterface.showMessageToast(unsuccessful);
    }

    /**
     * If email input is missing, notifies the user
     *
     * @param error error message
     */
    @Override
    public void prepareEmailFailureView(String error) {
        loginViewInterface.showEmailMessage(error);
    }

    /**
     * If password input is missing, notifies the user
     *
     * @param error error message
     */
    @Override
    public void preparePasswordFailureView(String error) {
        loginViewInterface.showPasswordMessage(error);
    }
}
