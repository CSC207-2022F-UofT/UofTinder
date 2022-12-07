/**
 * Updates view according to user logging in successfully or unsuccessfully
 */

package com.group80.uoftinder.login_use_case;

// Interface Adapter Layer

import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.feed.RecommendationView;

public class LoginPresenter implements LoginPresenterInterface {

    final Class<RecommendationView> recommendationViewClass;
    final LoginViewInterface loginViewModel;

    public LoginPresenter(Class<RecommendationView> recommendationViewClass, LoginViewInterface loginViewInterface) {
        this.recommendationViewClass = recommendationViewClass;
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
