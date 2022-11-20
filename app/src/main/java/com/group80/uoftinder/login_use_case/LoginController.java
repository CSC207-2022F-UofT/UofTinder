package com.group80.uoftinder.login_use_case;

import android.widget.EditText;

public class LoginController {
    final LoginInput loginInput;

    public LoginController(LoginInput loginGateway) {
        this.loginInput = loginGateway;
    }

    public void loginUser(EditText loginEmail, EditText loginPassword) {
        loginInput.loginUser(loginEmail, loginPassword);
    }
}
