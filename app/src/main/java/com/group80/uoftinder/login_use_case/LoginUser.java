package com.group80.uoftinder.login_use_case;

import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class LoginUser {
    private FirebaseAuth mAuth;
    private EditText loginEmail;
    private EditText loginPassword;

    public LoginUser(FirebaseAuth mAuth, EditText loginEmail, EditText loginPassword) {
        this.mAuth = mAuth;
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

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }
}
