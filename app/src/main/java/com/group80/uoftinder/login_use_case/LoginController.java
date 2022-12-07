package com.group80.uoftinder.login_use_case;

import android.widget.EditText;

// Interface Adapter Layer
/**
 * Responds to user activity when they try to sign into their account
 */
public class LoginController {
    final LoginInput loginInput;

    public LoginController(LoginInput loginGateway) {
        this.loginInput = loginGateway;
    }


    /**
     * Attempts to log in user using email and password
     *
     * @param loginEmail    email input
     * @param loginPassword password input
     */
    public void loginUser(EditText loginEmail, EditText loginPassword) {
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        loginInput.loginUser(email, password);
    }
}
