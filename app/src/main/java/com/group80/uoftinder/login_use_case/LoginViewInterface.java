/**
 * Provides methods to update view
 */

package com.group80.uoftinder.login_use_case;

import com.group80.uoftinder.entities.User;

public interface LoginViewInterface {
    void updateUI(User currentUser);

    void showMessageToast(String message);

    void showEmailMessage(String error);

    void showPasswordMessage(String error);
}
