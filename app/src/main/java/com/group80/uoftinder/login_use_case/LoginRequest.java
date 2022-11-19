package com.group80.uoftinder.login_use_case;

import android.widget.EditText;

public class LoginRequest {
    private EditText loginEmail;
    private EditText loginPassword;

    public LoginRequest(EditText loginEmail, EditText loginPassword) {
        this.loginEmail = loginEmail;
        this.loginPassword = loginPassword;
    }

    public EditText getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(EditText loginEmail) {
        this.loginEmail = loginEmail;
    }

    public EditText getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(EditText loginPassword) {
        this.loginPassword = loginPassword;
    }
}
