package com.group80.uoftinder.login_use_case;

import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.feed.RecommendationView;

public class LoginPresenterFormatter implements LoginPresenter {

    final Class<RecommendationView> recommendationViewClass;
    final LoginViewModel loginViewModel;

    public LoginPresenterFormatter(Class<RecommendationView> recommendationViewClass, LoginViewModel loginViewInterface) {
        this.recommendationViewClass = recommendationViewClass;
        this.loginViewModel = loginViewInterface;
    }

    /**
     * Pop-ups a message indicating login was successful and changes view to recommendation view
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
     * If there is a missing input, sets an error to text and also requests focus, shows error as error message
     * @param error error message
     */
    @Override
    public void prepareEmailFailureView(String error) {
        loginViewModel.showEmailMessage(error);
    }

    @Override
    public void preparePasswordFailureView(String error) {
        loginViewModel.showPasswordMessage(error);
    }
}
