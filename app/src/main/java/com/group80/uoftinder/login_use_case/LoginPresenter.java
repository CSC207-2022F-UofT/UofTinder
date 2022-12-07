package com.group80.uoftinder.login_use_case;

import com.group80.uoftinder.entities.User;

// Interface Adapter Layer
/**
 * Updates view according to user logging in successfully or unsuccessfully
 */
public class LoginPresenter implements LoginPresenterInterface {

    final LoginViewInterface loginViewModel;

    public LoginPresenter(LoginViewInterface loginViewInterface) {
        this.loginViewModel = loginViewInterface;
    }

    /**
     * Pop-ups a message indicating login was successful and changes view to recommendation view
     *
     * @param currentUser current User
     */
    @Override
    public void prepareSuccessView(String success, User currentUser) {
        loginViewModel.showMessageToast(success);
        loginViewModel.updateUI(currentUser);
    }

    /**
     * If email and password combination do not match, pop-ups a message indicating login failure
     */
    @Override
    public void prepareLoginFailureView(String unsuccessful) {
        loginViewModel.showMessageToast(unsuccessful);
    }

    /**
     * If email input is missing, notifies the user
     *
     * @param error error message
     */
    @Override
    public void prepareEmailFailureView(String error) {
        loginViewModel.showEmailMessage(error);
    }

    /**
     * If password input is missing, notifies the user
     *
     * @param error error message
     */
    @Override
    public void preparePasswordFailureView(String error) {
        loginViewModel.showPasswordMessage(error);
    }
}
