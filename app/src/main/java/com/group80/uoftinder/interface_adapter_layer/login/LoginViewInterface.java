package com.group80.uoftinder.interface_adapter_layer.login;

import com.group80.uoftinder.entities_layer.User;

/**
 * Provides methods to update view
 */
public interface LoginViewInterface {
    void updateUI(User currentUser);

    void showMessageToast(String message);

    void showEmailMessage(String error);

    void showPasswordMessage(String error);
}
