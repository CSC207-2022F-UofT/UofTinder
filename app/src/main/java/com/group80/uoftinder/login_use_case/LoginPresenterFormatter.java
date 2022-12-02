package com.group80.uoftinder.login_use_case;

import com.google.firebase.auth.FirebaseUser;
import com.group80.uoftinder.feed.RecommendationView;

public class LoginPresenterFormatter implements LoginPresenter {

    final Class<RecommendationView> recommendationViewClass;
    final LoginViewModel loginViewInterface;

    public LoginPresenterFormatter(Class<RecommendationView> recommendationViewClass, LoginViewModel loginViewInterface) {
        this.recommendationViewClass = recommendationViewClass;
        this.loginViewInterface = loginViewInterface;
    }

    /**
     * Pop-ups a message indicating login was successful and changes view to recommendation view
     * @param firebaseUser current FirebaseUser
     */
    @Override
    public void prepareSuccessView(String success, FirebaseUser firebaseUser) {
        loginViewInterface.showMessageToast(success);
        loginViewInterface.updateUI(firebaseUser);
    }

    /**
     * If email and password combination do not match, pop-ups a message indicating login failure
     */
    @Override
    public void prepareLoginFailureView(String unsuccessful) {
        loginViewInterface.showMessageToast(unsuccessful);
    }

    /**
     * If there is a missing input, sets an error to text and also requests focus, shows error as error message
     * @param error error message
     */
    @Override
    public void prepareEmailFailureView(String error) {
        loginViewInterface.showEmailMessage(error);
    }

    @Override
    public void preparePasswordFailureView(String error) {
        loginViewInterface.showPasswordMessage(error);
    }
}
