package com.group80.uoftinder.interface_adapter_layer.login;

import android.widget.EditText;

import com.group80.uoftinder.use_case_layer.login.LoginInput;

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
