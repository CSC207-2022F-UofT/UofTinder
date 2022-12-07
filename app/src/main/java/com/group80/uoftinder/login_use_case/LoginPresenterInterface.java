package com.group80.uoftinder.login_use_case;

import com.group80.uoftinder.entities.User;

/**
 * Provides methods to prepare view changes
 */
public interface LoginPresenterInterface {
    void prepareSuccessView(String success, User currentUser);

    void prepareLoginFailureView(String error);

    void prepareEmailFailureView(String error);

    void preparePasswordFailureView(String error);
}
